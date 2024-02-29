/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v2;

import com.cowards.onlyarts.models.reactions.ReactionDAO;
import com.cowards.onlyarts.models.reactions.ReactionDTO;
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
@Path("resources/v2/artworks/reaction")
public class ReactOnArtworks {
    @GET
    @Path("/{artwork_id}")
    public Response viewReaction(@PathParam("artwork_id") String artworkId) {
        ReactionDAO dao = new ReactionDAO();
        List<ReactionDTO> reaction = null;
        reaction = dao.getReaction(artworkId);
        if (!reaction.isEmpty()) {
            return Response.ok(reaction, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReaction(@HeaderParam("authtoken") String tokenString, ReactionDTO reaction) {
        ReactionDAO dao = new ReactionDAO();
        String userId = "US0002"; //Thuc sua nha, String userId = "tokenString";
        boolean checkAddNewReaction = false;
        checkAddNewReaction = dao.addNewReaction(reaction.getArtworkId(), reaction.getUserId());
        return checkAddNewReaction ? 
                Response.status(Response.Status.NO_CONTENT).build(): 
                Response.status(Response.Status.NOT_FOUND).build();
    }
}
