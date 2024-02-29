/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v3;

import com.cowards.onlyarts.repositories.users.UserDTO;
import com.cowards.onlyarts.services.UserDAO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Admin
 */
@Path("/v3/admin/users/statistics")
public class ViewUserStatistics {
    private static final UserDAO userDao = UserDAO.getInstance();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        try {
            HashMap result = new HashMap();
            
            List<UserDTO> creator = userDao.getAll("CT");
            List<UserDTO> customer = userDao.getAll("CS");
            int creatorQuantity = creator.size();
            int customerQuantity = customer.size();
            
            result.put("creatorQuantity", creatorQuantity);
            result.put("creatorList", creator);
            result.put("customerQuantity", customerQuantity);
            result.put("customerList", customer);
            
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.ok(e).build();
        }
    }
}
