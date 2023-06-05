package raf.news.rafnews.resources;

import raf.news.rafnews.entities.Comment;
import raf.news.rafnews.services.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @POST
    @Produces({"application/json"})
    public Response addComment(Comment comment){
        Map<String, String> response = new HashMap<>();

        if ((comment == null) || (comment.getContent() == null
                || comment.getAuthor() == null || comment.getTimeCreated() == null ||
                (comment.getContent().equals("") || comment.getAuthor().equals("")
                || comment.getTimeCreated() < (new Date().getTime() - 86400000)))){
            response.put("message", "Provided inputs aren't valid!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        Comment commentAdded = commentService.addComment(comment);

        if (commentAdded == null){
            response.put("message", "Provided news Id doesn't exist!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        return Response.ok(commentAdded).build();
    }

    @GET
    @Produces({"application/json"})
    @Path("/{id}")
    public List<Comment> allPostComments(@PathParam("id") int id){
        return commentService.allPostComments(id);
    }

    @GET
    @Produces({"application/json"})
    public List<Comment> allComments(){
        return commentService.allComments();
    }
}
