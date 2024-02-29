package com.cowards.onlyarts.resources.v4;

import com.cowards.onlyarts.models.users.UserDAO;
import com.cowards.onlyarts.models.users.UserDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author truon
 */
@Path("v4/user")
public class User {
    
    private final UserDAO userDao = UserDAO.getInstance();
    
    @GET
    public Response getUserProfile(@HeaderParam("authtoken") String tokenString,@Context HttpHeaders header){
        UserDTO user = null;
        String userId = "US0001";
        user = userDao.getUserProfile(userId);
        return user != null ?
                Response.ok(user, MediaType.APPLICATION_JSON).build():
                Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{user_id}")
    public Response getUser(@PathParam("user_id") String userId){
        UserDTO user = null;
        user = userDao.getUserProfile(userId);
        return user != null ? 
                Response.ok(user, MediaType.APPLICATION_JSON).build():
                Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateUser(UserDTO user) {
        boolean checkUpdate = userDao.updateUser(user);
        return checkUpdate ? 
                Response.status(Response.Status.NO_CONTENT).build():
                Response.status(Response.Status.NOT_FOUND).build();
    }
}
