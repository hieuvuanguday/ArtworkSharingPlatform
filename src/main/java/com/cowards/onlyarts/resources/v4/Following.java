
package com.cowards.onlyarts.resources.v4;

import com.cowards.onlyarts.models.users.UserDAO;
import com.cowards.onlyarts.models.users.UserDTO;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truon
 */
@Path("v4/follow")
public class Following {
    private final UserDAO userDao = UserDAO.getInstance();
    
    @GET
    @Path("following")
    public Response getFollowing(@HeaderParam("authtoken") String tokenString){
        List<UserDTO> followingList = new ArrayList<>();
        String userId = "US0001";
        followingList = userDao.getFollowing(userId);
        return !followingList.isEmpty() ?
                    Response.ok(followingList, MediaType.APPLICATION_JSON).build() :
                    Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("countfollowing")
    public Response countFollowing(@HeaderParam("authtoken") String tokenString){
        int numberoffolowing = 0;
        String userId = "US0001";
        numberoffolowing = userDao.countFollowing(userId);
        return Response.ok(numberoffolowing, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("follower")
    public Response getFollower(@HeaderParam("authtoken") String tokenString){
        List<UserDTO> followerList = new ArrayList<>();
        String userId = "US0001";
        followerList = userDao.getFollower(userId);
        return !followerList.isEmpty() ?
                    Response.ok(followerList, MediaType.APPLICATION_JSON).build() :
                    Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @GET
    @Path("countfollower")
    public Response countFollower(@HeaderParam("authtoken") String tokenString){
        int numberoffolower = 0;
        String userId = "US0001";
        numberoffolower = userDao.countFollower(userId);
        return Response.ok(numberoffolower, MediaType.APPLICATION_JSON).build();
    }
    
    
    @POST
    @Path("{user_id}")
    public Response followUser(@HeaderParam("authtoken") String tokenString,@PathParam("user_id") String userId){
        boolean checkFollow = false;
        String loginUser = "US0001";
        checkFollow = userDao.followUser(loginUser,userId);
        return checkFollow ? 
                Response.status(Response.Status.NO_CONTENT).build():
                Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Path("{user_id}")
    public Response unfollowUser(@HeaderParam("authtoken") String tokenString,@PathParam("user_id") String userId){
        boolean checkFollow = false;
        String loginUser = "US0001";
        checkFollow = userDao.unfollowUser(loginUser,userId);
        return checkFollow ? 
                Response.status(Response.Status.NO_CONTENT).build():
                Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("check/{user_id}")
    public Response checkFollow(@HeaderParam("authtoken") String tokenString,@PathParam("user_id") String userId){
        boolean checkFollow = false;
        String loginUserId = "US0001";
        checkFollow = userDao.checkFollow(loginUserId,userId);
        return Response.ok(checkFollow, MediaType.APPLICATION_JSON).build();
    }
    
}
