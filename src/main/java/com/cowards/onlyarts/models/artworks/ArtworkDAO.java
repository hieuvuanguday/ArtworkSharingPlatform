package com.cowards.onlyarts.models.artworks;

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

public class ArtworkDAO {

    private static final String SEARCH_BY_TYPE = "SELECT aw.artwork_id, u.first_name, u.last_name, c.cate_name, aw.name, aw.description, aw.artwork_image, aw.price, aw.quantity, aw.released_date, aw.status FROM Artworks aw LEFT JOIN Users u on aw.owner_id = u.user_id LEFT JOIN Categories c on aw.cate_id = c.cate_id WHERE c.cate_name like ?";
    private static final String SEARCH_BY_NAME = "SELECT aw.artwork_id, u.first_name, u.last_name, c.cate_name, aw.name, aw.description, aw.artwork_image, aw.price, aw.quantity, aw.released_date, aw.status FROM Artworks aw LEFT JOIN Users u on aw.owner_id = u.user_id LEFT JOIN Categories c on aw.cate_id = c.cate_id WHERE aw.name like ?";
    private static final String GET_ARTWORK = "SELECT aw.artwork_id, u.first_name, u.last_name, c.cate_name, aw.name, aw.description, aw.artwork_image, aw.price, aw.quantity, aw.released_date, aw.status FROM Artworks aw LEFT JOIN Users u on aw.owner_id = u.user_id LEFT JOIN Categories c on aw.cate_id = c.cate_id WHERE aw.artwork_id = ?";
    private static final String ADD_ARTWORK = "INSERT INTO Artworks (artwork_id, owner_id, cate_id, name, description, artwork_image, price, quantity, released_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_ALL_ARTWORKS = "SELECT aw.artwork_id, u.first_name, u.last_name, c.cate_name, aw.name, aw.description, aw.artwork_image, aw.price, aw.quantity, aw.released_date, aw.status FROM Artworks aw LEFT JOIN Users u on aw.owner_id = u.user_id LEFT JOIN Categories c on aw.cate_id = c.cate_id";
    
    public List<ArtworkDTO> getListArtworkWithType(String typeInput) {
        List<ArtworkDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_TYPE);
                ptm.setString(1, "%" + typeInput + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String artworkId = rs.getString("artwork_id");
                    String ownerId = rs.getString("first_name").concat(" ").concat(rs.getString("last_name"));
                    String cateId = rs.getString("cate_name");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String artworkImage = rs.getString("artwork_image");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date releaseDate = rs.getDate("released_date");
                    int status = rs.getInt("status");
                    list.add(new ArtworkDTO(artworkId, ownerId, cateId, name, description, artworkImage, price, quantity, releaseDate, status));
                }
            }
        } catch (SQLException e) {
            logError("Exception found on getListArtworkWithType() method", e);
        } finally {
            DBContext.closeResultSet(rs);
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return list;
    }

    public List<ArtworkDTO> getListArtworkWithName(String titleInput) {
        List<ArtworkDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BY_NAME);
                ptm.setString(1, "%" + titleInput + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String artworkId = rs.getString("artwork_id");
                    String ownerId = rs.getString("first_name").concat(" ").concat(rs.getString("last_name"));
                    String cateId = rs.getString("cate_name");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String artworkImage = rs.getString("artwork_image");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date releaseDate = rs.getDate("released_date");
                    int status = rs.getInt("status");
                    list.add(new ArtworkDTO(artworkId, ownerId, cateId, name, description, artworkImage, price, quantity, releaseDate, status));
                }
            }
        } catch (SQLException e) {
            logError("Exception found on getListArtworkWithName() method", e);
        } finally {
            DBContext.closeResultSet(rs);
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return list;
    }

    public List<ArtworkDTO> getDetailArtwork(String artworkId) {
        List<ArtworkDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ARTWORK);
                ptm.setString(1, artworkId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String ownerId = rs.getString("first_name").concat(" ").concat(rs.getString("last_name"));
                    String cateId = rs.getString("cate_name");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String artworkImage = rs.getString("artwork_image");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date releaseDate = rs.getDate("released_date");
                    int status = rs.getInt("status");
                    list.add(new ArtworkDTO(artworkId, ownerId, cateId, name, description, artworkImage, price, quantity, releaseDate, status));
                }
            }
        } catch (SQLException e) {
            logError("Exception found on getDetailArtwork() method", e);
        } finally {
            DBContext.closeResultSet(rs);
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return list;
    }

    public boolean addNewArtwork(ArtworkDTO artwork) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_ARTWORK);
                ptm.setString(1, artwork.getArtworkId());
                ptm.setString(2, artwork.getOwnerId());
                ptm.setString(3, artwork.getCateId());
                ptm.setString(4, artwork.getName());
                ptm.setString(5, artwork.getDescription());
                ptm.setString(6, artwork.getArtworkImage());
                ptm.setFloat(7, artwork.getPrice());
                ptm.setInt(8, artwork.getQuantity());
                ptm.setDate(9, artwork.getReleaseDate());
                ptm.setInt(10, artwork.getStatus());
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Exception found on addNewArtwork() method", e);
        } finally {
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return check;
    }

    public List<ArtworkDTO> getAllListArtwork() {
        List<ArtworkDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_ARTWORKS);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String artworkId = rs.getString("artwork_id");
                    String ownerId = rs.getString("first_name").concat(" ").concat(rs.getString("last_name"));
                    String cateId = rs.getString("cate_name");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String artworkImage = rs.getString("artwork_image");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date releaseDate = rs.getDate("released_date");
                    int status = rs.getInt("status");
                    list.add(new ArtworkDTO(artworkId, ownerId, cateId, name, description, artworkImage, price, quantity, releaseDate, status));
                }
            }
        } catch (SQLException e) {
            logError("Exception found on getAllListArtwork() method", e);
        } finally {
            DBContext.closeResultSet(rs);
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return list;
    }

    private static void logError(String message, Throwable e) {
        Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE,
                message, e);
    }
}
