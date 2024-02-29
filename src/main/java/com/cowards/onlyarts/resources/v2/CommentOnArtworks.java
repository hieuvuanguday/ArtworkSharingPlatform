/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v2;

import com.cowards.onlyarts.models.comments.CommentDAO;
import com.cowards.onlyarts.models.comments.CommentDTO;
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
@Path("resources/v2/artworks/comment")
public class CommentOnArtworks {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response comment(@HeaderParam("authtoken") String tokenString, CommentDTO comment) {
        CommentDAO dao = new CommentDAO();
        String userId = "US0002"; //Thuc sua nha, String userId = "tokenString";
        boolean checkAddNewComment = false;
        checkAddNewComment = dao.addNewComment(comment.getCommenterId(), comment.getArtworkId(), comment.getDescription(), comment.getCommentTime());
        return checkAddNewComment ? 
                Response.status(Response.Status.NO_CONTENT).build(): 
                Response.status(Response.Status.NOT_FOUND).build();
    }
}
