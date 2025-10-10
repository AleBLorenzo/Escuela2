package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Eje1 {
    public static void main(String[] args) throws IOException {

        String ruta = "src/main/java/com/example/productos.json";
        StringBuilder contenido = new StringBuilder();

        try (BufferedReader read = new BufferedReader(new FileReader(ruta))) {

            String linea;

            while ((linea = read.readLine()) != null) {

                contenido.append(linea);

            }

            JSONObject json = new JSONObject(contenido.toString());

            String tienda = json.getString("tienda");
            System.out.println("Tienda: " + tienda);

            JSONArray array = json.getJSONArray("productos");
            System.out.println("Total de productos: " + array.length());

            for (int i = 0; i < array.length(); i++) {
                JSONObject producto = array.getJSONObject(i);

                System.out.println("--------------------");
                System.out.println("Producto: " + (i + 1));
                System.out.println("ID: " + producto.getInt("id"));
                System.out.println("Nombre: " + producto.getString("nombre"));
                System.out.println("Categoria: " + producto.getString("categoria"));
                System.out.println("Precio: " + producto.getDouble("precio"));
                System.out.println("Disponible: " + producto.getBoolean("disponible"));

                JSONObject espec = producto.getJSONObject("especificaciones");
                System.out.println("Especificaiones: ");
                for (String s : espec.keySet()) {
                    System.out.println("- " + s + ": " + espec.getString(s));

                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
        } catch (org.json.JSONException ex) {
            System.out.println("Error al parsear JSON: " + ex.getMessage());
        }

    }
}