/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v3;

import com.cowards.onlyarts.repositories.orders.OrdersDTO;
import com.cowards.onlyarts.services.OrdersDAO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Admin
 */
@Path("/v3/orders/history")
public class ViewOrderStatus {

    private static final OrdersDAO ordersDao = OrdersDAO.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {
        try {
            String userId = "US0002"; //fix
            
            List<OrdersDTO> list = ordersDao.getAll(userId);
            return Response.ok(list).build();
        } catch (Exception e) {
            return Response.ok(e).build();
        }
    }
}
