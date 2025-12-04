package com.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
       

        String URL = "jdbc:mysql://74.208.70.10:3306/AccesoDatos";
            String user = "root";
    String password = "1234";

        Connection con = DriverManager.getConnection(URL ,user,password);

        DatabaseMetaData MeTa = con.getMetaData();

        System.out.println(MeTa.getDatabaseProductName());
        
    }
}