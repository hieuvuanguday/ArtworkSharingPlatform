package com.cowards.onlyarts.services;

import com.cowards.onlyarts.repositories.users.UserDTO;
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
public class UserDAO {

    private static final String GETALL = "SELECT * FROM Users";

    private final Connection conn = DBContext.getInstance();

    private static UserDAO instance;

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public List<UserDTO> getAll(String roleId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDTO userDTO = new UserDTO();
        List<UserDTO> list = new ArrayList<>();
        try {
            stm = conn.prepareStatement(GETALL + "WHERE role_id = ?");
            stm.setString(1, roleId);
            rs = stm.executeQuery();
            while (rs.next()) {
                userDTO.setUserId(rs.getString(1));
                userDTO.setRoleId(rs.getString(2));
                userDTO.setFirstName(rs.getString(3));
                userDTO.setLastName(rs.getString(4));
                userDTO.setPhone(rs.getString(5));
                userDTO.setEmail(rs.getString(6));
                userDTO.setAddress(rs.getString(7));
                userDTO.setJoinDate(rs.getDate(8));
                userDTO.setBio(rs.getString(9));
                userDTO.setStatus(rs.getInt(10));
                userDTO.setPassword(rs.getString(11));
                list.add(userDTO);
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Exception found on count() method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Error closing PrepareStatement", e);
                }
            }
        }
        return list;
    }

}
