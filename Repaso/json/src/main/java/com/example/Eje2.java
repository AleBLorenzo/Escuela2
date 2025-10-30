package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Eje2 {

    public static void main(String[] args) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader rd = new BufferedReader(
                new FileReader("json/src/main/resources/empleados_proyectos.json"))) {

            Gson builder = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class,
                            (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> LocalDate
                                    .parse(json.getAsString(), formato))
                    .registerTypeAdapter(LocalDate.class,
                            (JsonSerializer<LocalDate>) (date, typeOfSrc,
                                    context) -> new JsonPrimitive(date.format(formato)))
                    .setPrettyPrinting().create();

            Empresa empresa = builder.fromJson(rd, Empresa.class);

            List<String> datoss = new ArrayList<>();
            datoss.add("Hola");
            datoss.add("q tal");

            Empleado empleadoN = new Empleado(true, "email", LocalDate.parse("2020-03-15"), datoss, 1, "nombre", 200.5);

            System.out.println(empresa.getEmpresa().toString());
            System.out.println(empresa.getUbicacion().toString());

            for (Departamento datos : empresa.getDepartamentos()) {

                System.out.println(datos.getNombre().toString());
                datos.getEmpleados().add(empleadoN);

                for (Proyecto proyectos : datos.getProyectos()) {

                    System.out.println(proyectos.getCodigo().toString());
                    System.out.println(proyectos.getNombre().toString());
                    System.out.println(proyectos.getFechaInicio().toString());
                    System.out.println(proyectos.getFechaFin().toString());

                }

                for (Empleado empleados : datos.getEmpleados()) {
                    if (empleados.isActivo() == true) {

                        System.out.println(empleados.getId());
                        System.out.println(empleados.getNombre().toString());
                        System.out.println(empleados.getEmail().toString());
                        System.out.println(empleados.getSalario());
                        System.out.println(empleados.getFechaContratacion());
                        System.out.println(empleados.isActivo());

                        for (String habilidades : empleados.getHabilidades()) {

                            System.out.println(habilidades.toString());
                        }
                    }

                }

            }

            try (FileWriter fw = new FileWriter("json/src/main/resources/empleados_modificado.json")) {
                builder.toJson(empresa, fw);
                System.out.println("JSON modificado guardado correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
