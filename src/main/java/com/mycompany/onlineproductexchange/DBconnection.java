/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlineproductexchange;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class DBconnection {
    
    static final String DB_url="jdbc:mysql://localhost:3306/olx";
    static final String USER="root";
    static final String PASS="shruthi418";
    public static Connection connectDB(){
        Connection conn=null;
    try{
        
       Class.forName("com.mysql.cj.jdbc.Driver");
       conn=DriverManager.getConnection(DB_url,USER,PASS);
       return conn;
    }
    catch(Exception ex){
        System.out.println("There were errors while connecting to the database");
        return null;
    }
    }
}
