package raf.news.rafnews.resources;

import raf.news.rafnews.entities.News_Tag_Table;
import raf.news.rafnews.entities.Tag;
import raf.news.rafnews.services.News_Tag_TableService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("news_tag_table")
public class News_Tag_TableResource {

    @Inject
    private News_Tag_TableService newsTagTableService;

    @GET
    @Produces({"application/json"})
    public Response allEntities(){
        return Response.ok(newsTagTableService.allEntities()).build();
    }

    @POST
    @Produces({"application/json"})
    public Response addEntity(News_Tag_Table entity){
        Map<String, String> response = new HashMap<>();

        if ((entity == null) || (entity.getNewsId() < 1 || entity.getTagId() < 1)){
            response.put("message", "Provided inputs aren't valid!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        return Response.ok(newsTagTableService.addEntity(entity)).build();
    }

    @DELETE
    @Produces({"application/json"})
    public Response deleteEntity(int newsId){
        return Response.ok(newsTagTableService.deletetEntity(newsId)).build();
    }
}
