/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.resources.v3;

import com.cowards.onlyarts.repositories.cart.CartDTO;
import com.cowards.onlyarts.services.CartDAO;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Path("/v3/cart")
public class EditCart {

    private static final CartDAO cartDao = CartDAO.getInstance();

    @POST
    @Path("/add/{artworkId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(@PathParam("artworkId") String artworkId, @CookieParam("cart") Cookie cookie) {
        try {
            String value = null;
            if (cookie != null) {
                value = cookie.getValue();
                System.out.println(value);
                if (!value.contains(artworkId)) {
                    if (!value.isEmpty()) {
                        value += "-";
                    }
                    value += artworkId;
                }
            } else {
                value = artworkId;
            }
            Cookie c = new Cookie("cart", value, "/", "") {
            };
            NewCookie cart = new NewCookie(c) {
            };
            CartDTO cartDTO = cartDao.get(value);
            return Response.ok(cartDTO).cookie(cart).build();
        } catch (Exception e) {
            return Response.ok(e).build();
        }
    }

    @POST
    @Path("/remove/{artworkId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("artworkId") String artworkId, @CookieParam("cart") Cookie cookie) {
        try {
            String value = null;
            if (cookie != null) {
                value = cookie.getValue();
                if (value.contains(artworkId)) {
                    String[] split = value.split("-");
                    String temp = "";
                    for (int i = 0; i < split.length; i++) {
                        if (!split[i].equals(artworkId)) {
                            temp += split[i];
                            if (i != (split.length - 1)) {
                                temp += "-";
                            }
                        }
                    }
                    value = temp;
                }
            }
            Cookie c = new Cookie("cart", value, "/", "") {
            };
            NewCookie cart = new NewCookie(c) {
            };
            CartDTO cartDTO = cartDao.get(value);
            return Response.ok(cartDTO).cookie(cart).build();
        } catch (Exception e) {
            return Response.ok(e).build();
        }
    }
}
