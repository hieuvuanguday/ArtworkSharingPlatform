package com.cowards.onlyarts.models.users;

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

public class UserDAO {

    private static final String GET_USER_REACT = "SELECT ar.[user_id],[role_id],[first_name],[last_name],[avatar],[phone],[email],[address],[join_date],[bio],[status],[password]\n" +
                                             "FROM Artwork_reactions ar \n" +
                                             "LEFT JOIN Users u on ar.user_id = u.user_id \n" +
                                             "WHERE artwork_id = ?";
    
    public static List<UserDTO> getUserReaction(String artworkId) {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER_REACT);
                ptm.setString(1, artworkId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userId = rs.getString("user_id");
                    String roleId = rs.getString("role_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String avatar = rs.getString("avatar");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    Date joinDate = rs.getDate("join_date");
                    String bio = rs.getString("bio");
                    int status = rs.getInt("status");
                    String password = "***";
                    list.add(new UserDTO(userId, roleId, firstName, lastName, avatar, phone, email, address, joinDate, bio, status, password));
                }
            }
        } catch (SQLException e) {
            logError("Exception found on getUserReaction() method", e);
        } finally {
            DBContext.closeResultSet(rs);
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return list;
    }    
    
    private static void logError(String message, Throwable e) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,
                message, e);
    }
}
