package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Ejer1 {

    public static void main(String[] args) {
        
  try (BufferedReader rd = new BufferedReader(new FileReader("datos_deportivos.json"))) {
       
            String linea = "";
            String dato;
            while ((dato = rd.readLine()) != null) { 

                linea += dato;
            }


            int contadorpartidos= 0 ;

            JSONObject datos = new JSONObject(linea);
            System.out.println(datos.getString("evento"));
            System.out.println(datos.getInt("temporada"));

            JSONArray lista = datos.getJSONArray("jornadas");

            int contador = 0;
            for (int i = 0; i < lista.length(); i++) {
                contador++;
                JSONObject jornada = lista.getJSONObject(i); 
                 JSONArray listapartidos = jornada.getJSONArray("partidos");

                  for (int j = 0; j < listapartidos.length(); j++) {
                      JSONObject partidos = listapartidos.getJSONObject(i);

              
                
                if (partidos.getBoolean("es_empate") == true) {
                    contadorpartidos++;
                }
              }

            }



       
                       System.out.println("Total de Jornadas "+ contador);
            System.out.println("Empataron "+ contadorpartidos);

           String datos22 = datos.toString();
            
            try (BufferedWriter wr = new BufferedWriter(new FileWriter("datos_sssdeportivos.json"))) {
                wr.write(datos22);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        
            
 
            
           
    }
}
