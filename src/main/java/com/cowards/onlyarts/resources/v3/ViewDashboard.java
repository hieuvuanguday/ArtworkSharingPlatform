/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v3;

import com.cowards.onlyarts.services.ArtworkDAO;
import com.cowards.onlyarts.services.OrdersDAO;
import com.cowards.onlyarts.services.UserDAO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;

/**
 *
 * @author Dat
 */
@Path("/v3/admin/dashboard")
public class ViewDashboard {

    private static final ArtworkDAO artworkDAO = ArtworkDAO.getInstance();
    private static final UserDAO userDAO = UserDAO.getInstance();
    private static final OrdersDAO ordersDAO = OrdersDAO.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response show() {
        try {
            int countUser = userDAO.getAll("CT").size() + userDAO.getAll("CR").size();
            int countArtwork = artworkDAO.getAll().size();
            int countOrder = ordersDAO.getAll().size();
            int profit = Math.round(ordersDAO.currentMonthProfit());
            
            HashMap<String, Integer> result = new HashMap<>();
            result.put("countUser", countUser);
            result.put("countArtwork", countArtwork);
            result.put("countOrder", countOrder);
            result.put("profit", profit);
            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.ok(e).build();
        }
    }
}
