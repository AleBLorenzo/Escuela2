package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Main {
    public static void main(String[] args) throws IOException {
        RegistroInvetario nuevo = new RegistroInvetario("si q mola", 1, 2.5);
          RegistroInvetario nuevo1 = new RegistroInvetario("si q mola", 1, 2.5);
            RegistroInvetario nuevo2 = new RegistroInvetario("si q mola", 1, 2.5);




       serializarAJson(nuevo , "datos.json");
 
      deserializarDesdeJson("datos.json");


    }


    private static void  serializarAJson(RegistroInvetario objeto , String ubi) throws IOException{

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(objeto);
       try (BufferedWriter guardar = new BufferedWriter(new FileWriter(ubi,true))) {
        
            guardar.write(json);
    
        }
       
    }

    private static void deserializarDesdeJson(String ubi){

        Gson gson = new GsonBuilder().create();
        try (BufferedReader br = new BufferedReader(new FileReader(ubi))) {

          RegistroInvetario datos = gson.fromJson(br, RegistroInvetario.class);
          System.out.println(datos.toString());

        } catch (Exception e) {
           
        }

        
    }
}