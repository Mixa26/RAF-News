package raf.news.rafnews.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;
import raf.news.rafnews.entities.User;
import raf.news.rafnews.repositories.UserRepository;

import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public UserService(){}

    public User addUser(User user){
        String hashedPassword = DigestUtils.sha256Hex(user.getPassword());

        user.setPassword(hashedPassword);

        return userRepository.addUser(user);
    }

    public User updateUser(User user){
        return userRepository.updateUser(user);
    }

    public List<User> allUsers(){
        return userRepository.allUsers();
    }

    public String findUser(String email, String password){
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = userRepository.findUser(email);

        if (user == null || !user.getPassword().equals(hashedPassword)){
            return null;
        }

        if (user.getStatusId() == 0){
            return "~inactive";
        }

        Integer id = user.getId();

        Date issuedAt = new Date();

        Algorithm algorithm = Algorithm.HMAC256("secret");

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withSubject(email)
                .withClaim("id", id)
                .withClaim("name", user.getName())
                .withClaim("typeId", user.getTypeId())
                .sign(algorithm);
    }

    public User findUser(int id){
        User user = userRepository.findUser(id);

        user.setPassword("");

        return user;
    }

    public User deactivateUser(int id){
        return userRepository.deactivateUser(id);
    }

    public User deleteUser(int id){

        return userRepository.deleteUser(id);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();

        User user = this.userRepository.findUser(email);

        if (user == null){
            return false;
        }

        return true;
    }
}
