package com.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
       
        String URL = "jdbc:mysql://localhost:3306/db-1";

        Connection con = DriverManager.getConnection(URL);

        DatabaseMetaData MeTa = con.getMetaData();

        System.out.println(MeTa.getDatabaseProductName());
        
    }
}