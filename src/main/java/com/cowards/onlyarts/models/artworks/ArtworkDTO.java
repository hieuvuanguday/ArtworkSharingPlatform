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
public class ArtworkDTO {
    private String artworkId;
    private String ownerId;
    private String cateId;
    private String name;
    private String description;
    private String artworkImage;
    private float price;
    private int quantity;
    private Date releaseDate;
    private int status;
}
