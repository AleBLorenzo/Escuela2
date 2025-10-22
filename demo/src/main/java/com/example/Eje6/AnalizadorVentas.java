package com.example.Eje6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

public class AnalizadorVentas {

    private static final String ruta = "src/main/java/com/example/Eje6/ventas_historicas.json";

    public static void main(String[] args) throws IOException {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String json = "";
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea = "";

            while ((linea = br.readLine()) != null) {

                json += linea;

            }

        } catch (FileNotFoundException ex) {

        }

        JsonSerializer<OffsetDateTime> serializer = (src, typeOfSrc,
                context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        JsonDeserializer<OffsetDateTime> deserializer = (jsonElement, typeOfT, context) -> OffsetDateTime
                .parse(jsonElement.getAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, serializer)
                .registerTypeAdapter(OffsetDateTime.class, deserializer)
                .setPrettyPrinting()
                .create();

        SistemaVentas sv = gson.fromJson(json, SistemaVentas.class);

        for (Region region : sv.getRegion()) {

            for (Venta venta : region.getVentas()) {

                System.out.println(venta.getTotal());
            }

        }

        System.out.println(sv);

    }

}
