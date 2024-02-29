/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.repositories.cart;

import com.cowards.onlyarts.repositories.orderDetails.OrderDetailsDTO;
import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Admin
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartDTO {

    private HashMap<String, OrderDetailsDTO> orderDetails = new HashMap<>();
    private String paymentMethod;
    private float totalPrice;
}
