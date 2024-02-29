/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.models.favorites;

import com.cowards.onlyarts.utils.DBContext;
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
 * @author dell
 */
public class FavoriteDAO {

    private static final String GET_FAVORITE = "SELECT * FROM Users_favor WHERE user_id = ?";
    private static final String ADD_FAVORITE = "INSERT INTO Users_favor (user_id, artwork_id) VALUES (?, ?);";

    public List<FavoriteDTO> getFavorite(String userId) {
        List<FavoriteDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_FAVORITE);
                ptm.setString(1, userId);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String artworkId = rs.getString("artwork_id");
                    list.add(new FavoriteDTO(userId, artworkId));
                }
            }
        } catch (SQLException e) {
            logError("Exception found on getFavorite() method", e);
        } finally {
            DBContext.closeResultSet(rs);
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return list;
    }

    public boolean addNewFavorite(String userId, String artworkId) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_FAVORITE);
                ptm.setString(1, userId);
                ptm.setString(2, artworkId);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Exception found on addNewFavorite() method", e);
        } finally {
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return check;
    }

    private void logError(String message, Throwable e) {
        Logger.getLogger(FavoriteDAO.class.getName()).log(Level.SEVERE,
                message, e);
    }
}
