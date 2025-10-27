package com.example;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App {

    public static void main(String[] args) {
        try {
            System.out.println("Iniciando Sistema de Gestión de Cursos...\n");

            PlataformaEducativa plataforma = crearDatos();

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .excludeFieldsWithoutExposeAnnotation()
                    .registerTypeAdapter(Date.class, new DateAdapter())
                    .create();

            String json = gson.toJson(plataforma);

            guardarArchivo(json);
            mostrarJSON(json);
            mostrarEstadisticas(plataforma);

            System.out.println("\n¡Proceso completado exitosamente!");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static PlataformaEducativa crearDatos() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        PlataformaEducativa plataforma = new PlataformaEducativa("Academia Online Pro");

        Estudiante est1 = new Estudiante(1, "María González",
                "maria.gonzalez@email.com", sdf.parse("1995-03-15"), "España");
        Estudiante est2 = new Estudiante(2, "Juan Pérez",
                "juan.perez@email.com", sdf.parse("1998-07-22"), "México");
        Estudiante est3 = new Estudiante(3, "Ana Rodríguez",
                "ana.rodriguez@email.com", sdf.parse("2000-11-08"), "Argentina");

        plataforma.agregarEstudiante(est1);
        plataforma.agregarEstudiante(est2);
        plataforma.agregarEstudiante(est3);

        Curso curso1 = new Curso(101, "Programación Java Avanzada",
                "Curso completo de Java desde básico hasta Spring Boot",
                "Dr. Carlos Martínez",
                sdf.parse("2024-01-15"), sdf.parse("2024-04-15"),
                299.99, true);

        Curso curso2 = new Curso(102, "Desarrollo Web Full Stack",
                "Aprende HTML, CSS, JavaScript, React y Node.js",
                "Ing. Laura Fernández",
                sdf.parse("2024-02-01"), sdf.parse("2024-05-30"),
                399.99, true);

        curso1.agregarCalificacion(new Calificacion(1, 9.5,
                sdf.parse("2024-04-10"), "Excelente dominio de conceptos"));
        curso1.agregarCalificacion(new Calificacion(2, 8.7,
                sdf.parse("2024-04-12"), "Buen desempeño general"));
        curso1.agregarCalificacion(new Calificacion(3, 9.2,
                sdf.parse("2024-04-11"), "Muy buen trabajo en proyectos"));

        curso2.agregarCalificacion(new Calificacion(1, 8.9,
                sdf.parse("2024-05-28"), "Proyecto final destacado"));
        curso2.agregarCalificacion(new Calificacion(2, 9.3,
                sdf.parse("2024-05-29"), "Excelente creatividad en diseño"));

        plataforma.agregarCurso(curso1);
        plataforma.agregarCurso(curso2);

        return plataforma;
    }

    private static void guardarArchivo(String json) throws IOException {
        try (FileWriter fileWriter = new FileWriter("plataforma_educativa.json");
                BufferedWriter writer = new BufferedWriter(fileWriter)) {

            writer.write(json);
            System.out.println("✓ Archivo 'plataforma_educativa.json' creado exitosamente\n");
        }
    }

    private static void mostrarJSON(String json) {
        System.out.println("=".repeat(70));
        System.out.println("CONTENIDO DEL JSON GENERADO:");
        System.out.println("=".repeat(70));
        System.out.println(json);
        System.out.println();
    }

    private static void mostrarEstadisticas(PlataformaEducativa plataforma) {
        System.out.println("=".repeat(70));
        System.out.println("ESTADÍSTICAS DE LA PLATAFORMA");
        System.out.println("=".repeat(70));

        System.out.println("Plataforma: " + plataforma.getNombre());
        System.out.println("Total de cursos: " + plataforma.getCursos().size());
        System.out.println("Total de estudiantes: " + plataforma.getEstudiantes().size());

        double sumaNotas = 0;
        int totalCalificaciones = 0;

        System.out.println("\nDETALLE DE CURSOS:");
        for (Curso curso : plataforma.getCursos()) {
            System.out.println("\n  • " + curso.getNombre());
            System.out.println("Instructor: " + curso.getInstructor());
            System.out.println("Precio: $" + curso.getPrecio());
            System.out.println("Estado: " + (curso.isActivo() ? "Activo" : "Inactivo"));
            System.out.println("Calificaciones: " + curso.getCalificaciones().size());

            for (Calificacion cal : curso.getCalificaciones()) {
                sumaNotas += cal.getNota();
                totalCalificaciones++;
            }
        }

        double promedioGeneral = totalCalificaciones > 0 ? sumaNotas / totalCalificaciones : 0;

        System.out.println("\n" + "-".repeat(70));
        System.out.printf("Promedio general de calificaciones: %.2f/10.0%n", promedioGeneral);
        System.out.println("Total de calificaciones registradas: " + totalCalificaciones);
        System.out.println("=".repeat(70));

        System.out.println("\nNOTA DE SEGURIDAD:");
        System.out.println("   Los emails NO aparecen en el JSON");
        System.out.println("   gracias a @Expose(serialize = false)");
    }
}