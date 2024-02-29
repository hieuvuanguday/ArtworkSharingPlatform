/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v2;

import com.cowards.onlyarts.models.favorites.FavoriteDAO;
import com.cowards.onlyarts.models.favorites.FavoriteDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author dell
 */
@Path("resources/v2/artworks/favorite")
public class SaveToFavoriteList {
    @GET
    @Path("/{user_id}")
    public Response viewFavorite(@PathParam("user_id") String userId) {
        FavoriteDAO dao = new FavoriteDAO();
        List<FavoriteDTO> favorite = null;
        favorite = dao.getFavorite(userId);
        if (!favorite.isEmpty()) {
            return Response.ok(favorite, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFavorite(@HeaderParam("authtoken") String tokenString, FavoriteDTO favorite) {
        FavoriteDAO dao = new FavoriteDAO();
        String userId = "US0002"; //Thuc sua nha, String userId = "tokenString";
        boolean checkAddNewFavorite = false;
        checkAddNewFavorite = dao.addNewFavorite(favorite.getUserId(), favorite.getArtworkId());
        return checkAddNewFavorite ? 
                Response.status(Response.Status.NO_CONTENT).build(): 
                Response.status(Response.Status.NOT_FOUND).build();
    }
}
