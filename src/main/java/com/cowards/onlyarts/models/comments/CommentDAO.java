/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.models.comments;

import com.cowards.onlyarts.utils.DBContext;
import java.sql.Connection;
import java.sql.Date;
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
public class CommentDAO {

    private static final String GET_COMMENT = "SELECT * FROM Comments WHERE artwork_id = ?";
    private static final String ADD_COMMENT = "INSERT INTO Comments (artwork_id, commenter_id, description, comment_time) VALUES (?, ?, ?, ?);";

    public List<CommentDTO> getDetailComment(String artworkId) {
        List<CommentDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_COMMENT);
                ptm.setString(1, artworkId);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String commenterId = rs.getString("commenter_id");
                    String description = rs.getString("description");
                    Date comment_time = rs.getDate("comment_time");
                    list.add(new CommentDTO(artworkId, commenterId, description, comment_time));
                }
            }
        } catch (SQLException e) {
            logError("Exception found on getDetailComment() method", e);
        } finally {
            DBContext.closeResultSet(rs);
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return list;
    }

    public boolean addNewComment(String userId, String artworkId, String description, Date time) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_COMMENT);
                ptm.setString(1, artworkId);
                ptm.setString(2, userId);
                ptm.setString(3, description);
                ptm.setDate(4, time);
                check = ptm.executeUpdate()> 0;
            }
        } catch (SQLException e) {
            logError("Exception found on addNewComment() method", e);
        } finally {
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return check;
    }

    private void logError(String message, Throwable e) {
        Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE,
                message, e);
    }
}
