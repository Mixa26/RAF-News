package raf.news.rafnews.resources;

import raf.news.rafnews.entities.News;
import raf.news.rafnews.services.NewsService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;

    @GET
    @Produces({"application/json"})
    public Response allNews(){
        return Response.ok(newsService.allNews()).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response updateNews(News news){
        Map<String, String> response = new HashMap<>();

        if ((news == null) || (news.getTitle() == null || news.getContent() == null)
                || (news.getTitle().equals("") || news.getContent().equals("")
                || news.getTimeCreated() < 0 || news.getNumOfVisits() < 0
                || news.getId() < 1 || news.getUserId() < 1)){
            response.put("message", "Provided inputs aren't valid!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        News newsUpdated = newsService.updateNews(news);

        if (newsUpdated == null){
            response.put("message", "Provided category Id or user Id doesn't exist.");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        return Response.ok(newsUpdated).build();
    }

    @PUT
    @Produces({"application/json"})
    @Path("/incr")
    public Response increaseVisits(News news){
        newsService.increaseVisits(news.getId());
        return Response.ok().build();
    }

    @POST
    @Produces({"application/json"})
    public Response addNews(News news){
        Map<String, String> response = new HashMap<>();

        if ((news == null) || (news.getTitle() == null || news.getContent() == null)
                || (news.getTitle().equals("") || news.getContent().equals("")
                || news.getTimeCreated() < 0 || news.getNumOfVisits() < 0
                || news.getUserId() < 1)){
            response.put("message", "Provided inputs aren't valid!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        News newsAdded = newsService.addNews(news);

        if (newsAdded == null){
            response.put("message", "Provided category Id or user Id doesn't exist.");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        return Response.ok(newsAdded).build();
    }

    @DELETE
    @Produces({"application/json"})
    public Response deleteCategory(int id){
        return Response.ok(newsService.deleteNews(id)).build();
    }
}
