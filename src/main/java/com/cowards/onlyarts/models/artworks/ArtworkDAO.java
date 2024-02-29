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

    private static ArtworkDAO instance;
    private static final String GETFAVORITEARTWORK = " SELECT [dbo].[Users_favor].[artwork_id],[owner_id],"
            + "[cate_id],[privacy],[name],[description],[artwork_image],[price],[quantity],[released_date],[status] "
            + "FROM [OnlyArts].[dbo].[Users_favor] RIGHT JOIN [OnlyArts].[dbo].[Artworks]  "
            + "ON [dbo].[Users_favor].[artwork_id] = [OnlyArts].[dbo].[Artworks].[artwork_id] "
            + "WHERE [OnlyArts].[dbo].[Users_favor].[user_id] = ?";

    public static ArtworkDAO getInstance() {
        if (instance == null) {
            instance = new ArtworkDAO();
        }
        return instance;
    }

    public List<ArtworkDTO> getFavoriteArtwork(String userId) {
        List<ArtworkDTO> favoriteArtwork = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETFAVORITEARTWORK);
                ptm.setString(1, userId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String artworkId = rs.getString("artwork_id");
                    String ownerId = rs.getString("owner_id");
                    String cateId = rs.getString("cate_id");
                    boolean privacy = rs.getBoolean("privacy");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String img = rs.getString("artwork_image");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    Date releasedDate = rs.getDate("released_date");
                    int status = rs.getInt("status");
                    favoriteArtwork.add(new ArtworkDTO(artworkId, ownerId, cateId, privacy, 
                            name, description, img, price, quantity, releasedDate, status));
                }
            }
        } catch (SQLException e) {
            logError("Error at getFavoriteArtwork() method of ArtworkDAO class", e);
            
        } finally {
            DBContext.closeResultSet(rs);
            DBContext.closePreparedStatement(ptm);
            DBContext.closeConnection(conn);
        }
        return favoriteArtwork;
    }
    
    private void logError(String message, Throwable ex){
        Logger.getLogger(ArtworkDAO.class.getName()).log(Level.SEVERE, message, ex);
    }
}
