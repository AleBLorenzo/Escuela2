package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

public class Eje2 {

    public static void main(String[] args) {

        String[] generos = { "Drama", "Terror", "Romance" };
        String[] generos2 = { "Drama", "Romance" };
        String[] generos3 = { "Romance" };

        File ruta = new File("src/main/java/com/example/datos.json");

            JSONObject objetos = new JSONObject();

            objetos.put("coleccion", "Mis Peliculas Favoritas");
            objetos.put("fecha_creacion", LocalDate.now());
            

            JSONArray lista = new JSONArray();

            JSONObject objetosl = new JSONObject();

            objetosl.put("id", 1);
            objetosl.put("titulo", "quijote");
            objetosl.put("director", "Sancho");
            objetosl.put("ano_lanzamiento", "1965");
            objetosl.put("genero", generos);
            objetosl.put("clasificacion", 8.2);

            JSONObject premios = new JSONObject();

            premios.put("oscar", true);
            premios.put("glovo:oro", false);
            premios.put("otros", 2);

            objetosl.put("premios", premios);

            lista.put(objetosl);

            JSONObject objetos2 = new JSONObject();

            objetos2.put("id", 2);
            objetos2.put("titulo", "sssss");
            objetos2.put("director", "Sanwwwcho");
            objetos2.put("ano_lanzamiento", "1ee965");
            objetos2.put("genero", generos2);
            objetos2.put("clasificacion", 7.5);

            JSONObject premios1 = new JSONObject();

            premios1.put("oscar", true);
            premios1.put("glovo:oro", false);
            premios1.put("otros", 2);

            objetos2.put("premios", premios1);

            lista.put(objetos2);

            JSONObject objetos3 = new JSONObject();

            objetos3.put("id", 2);
            objetos3.put("titulo", "sssss");
            objetos3.put("director", "Sanwwwcho");
            objetos3.put("ano_lanzamiento", "1ee965");
            objetos3.put("genero", generos3);
            objetos3.put("clasificacion", 7.5);

            JSONObject premios2 = new JSONObject();

            premios2.put("oscar", true);
            premios2.put("glovo:oro", false);
            premios2.put("otros", 2);

            objetos3.put( "premios",premios2);

            lista.put(objetos3);

            objetos.put("peliculas", lista);
            objetos.put("total_peliculas", lista.length());

            try (BufferedWriter Json = new BufferedWriter(new FileWriter(ruta))) {
                Json.write(objetos.toString(4));
                System.out.println("Se creo 'Datos.json' correctamente");

            } catch (Exception e) {
                System.out.println("Error: "+e);
            }

        System.out.println("Contenido");
        System.out.println(objetos.toString(4));
    }

}
