/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v2;

import com.cowards.onlyarts.models.artworks.ArtworkDAO;
import com.cowards.onlyarts.models.artworks.ArtworkDTO;
import com.cowards.onlyarts.models.comments.CommentDAO;
import com.cowards.onlyarts.models.comments.CommentDTO;
import com.cowards.onlyarts.models.users.UserDAO;
import com.cowards.onlyarts.models.users.UserDTO;
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
@Path("resources/v2/artworks/view")
public class ViewArtworkDetailingInformation {

    @GET
    @Path("/artwork/{artwork_id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response viewArtwork(@PathParam("artwork_id") String artworkId) {
        ArtworkDAO dao = new ArtworkDAO();
        List<ArtworkDTO> artwork = null;
        artwork = dao.getDetailArtwork(artworkId);
        if (!artwork.isEmpty()) {
            return Response.ok(artwork, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/comment/{artwork_id}")
    public Response viewComment(@PathParam("artwork_id") String artworkId) {
        CommentDAO dao = new CommentDAO();
        List<CommentDTO> comment = null;
        comment = dao.getDetailComment(artworkId);
        if (!comment.isEmpty()) {
            return Response.ok(comment, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/reaction/{artwork_id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response viewReaction(@PathParam("artwork_id") String artworkId) {
        List<UserDTO> userList = null;
        userList = UserDAO.getUserReaction(artworkId);
        if (!userList.isEmpty()) {
            return Response.ok(userList, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
