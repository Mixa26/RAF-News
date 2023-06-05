package raf.news.rafnews.resources;

import raf.news.rafnews.entities.Category;
import raf.news.rafnews.services.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/categories")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Produces({"application/json"})
    public Response allCategories(){
        return Response.ok(categoryService.allCategories()).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response updateCategory(Category category){
        Map<String, String> response = new HashMap<>();

        if ((category == null) || (category.getName() == null || category.getDescription() == null) ||
                (category.getName().equals("") || category.getDescription().equals("")
                || category.getId() < 1)){
            response.put("message", "Provided inputs aren't valid!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        Category categoryAdded = categoryService.updateCategory(category);

        if (categoryAdded == null){
            response.put("message", "There is already a category with this name!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        return Response.ok(response).build();
    }

    @POST
    @Produces({"application/json"})
    public Response addCategory(Category category){
        Map<String, String> response = new HashMap<>();

        if ((category == null) || (category.getName() == null || category.getDescription() == null) ||
                (category.getName().equals("") || category.getDescription().equals(""))){
            response.put("message", "Provided inputs aren't valid!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        Category categoryAdded = categoryService.addCategory(category);

        if (categoryAdded == null){
            response.put("message", "There is already a category with this name!");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }

        return Response.ok(categoryAdded).build();
    }

    @DELETE
    @Produces({"application/json"})
    public Response deleteCategory(int id){
        Map<String, String> response = new HashMap<>();

        if (categoryService.deleteCategory(id) == null){
            response.put("message", "There are news in this category, delete them first.");
            return Response.status(403, "Action forbidden!").entity(response).build();
        }
        return Response.ok().build();
    }
}
