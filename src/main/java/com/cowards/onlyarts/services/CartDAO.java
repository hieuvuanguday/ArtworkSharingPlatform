/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.services;

import com.cowards.onlyarts.repositories.cart.CartDTO;
import com.cowards.onlyarts.repositories.orderDetails.OrderDetailsDTO;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class CartDAO {

    private static CartDAO instance;

    private CartDAO() {
    }

    public static CartDAO getInstance() {
        if (instance == null) {
            instance = new CartDAO();
        }
        return instance;
    }

    public CartDTO get(String artworkId) {
        CartDTO cartDTO = new CartDTO();
        HashMap<String, OrderDetailsDTO> orderDetails = new HashMap<>();
        String[] split = artworkId.split("-");

        for (String string : split) {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
            orderDetailsDTO.setArtworkId(string);
            orderDetails.put(string, orderDetailsDTO);
        }

        cartDTO.setOrderDetails(orderDetails);
        return cartDTO;
    }
}
