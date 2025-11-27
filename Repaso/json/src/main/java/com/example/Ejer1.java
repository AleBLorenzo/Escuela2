package com.example;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;



public class Ejer1 {

    public static void main(String[] args) {
        
        try (BufferedReader rd = new BufferedReader(new FileReader("json/src/main/resources/evento.json"))) {
       
            String linea = "";
            String dato;
            while ((dato = rd.readLine()) != null) { 

                linea += dato;
            }


            JSONObject datos = new JSONObject(linea);
            System.out.println(datos.toString());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        


    }
}
