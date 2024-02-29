package com.cowards.onlyarts.models.artworks;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ArtworkERROR {
    private String artworkIdError;
    private String ownerIdError;
    private String cateIdError;
    private boolean privacyError;
    private String nameError;
    private String descriptionError;
    private String artworkImageError;
    private float priceError;
    private int quantityError;
    private Date releaseDateError;
    private String statusError;
}
