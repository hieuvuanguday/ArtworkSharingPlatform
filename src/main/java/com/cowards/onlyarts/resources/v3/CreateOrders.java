/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v3;

import com.cowards.onlyarts.core.Generator;
import com.cowards.onlyarts.repositories.cart.CartDTO;
import com.cowards.onlyarts.repositories.orderDetails.OrderDetailsDTO;
import com.cowards.onlyarts.repositories.orders.OrdersDTO;
import com.cowards.onlyarts.services.OrderDetailsDAO;
import com.cowards.onlyarts.services.OrdersDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
@Path("/v3/orders/create")
public class CreateOrders {

    private final OrdersDAO ordersDao = OrdersDAO.getInstance();
    private final OrderDetailsDAO orderDetailsDao = OrderDetailsDAO.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(CartDTO cartDTO) {
        try {
            HashMap result = new HashMap();
            String orderId = Generator.generateUUID();

            String userId = "US0002"; //fix

            OrdersDTO ordersDTO = new OrdersDTO();
            ordersDTO.setOrderId(orderId);
            ordersDTO.setUserId(userId);
            ordersDTO.setStatus(0b00);
            ordersDTO.setPaymentMethod(cartDTO.getPaymentMethod());
            ordersDTO.setTotalPrice(cartDTO.getTotalPrice());
            ordersDao.insert(ordersDTO);
            ordersDTO = ordersDao.getOne(orderId);
            result.put("order", ordersDTO);

            for (Map.Entry<String, OrderDetailsDTO> entry : cartDTO.getOrderDetails().entrySet()) {
                entry.getValue().setOrderId(orderId);
                orderDetailsDao.insert(entry.getValue());
            }
            result.put("orderDetails", cartDTO.getOrderDetails());

            return Response.ok(result).build();
        } catch (Exception e) {
            return Response.ok(e).build();
        }
    }
}
