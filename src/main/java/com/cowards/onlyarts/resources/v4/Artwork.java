package com.cowards.onlyarts.resources.v4;

import com.cowards.onlyarts.models.artworks.ArtworkDAO;
import com.cowards.onlyarts.models.artworks.ArtworkDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("v4/artwork")
public class Artwork {
    private final ArtworkDAO artworkDAO = ArtworkDAO.getInstance();
    
    @GET
    @Path("favorite")
    public Response getFavoriteArtwork(@HeaderParam("authtoken") String tokenString){
        List<ArtworkDTO> favoriteArtwork = new ArrayList<>();
        String userId = "US0001";
        favoriteArtwork = artworkDAO.getFavoriteArtwork(userId);
        return !favoriteArtwork.isEmpty() ?
                Response.ok(favoriteArtwork, MediaType.APPLICATION_JSON).build():
                Response.status(Response.Status.NO_CONTENT).build();
    }
}
