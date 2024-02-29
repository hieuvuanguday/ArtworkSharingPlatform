/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.services;

import com.cowards.onlyarts.repositories.orders.OrdersDTO;
import com.cowards.onlyarts.core.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class OrdersDAO {

    private static final String GETALL = "SELECT * FROM Orders";
    private static final String GETONE = "SELECT * FROM Orders WHERE order_id = ?";
    private static final String CURRENTMONTHPROFIT = "SELECT SUM(total_price) * 0.05 FROM Orders WHERE YEAR(order_time) = YEAR(GETDATE())"
            + "AND MONTH(order_time) = MONTH(GETDATE())";
    private static final String INSERT = "INSERT INTO Orders (order_id, user_id, status, payment_method, total_price) VALUES (?, ?, ?, ?, ?)";

    private final Connection conn = DBContext.getInstance();

    private static OrdersDAO instance;

    private OrdersDAO() {
    }

    public static OrdersDAO getInstance() {
        if (instance == null) {
            instance = new OrdersDAO();
        }
        return instance;
    }

    public List<OrdersDTO> getAll() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrdersDTO ordersDTO = new OrdersDTO();
        List<OrdersDTO> list = new ArrayList<>();
        try {
            stm = conn.prepareStatement(GETALL);
            rs = stm.executeQuery();
            while (rs.next()) {
                ordersDTO.setOrderId(rs.getString(1));
                ordersDTO.setUserId(rs.getString(2));
                ordersDTO.setStatus(rs.getInt(3));
                ordersDTO.setPaymentMethod(rs.getString(4));
                ordersDTO.setOrderTime(rs.getDate(5));
                ordersDTO.setTotalPrice(rs.getFloat(6));
                list.add(ordersDTO);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "Exception found on getAll() method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "Error closing PrepareStatement", e);
                }
            }
        }
        return list;
    }

    public float currentMonthProfit() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        float profit = 0;
        try {
            stm = conn.prepareStatement(CURRENTMONTHPROFIT);
            rs = stm.executeQuery();
            while (rs.next()) {
                profit = rs.getFloat(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "Exception found on currentMonthProfit() method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "Error closing PrepareStatement", e);
                }
            }
        }
        return profit;
    }

    public void insert(OrdersDTO ordersDTO) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(INSERT);
            stm.setString(1, ordersDTO.getOrderId());
            stm.setString(2, ordersDTO.getUserId());
            stm.setInt(3, ordersDTO.getStatus());
            stm.setString(4, ordersDTO.getPaymentMethod());
            stm.setFloat(5, ordersDTO.getTotalPrice());
            stm.execute();
        } catch (SQLException e) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "Exception found on insert(OrdersDTO ordersDTO) method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "Error closing PrepareStatement", e);
                }
            }
        }
    }

    public OrdersDTO getOne(String orderId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrdersDTO ordersDTO = null;
        try {
            stm = conn.prepareStatement(GETONE);
            stm.setString(1, orderId);
            rs = stm.executeQuery();
            ordersDTO = new OrdersDTO();
            while (rs.next()) {
                ordersDTO.setOrderId(rs.getString(1));
                ordersDTO.setUserId(rs.getString(2));
                ordersDTO.setStatus(rs.getInt(3));
                ordersDTO.setPaymentMethod(rs.getString(4));
                ordersDTO.setOrderTime(rs.getDate(5));
                ordersDTO.setTotalPrice(rs.getFloat(6));
            }
        } catch (SQLException e) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "Exception found on getOne(String orderId) method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "Error closing PrepareStatement", e);
                }
            }
        }
        return ordersDTO;
    }
    
    public List<OrdersDTO> getAll(String userId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrdersDTO ordersDTO = new OrdersDTO();
        List<OrdersDTO> list = new ArrayList<>();
        try {
            stm = conn.prepareStatement(GETALL + "WHERE user_id = ?");
            stm.setString(1, userId);
            rs = stm.executeQuery();
            while (rs.next()) {
                ordersDTO.setOrderId(rs.getString(1));
                ordersDTO.setUserId(rs.getString(2));
                ordersDTO.setStatus(rs.getInt(3));
                ordersDTO.setPaymentMethod(rs.getString(4));
                ordersDTO.setOrderTime(rs.getDate(5));
                ordersDTO.setTotalPrice(rs.getFloat(6));
                list.add(ordersDTO);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "Exception found on getAll(String userId) method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "Error closing PrepareStatement", e);
                }
            }
        }
        return list;
    } 
}
