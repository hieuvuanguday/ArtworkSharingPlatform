/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.repositories.artworks;

import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The {@code ArtworkDTO} class represents a Data Transfer Object (DTO) for
 * artwork information within the OnlyArts application. It encapsulates details
 * such as artwork ID, owner ID, category ID, privacy level, name, description,
 * artwork image, price, quantity, release date, and status. Instances of this
 * class are used to transfer artwork data between different layers of the
 * application, providing a standardized format for artwork-related information.
 *
 * Usage Example:
 *
 * <pre>{@code
 * ArtworkDTO artwork = new ArtworkDTO();
 * artwork.setArtworkId("123");
 * artwork.setOwnerId("456");
 * artwork.setCateId("789");
 * artwork.setPrivacy(1);
 * artwork.setName("Artwork Name");
 * artwork.setDescription("Artwork Description");
 * artwork.setArtworkImage("/path/to/image.jpg");
 * artwork.setPrice(99.99f);
 * artwork.setQuantity(10L);
 * artwork.setReleasedDate(new Date(System.currentTimeMillis()));
 * artwork.setStatus("Active");
 * }</pre>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ArtworkDTO implements Serializable {

    /**
     * Unique identifier for the artwork.
     */
    private String artworkId;

    /**
     * Identifier of the owner (artist) of the artwork.
     */
    private String ownerId;

    /**
     * Identifier of the category to which the artwork belongs.
     */
    private String cateId;

    /**
     * Name of the artwork.
     */
    private String name;

    /**
     * Description providing additional details about the artwork.
     */
    private String description;

    /**
     * Path or URL to the image representing the artwork.
     */
    private String artworkImage;

    /**
     * Price of the artwork.
     */
    private float price;

    /**
     * Date when the artwork was released or created.
     */
    private Date releasedDate;

    /**
     * Status of the artwork (e.g., active, inactive).
     */
    private int status;
}
