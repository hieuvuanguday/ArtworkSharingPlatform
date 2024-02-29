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
    private String owerId;
    private String cateId;
    private boolean privacy;
    private String name;
    private String description;
    private String img;
    private double price;
    private int quantity;
    private Date releasedDate;
    private int status;
}
