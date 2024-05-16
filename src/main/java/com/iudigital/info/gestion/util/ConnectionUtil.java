/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.info.gestion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kevin
 */
public class ConnectionUtil {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/info-gestion-bd"; 
    private static final String USER = "postgres";    
    private static final String PASSWORD = "psssword123";
    
    public static Connection getConnection()throws SQLException{
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
        
    }
}
