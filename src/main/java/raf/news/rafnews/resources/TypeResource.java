package raf.news.rafnews.resources;

import raf.news.rafnews.services.TypeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/types")
public class TypeResource {

    @Inject
    private TypeService typeService;

    @GET
    @Produces({"application/json"})
    public Response allTypes(){
        return Response.ok(typeService.allTypes()).build();
    }
}
