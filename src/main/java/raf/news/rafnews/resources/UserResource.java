package raf.news.rafnews.resources;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import raf.news.rafnews.entities.User;
import raf.news.rafnews.requests.LoginRequest;
import raf.news.rafnews.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Produces({"application/json"})
    public Response addUser(@HeaderParam("Authorization") String token, User user) {
        if (token == null){
            return Response.status(401, "Unauthorized access, please login.").build();
        }

        Map<String, String> response = new HashMap<>();

        if ((user == null) || (user.getName() == null || user.getSurname() == null
                || user.getEmail() == null)
                || (user.getName().equals("") || user.getSurname().equals("")
                || user.getEmail().equals("")
                || (!(user.getStatusId() == 1 || user.getStatusId() == 0))
                || (!(user.getTypeId() == 1 || user.getTypeId() == 2))
                || user.getPassword().equals(""))){
            response.put("message", "Provided inputs aren't valid!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token.replace("Bearer ", ""));
        Integer userId = decodedJWT.getClaim("id").asInt();

        if (userService.findUser(userId).getTypeId() != 1) {
            response.put("message", "You're not an admin!");
            return Response.status(401, "Unauthorized access!").entity(response).build();
        }

        User userAdded = userService.addUser(user);

        if (userAdded == null){
            response.put("message", "An account with given email already exists!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        return Response.ok(userAdded).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response updateUser(@HeaderParam("Authorization") String token, User user) {
        if (token == null){
            return Response.status(401, "Unauthorized access, please login.").build();
        }

        Map<String, String> response = new HashMap<>();

        if ((user == null) || (user.getName() == null || user.getSurname() == null
                || user.getEmail() == null)
                || (user.getName().equals("") || user.getSurname().equals("")
                || user.getEmail().equals("")
                || (!(user.getStatusId() == 1 || user.getStatusId() == 0))
                || (!(user.getTypeId() == 1 || user.getTypeId() == 2))
                || user.getId() < 1)){
            response.put("message", "Provided inputs aren't valid!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token.replace("Bearer ", ""));
        Integer userId = decodedJWT.getClaim("id").asInt();

        if (userService.findUser(userId).getTypeId() != 1) {
            response.put("message", "You're not an admin!");
            return Response.status(401, "Unauthorized access!").entity(response).build();
        }

        User userUpdated = userService.updateUser(user);

        if (userUpdated == null){
            response.put("message", "An account with given email already exists!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        return Response.ok(userUpdated).build();
    }

    @GET
    @Produces({"application/json"})
    public Response allUsers(@HeaderParam("Authorization") String token) {
        List<User> users = userService.allUsers();
        if (token == null || token.equals("Bearer")){
            for (User user : users){
                user.setPassword("");
                user.setSurname("");
                user.setStatusId(-1);
                user.setEmail("");
                user.setTypeId(-1);
            }
            return Response.ok(users).build();
        }

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token.replace("Bearer ", ""));
        Integer userId = decodedJWT.getClaim("id").asInt();

        if (userService.findUser(userId).getTypeId() != 1) {
            return Response.ok(users).build();
        }

        return Response.ok(users).build();
    }

    @POST
    @Produces({"application/json"})
    @Path("/login")
    public Response findUser(LoginRequest loginRequest) {
        Map<String, String> response = new HashMap<>();

        String jwt = userService.findUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (jwt == null){
            response.put("message", "Bad credentials!");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        if (jwt.equals("~inactive")){
            response.put("message", "You're account status has been set inactive by the admin!");
            return Response.status(403, "Access forbidden!").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }

    @GET
    @Produces({"application/json"})
    @Path("/{id}")
    public Response findUser(@PathParam("id") int id){
        return Response.ok(userService.findUser(id)).build();
    }

    @POST
    @Produces({"application/json"})
    @Path("/deactivate")
    public Response deactivateUser(@HeaderParam("Authorization") String token, int id) {
        Map<String, String> response = new HashMap<>();

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token.replace("Bearer ", ""));
        Integer userId = decodedJWT.getClaim("id").asInt();

        if (userService.findUser(userId).getTypeId() != 1) {
            response.put("message", "You're not an admin!");
            return Response.status(401, "Unauthorized access!").entity(response).build();
        }

        User user = userService.deactivateUser(id);

        if (user == null){
            response.put("message", "No regular user with provided id!");
            return Response.status(403, "Forbidden action").entity(response).build();
        }

        return Response.ok(user).build();
    }

    @GET
    @Produces({"application/json"})
    @Path("/curr")
    public Response currUser(@HeaderParam("Authorization") String token) {
        if (token == null){
            return Response.status(401, "Unauthorized access, please login.").build();
        }

        User user = new User();

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token.replace("Bearer ", ""));
        Integer userId = decodedJWT.getClaim("id").asInt();
        String userName = decodedJWT.getClaim("name").asString();

        user.setId(userId);
        user.setName(userName);
        return Response.ok(user).build();
    }

    @DELETE
    @Produces({"application/json"})
    public Response deleteUser(@HeaderParam("Authorization") String token, int id){
        Map<String, String> response = new HashMap<>();

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token.replace("Bearer ", ""));
        Integer userId = decodedJWT.getClaim("id").asInt();

        if (userService.findUser(userId).getTypeId() != 1) {
            response.put("message", "You're not an admin!");
            return Response.status(401, "Unauthorized access!").entity(response).build();
        }

        return Response.ok(userService.deleteUser(id)).build();
    }

    @POST
    @Produces({"application/json"})
    @Path("/authenticated")
    public Response isUserAuthenticated(@HeaderParam("Authorization") String token){
        if (token == null)return Response.ok(false).build();

        String tokenStripped = token.replace("Bearer", "");
        tokenStripped = tokenStripped.replace(" ", "");

        return Response.ok(userService.isAuthorized(tokenStripped)).build();
    }

    @GET
    @Produces({"application/json"})
    @Path("/admin")
    public Response isUserAdmin(@HeaderParam("Authorization") String token){
        if (token == null){
            return Response.status(401, "Unauthorized access, please login.").build();
        }

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token.replace("Bearer ", ""));
        Integer userId = decodedJWT.getClaim("id").asInt();

        boolean isAdmin = false;

        if (userService.findUser(userId).getTypeId() == 1) isAdmin = true;

        return Response.ok(isAdmin).build();
    }
}
