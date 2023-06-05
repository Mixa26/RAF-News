package raf.news.rafnews.filters;


import com.sun.research.ws.wadl.HTTPMethods;
import raf.news.rafnews.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if (!this.isAuthRequired(requestContext)) {
            return;
        }

        try {
            String token = requestContext.getHeaderString("Authorization");
            if(token != null && token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }

            if (!this.userService.isAuthorized(token)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } catch (Exception exception) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext req) {
        if (req.getUriInfo().getPath().contains("login")
                || (req.getUriInfo().getPath().contains("authenticated") && req.getMethod().equals("POST"))
                || (req.getUriInfo().getPath().contains("news") && req.getMethod().equals("GET"))
                || (req.getUriInfo().getPath().contains("categories") && req.getMethod().equals("GET"))
                || (req.getUriInfo().getPath().contains("users") && req.getMethod().equals("GET"))
                || (req.getUriInfo().getPath().contains("comments"))
                || (req.getUriInfo().getPath().contains("news/incr") && req.getMethod().equals("PUT"))
                || (req.getUriInfo().getPath().contains("tags") && req.getMethod().equals("GET"))
                || (req.getUriInfo().getPath().contains("news_tag_table") && req.getMethod().equals("GET"))){
            return false;
        }

//        List<Object> matchedResources = req.getUriInfo().getMatchedResources();
//        for (Object matchedResource : matchedResources) {
//            if (matchedResource instanceof SubjectResource) {
//                return true;
//            }
//        }

        return true;
    }
}
