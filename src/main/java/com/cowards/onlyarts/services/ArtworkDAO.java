/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.services;

import com.cowards.onlyarts.repositories.artworks.ArtworkDTO;
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
 * @author Admin
 */
public class ArtworkDAO {

    private static final String GETONE = "SELECT * FROM Artworks WHERE artwork_id = ?";
    private static final String UPDATE = "UPDATE Artworks SET cate_id = ?, privacy = ?, name = ?, description = ?, "
            + "artwork_image = ?, price = ?, status = ? WHERE artwork_id = ?";
    private static final String DELETE = "DELETE FROM Artworks WHERE artwork_id = ?";
    private static final String GETALL = "SELECT * FROM Artworks";

    private static ArtworkDAO instance;

    private final Connection conn = DBContext.getInstance();

    private ArtworkDAO() {
    }

    public static ArtworkDAO getInstance() {
        if (instance == null) {
            instance = new ArtworkDAO();
        }
        return instance;
    }

    public ArtworkDTO getOne(String id) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArtworkDTO artworkDTO = null;
        try {
            stm = conn.prepareStatement(GETONE);
            stm.setString(1, id);
            rs = stm.executeQuery();
            artworkDTO = new ArtworkDTO();
            while (rs.next()) {
                artworkDTO.setArtworkId(rs.getString(1));
                artworkDTO.setOwnerId(rs.getString(2));
                artworkDTO.setCateId(rs.getString(3));
                artworkDTO.setPrivacy(rs.getInt(4));
                artworkDTO.setName(rs.getString(5));
                artworkDTO.setDescription(rs.getString(6));
                artworkDTO.setArtworkImage(rs.getBytes(7));
                artworkDTO.setPrice(rs.getFloat(8));
                artworkDTO.setReleasedDate(rs.getTimestamp(9));
                artworkDTO.setStatus(rs.getString(10));
            }
        } catch (SQLException e) {
            Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Exception found on getOne(String id) method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Error closing PreparedStatement", e);
                }
            }
        }
        return artworkDTO;
    }

    public void update(ArtworkDTO artworkDTO) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(UPDATE);
            stm.setString(1, artworkDTO.getCateId());
            stm.setInt(2, artworkDTO.getPrivacy());
            stm.setString(3, artworkDTO.getName());
            stm.setString(4, artworkDTO.getDescription());
            stm.setBytes(5, artworkDTO.getArtworkImage());
            stm.setFloat(6, artworkDTO.getPrice());
            stm.setString(7, artworkDTO.getStatus());
            stm.setString(8, artworkDTO.getArtworkId());
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Exception found on update(ArtworkDTO artworkDTO) method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Error closing PreparedStatement", e);
                }
            }
        }
    }

    public void delete(String artworkId) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(DELETE);
            stm.setString(1, artworkId);
            stm.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Exception found on delete(String artworkId) method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Error closing PrepareStatement", e);
                }
            }
        }
    }

    public List getAll() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ArtworkDTO> list = new ArrayList<>();
        ArtworkDTO artworkDTO = new ArtworkDTO();
        try {
            stm = conn.prepareStatement(GETALL);
            rs = stm.executeQuery();
            while (rs.next()) {
                artworkDTO.setArtworkId(rs.getString(1));
                artworkDTO.setOwnerId(rs.getString(2));
                artworkDTO.setCateId(rs.getString(3));
                artworkDTO.setPrivacy(rs.getInt(4));
                artworkDTO.setName(rs.getString(5));
                artworkDTO.setDescription(rs.getString(6));
                artworkDTO.setArtworkImage(rs.getBytes(7));
                artworkDTO.setPrice(rs.getFloat(8));
                artworkDTO.setReleasedDate(rs.getTimestamp(9));
                artworkDTO.setStatus(rs.getString(10));
                list.add(artworkDTO);
            }
        } catch (SQLException e) {
            Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Exception found on count() method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Error closing PrepareStatemt", e);
                }
            }
        }
        return list;
    }

    public List getAll(String userId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ArtworkDTO> list = new ArrayList<>();
        ArtworkDTO artworkDTO = new ArtworkDTO();
        try {
            stm = conn.prepareStatement(GETALL + "WHERE owner_id = ?");
            stm.setString(1, userId);
            rs = stm.executeQuery();
            while (rs.next()) {
                artworkDTO.setArtworkId(rs.getString(1));
                artworkDTO.setOwnerId(rs.getString(2));
                artworkDTO.setCateId(rs.getString(3));
                artworkDTO.setPrivacy(rs.getInt(4));
                artworkDTO.setName(rs.getString(5));
                artworkDTO.setDescription(rs.getString(6));
                artworkDTO.setArtworkImage(rs.getBytes(7));
                artworkDTO.setPrice(rs.getFloat(8));
                artworkDTO.setReleasedDate(rs.getTimestamp(9));
                artworkDTO.setStatus(rs.getString(10));
                list.add(artworkDTO);
            }
        } catch (SQLException e) {
            Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Exception found on count(String userId) method", e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, "Error closing PrepareStatemt", e);
                }
            }
        }
        return list;
    }

}
