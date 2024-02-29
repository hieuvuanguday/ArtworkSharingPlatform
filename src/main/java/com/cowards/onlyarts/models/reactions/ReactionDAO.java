/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.models.reactions;

import com.cowards.onlyarts.models.favorites.FavoriteDAO;
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
public class ReactionDAO {
    private static final String GET_REACTION = "SELECT * FROM Artwork_reactions WHERE artwork_id = ?";
    private static final String ADD_REACTION = "INSERT INTO Artwork_reactions (artwork_id, user_id) VALUES (?, ?);";

    public List<ReactionDTO> getReaction(String artworkId) {
        List<ReactionDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_REACTION);
                ptm.setString(1, artworkId);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String userId = rs.getString("user_id");
                    list.add(new ReactionDTO(artworkId, userId));
                }
            }
        } catch (SQLException e) {
            logError("Exception found on getReaction() method", e);
        } finally {
            DBContext.closeResultSet(rs);
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return list;
    }

    public boolean addNewReaction(String artworkId, String userId) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_REACTION);
                ptm.setString(1, artworkId);
                ptm.setString(2, userId);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Exception found on addNewReaction() method", e);
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
