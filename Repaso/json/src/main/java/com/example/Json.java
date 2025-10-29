package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.ObjDoubleConsumer;

import org.json.JSONArray;
import org.json.JSONObject;

public class Json {

    private static final String RUTA = "json/src/main/resources/inventario.json";

    public static void main(String[] args) throws IOException {

        String linea = "";
        int contador = 0 ;
        int productosDisponibles = 0;
        float precioTotal = 0;
        float precioPromedio = 0;
        int stockactual = 0 ; 

        try (BufferedReader rd = new BufferedReader(new FileReader(RUTA))) {

            String dato = "";

            while ((dato = rd.readLine()) != null) {

                linea += dato;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONObject objetos = new JSONObject(linea);

        String nombre = objetos.getString("inventario");

        JSONArray array = objetos.getJSONArray("productos");
        contador = array.length();

        System.out.println("Nombre : " + nombre);
        System.out.println("Cantidad Total de Libros : " + contador);

        boolean existe = false;

        for (int i = 0; i < array.length(); i++) {

            JSONObject productos = array.getJSONObject(i);
           
                 int id = productos.getInt("id");
            String nombreP = productos.getString("nombre");
            int stock = productos.getInt("stock");
            float precio = productos.getFloat("precio");
            boolean disponible = productos.getBoolean("disponible");
           
           
            if (productos.optBoolean("disponible") ) {
                productosDisponibles++;
                
            }

            stockactual +=stock;

            precioPromedio +=precio;

           
            

            System.out.println("\nID : " + id);
            System.out.println("Nombre Producto: " + nombreP);
            System.out.println("Stock : " + stock);
            System.out.println("Precio: " + precio);
            System.out.println("Disponible : " + disponible);

               if (productos.getString("nombre").equals("Ratón USB")) {
                productos.remove("precio");
                productos.remove("nombre");
                productos.remove("stock");
                productos.remove("id");
                productos.remove("disponible");
                array.remove(i);
    
            }


            if (productos.has("id") && productos.getInt("id") == 2) {
                productos.put("stock" , 0);
                productos.put("disponible", false);
                
            }
         
    if (productos.optInt("id") == 4) {
        existe = true;
        break; 
    }


        }
         precioPromedio = precioPromedio / contador;

         if (!existe) {
            JSONObject nuevoProducto = new JSONObject();
        nuevoProducto.put("id", 4);
        nuevoProducto.put("nombre", "Audifonos");
        nuevoProducto.put("stock", 2);
        nuevoProducto.put("precio", 23.25);
        nuevoProducto.put("disponible", true);
        array.put(nuevoProducto);

            
        }
    
        


       
        JSONObject datos = new JSONObject();
        datos.put("total_productos", contador);
        datos.put("productos_disponibles", productosDisponibles);
        datos.put("precio_promedio", precioPromedio);
        datos.put("stock_total", stockactual);
       

        
        objetos.put("estadisticas",datos);

        try (BufferedWriter re = new BufferedWriter(new FileWriter(RUTA))) {
            re.write(objetos.toString(4));
            
        } catch (Exception e) {
        }

    }
}