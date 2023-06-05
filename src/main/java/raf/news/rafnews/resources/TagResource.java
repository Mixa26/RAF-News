package raf.news.rafnews.resources;

import raf.news.rafnews.entities.Comment;
import raf.news.rafnews.entities.Tag;
import raf.news.rafnews.services.TagService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tags")
public class TagResource {

    @Inject
    private TagService tagService;

    @GET
    @Produces({"application/json"})
    public Response allTags(){
        return Response.ok(tagService.allTags()).build();
    }

    @POST
    @Produces({"application/json"})
    public Response addTag(Tag tag){
        return Response.ok(tagService.addTag(tag)).build();
    }

    @DELETE
    @Produces({"application/json"})
    public Response deleteTag(int id){
        return Response.ok(tagService.deleteTag(id)).build();
    }
}
