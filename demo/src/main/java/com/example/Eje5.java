package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.text.DateFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

public class Eje5 {

    public static void main(String[] args) {

        String direccion = "src/main/java/com/example/plataforma_educativa.json";

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        

       

        Curso cus1 = new Curso(true, "Curso de HTML, CSS y JS", LocalDate.parse("2025-01-19"), LocalDate.parse("2024-09-01"), 001, "Jose Andres", "Desarrollo Web", 219.99);
         Curso cus2 = new Curso(false, "Curso de Java", LocalDate.parse("2025-07-01"), LocalDate.parse("2025-01-21"), 002, "Pedro Alcala", "Programacion", 259.99);

         Estudiante e1 = new Estudiante("alejo@gmail.com", LocalDate.parse("2005-05-25"), 001,"Alejandro", "Cuba");
         Estudiante e2 = new Estudiante("pedro@gmail.com", LocalDate.parse("2005-07-05"), 002,"Pedro", "España");
         Estudiante e3 = new Estudiante("juan@gmail.com", LocalDate.parse("2006-09-02"), 003,"Juan", "España");

         Calificacion c1 = new Calificacion(001, "Muy bien", LocalDate.parse("2025-01-30"), 9.6);
         Calificacion c2 = new Calificacion(003, "Mal planteado", LocalDate.parse("2025-01-30"), 5.1);
         Calificacion c3 = new Calificacion(002, "Bien", LocalDate.parse("2025-01-31"), 7.9);
         Calificacion c4 = new Calificacion(003, "Se puede Mejorar", LocalDate.parse("2025-02-25"),6.0);

        PlataformaEducativa pe = new PlataformaEducativa(List.of(c1 ,c2,c3,c4),List.of(cus1,cus2),List.of(e1, e2, e3), "EVGD");

           JsonSerializer<LocalDate> serializer = (src, typeOfSrc, context) ->
                new JsonPrimitive(src.format(formato));
       

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, serializer).setPrettyPrinting().create();

         String json = gson.toJson(pe);

         int total_cursos = pe.getCursos().size();
         int total_estudiantes = pe.getEstudiantes().size();
         double suma = 0 ;

         for (Calificacion c  : pe.getCalificaciones()) {
            suma+= c.getNota();
             
         }

         double promedio = suma / pe.getCalificaciones().size();

         try (BufferedWriter bw = new BufferedWriter (new FileWriter(direccion))) {
            
            bw.write(json);

            System.out.println("\nArchivo Json generado");
            System.out.println("\nTotal de cursos: "+ total_cursos);
            System.out.println("Total de estudiantes: "+ total_estudiantes);
               System.out.println("Promedio general clasificaiones: "+ promedio);
             
         } catch (Exception e) {
            e.printStackTrace();
         }

    }

}
