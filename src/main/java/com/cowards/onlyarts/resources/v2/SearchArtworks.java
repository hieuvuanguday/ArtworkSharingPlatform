/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v2;

import com.cowards.onlyarts.models.artworks.ArtworkDAO;
import com.cowards.onlyarts.models.artworks.ArtworkDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author dell
 */
@Path("resources/v2/artworks/search")
public class SearchArtworks {
    @GET
    @Path("/type/{type_input}")
    public Response searchByType(@PathParam("type_input") String typeInput) {
        if(typeInput.isBlank()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ArtworkDAO dao = new ArtworkDAO();
        List<ArtworkDTO> listArtwork = null;
        listArtwork = dao.getListArtworkWithType(typeInput);
        if (!listArtwork.isEmpty()) {
            return Response.ok(listArtwork, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/title/{title_input}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response searchByName(@PathParam("title_input") String titleInput) {
        if(titleInput.isBlank()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ArtworkDAO dao = new ArtworkDAO();
        List<ArtworkDTO> listArtwork = null;
        listArtwork = dao.getListArtworkWithName(titleInput);
        if (!listArtwork.isEmpty()) {
            return Response.ok(listArtwork, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/getall")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getAll() {
        ArtworkDAO dao = new ArtworkDAO();
        List<ArtworkDTO> listArtwork = null;
        listArtwork = dao.getAllListArtwork();
        if (!listArtwork.isEmpty()) {
            return Response.ok(listArtwork, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}