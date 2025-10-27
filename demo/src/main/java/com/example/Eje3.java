package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.JSONArray;
import org.json.JSONObject;

public class Eje3 {

    public static void main(String[] args) {

        String ruta = "src/main/java/com/example/biblioteca.json";
        String Nruta = "src/main/java/com/example/biblioteca_modificado.json";
        StringBuilder contenido = new StringBuilder();

        try (BufferedReader read = new BufferedReader(new FileReader(ruta))) {

            String linea;

            while ((linea = read.readLine()) != null) {
                contenido.append(linea);

            }

            JSONObject principal = new JSONObject(contenido.toString());
            JSONArray libros = principal.getJSONArray("libros");

            JSONObject preestado = libros.getJSONObject(0);
            preestado.put("prestado", true);
            System.out.println("campo prestado cambiado a 'true'");

            JSONObject reseña = new JSONObject();
            reseña.put("usuario", "Jose344");
            reseña.put("comentario", "Excelente");
            reseña.put("puntuacion", 4.4);

            JSONObject Slibro = libros.getJSONObject(1);
            JSONArray reseñas = new JSONArray();
            Slibro.put("reseñas", reseñas);
            reseñas.put(reseña);

            System.out.println("campo reseña añadido");

            JSONObject Nlibro = new JSONObject();
            Nlibro.put("isbn", "859-85-745-6325-4");
            Nlibro.put("titulo", "cencienta");
            Nlibro.put("autor", "alguien");
            Nlibro.put("año_publicacion", 1968);
            Nlibro.put("genero", "Fantasia");
            Nlibro.put("prestado", true);
            Nlibro.put("valoracion", 3.9);

            JSONArray Nreseña = new JSONArray();
            Nreseña.put(reseña);
            Nlibro.put("reseñas", Nreseña);

            libros.put(Nlibro);

            System.out.println("nuevo libro agregado");

            principal.put("ubicacion", "barcelona");

            System.out.println("ubicacion cambiada");

            int totallibros = libros.length();
            int librosPrestados = 0;
            double SumaValores = 0.0;

            for (int i = 0; i < totallibros; i++) {
                JSONObject libro = libros.getJSONObject(i);
                if (libro.getBoolean("prestado")) {
                    librosPrestados++;
                }

                SumaValores += libro.getDouble("valoracion");
            }

            double promedio = Math.round((SumaValores / totallibros) * 100.0) / 100.0;

            JSONObject estadisticas = new JSONObject();

            estadisticas.put("total_libros", totallibros);
            estadisticas.put("libros_prestados", librosPrestados);
            estadisticas.put("valoracion_promedio", promedio);

            principal.put("estadisticas", estadisticas);

            System.out.println("Estadisticas añadidas");

            try (BufferedWriter write = new BufferedWriter(new FileWriter(Nruta))) {

                write.write(principal.toString(4));

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();

        }

    }

}
