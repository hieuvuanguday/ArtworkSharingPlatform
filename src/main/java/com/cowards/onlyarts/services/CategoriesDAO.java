/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cowards.onlyarts.services;

import com.cowards.onlyarts.core.DBContext;
import java.sql.Connection;

/**
 *
 * @author Admin
 */
public class CategoriesDAO {

    private static CategoriesDAO instance;

    private final Connection conn = DBContext.getInstance();

    private CategoriesDAO() {
    }

    public static CategoriesDAO getInstance() {
        if (instance == null) {
            instance = new CategoriesDAO();
        }
        return instance;
    }

}
