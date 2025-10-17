package com.example.Eje6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class AnalizadorVentas {

    private static final String ruta = "src/main/java/com/example/Eje6/ventas_historicas.json";

    public static void main(String[] args) throws IOException {

      
          String json = "";

        try(BufferedReader br = new BufferedReader(new FileReader(ruta))) {
          
            String linea = "";

            while ((linea = br.readLine())!=null) {
                json+= linea;
            }
       
        } catch (Exception ex) {
            System.out.println("Error: "+ ex);
        }


        JsonSerializer<LocalDateTime> serializer = (src, typeOfSrc, context) ->
        new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        JsonDeserializer<LocalDateTime> deserializer = (jsonElement, typeOfT, context) ->
        LocalDateTime.parse(jsonElement.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, serializer)
        .registerTypeAdapter(LocalDateTime.class, deserializer)
        .setPrettyPrinting()
        .create();

    SistemaVentas sv = gson.fromJson(json, SistemaVentas.class);

    double totalEuropa = 0.0;
   
    for (Region regions :sv.getRegion()) {
        if (regions.getNombre().equals("Europa")) {
             for (Venta venta : regions.getVentas()) {
            totalEuropa+= venta.getTotal();               
        }      
        }
       
    }

    double totalAmerica = 0.0;
    for (Region regions : sv.getRegion()) {
        if (regions.getNombre().equals("America del Norte")) {
            for (Venta venta : regions.getVentas()) {
                totalAmerica+=venta.getTotal();
                
            }
        }
        
    }

        double totalAsia = 0.0;
    for (Region regions : sv.getRegion()) {
        if (regions.getNombre().equals("Asia")) {
            for (Venta venta : regions.getVentas()) {
                totalAsia+=venta.getTotal();
                
            }
        }
        
    }

          double totalSuda = 0.0;
    for (Region regions : sv.getRegion()) {
        if (regions.getNombre().equals("Sudamerica")) {
            for (Venta venta : regions.getVentas()) {
                totalSuda+=venta.getTotal();
                
            }
        }
        
    }
       System.out.println("Total en Europa: "+ totalEuropa);
       System.out.println("Total en America del  Norte: "+ totalAmerica);
       System.out.println("Total en Asia: "+ totalAsia);
       System.out.println("Total en Sudamerica: "+ totalSuda);


           for (Region regions : sv.getRegion()) {
       
            for (Venta venta : regions.getVentas()) {
               
            String nombreV = venta.getVendedor().getNombre();
            double totalV = venta.getTotal();

            System.out.println("\nVendedor: "+nombreV +" - "+totalV);
                
        }

        
    }

    
        double dolarEuro = totalAmerica *0.86;
        double dolarYan = totalAsia * 0.00570;
        double dolarArg = totalSuda *0.00059;

        System.out.println("\nConvertir moneda");
        System.out.println("Ganancias de America(USD) a EUR: "+dolarEuro);
        System.out.println("Ganancias de Asia(JPY) a EUR: "+dolarYan);
        System.out.println("Ganancias de Sudamerica(ARG) a EUR: "+dolarArg);


    }

}
