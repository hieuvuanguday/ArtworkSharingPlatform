/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.services;

import com.cowards.onlyarts.repositories.orderDetails.OrderDetailsDTO;
import com.cowards.onlyarts.core.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class OrderDetailsDAO {

    private static final String INSERT = "INSERT INTO Order_details (order_id, artwork_id) VALUES (?, ?)";

    private final Connection conn = DBContext.getInstance();

    private static OrderDetailsDAO instance;

    private OrderDetailsDAO() {
    }

    public static OrderDetailsDAO getInstance() {
        if (instance == null) {
            instance = new OrderDetailsDAO();
        }
        return instance;
    }

    public void insert(OrderDetailsDTO orderDetailsDTO) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(INSERT);
            stm.setString(1, orderDetailsDTO.getOrderId());
            stm.setString(2, orderDetailsDTO.getArtworkId());
            stm.execute();
        } catch (SQLException e) {
            Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, "Exception found on "
                    + "insert(OrderDetailsDTO orderDetailsDTO) method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(OrderDetailsDAO.class.getName()).log(Level.SEVERE, "Error closing PrepareStatement", e);
                }
            }
        }
    }
}
