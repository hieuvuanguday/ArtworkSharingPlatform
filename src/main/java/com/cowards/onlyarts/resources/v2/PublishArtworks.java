/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v2;

import com.cowards.onlyarts.models.artworks.ArtworkDAO;
import com.cowards.onlyarts.models.artworks.ArtworkDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author dell
 */
@Path("resources/v2/artworks/publish")
public class PublishArtworks {
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addArtwork(@HeaderParam("authtoken") String tokenString, ArtworkDTO artwork) {
        ArtworkDAO dao = new ArtworkDAO();
        String userId = "US0002"; //Thuc sua nha, String userId = "tokenString";
        artwork.setOwnerId(userId);
        boolean checkAddNewArtwork = false;
        checkAddNewArtwork = dao.addNewArtwork(artwork);
        return checkAddNewArtwork ? 
                Response.status(Response.Status.NO_CONTENT).build(): 
                Response.status(Response.Status.NOT_FOUND).build();
    }
}
