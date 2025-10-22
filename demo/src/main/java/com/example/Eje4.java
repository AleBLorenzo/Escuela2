package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Eje4 {

    public static void main(String[] args) {


        String ruta = "src/main/java/com/example/empleados.json";
       

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
        
            
        Gson gson = new GsonBuilder().create();
        Empresa empresa = gson.fromJson(br, Empresa.class);
      
   
        System.out.println("_____INFORMACION_____");

        for (Departamento dep  : empresa.getDepartamentos()) {
                for (Empleado empl : dep.getEmpleados()) {

                    System.out.println(empl);
                    
                }
                   
               }

               
        System.out.println("\nEmpleados con salario mayor de 50.000");

        for (Departamento dep  : empresa.getDepartamentos()) {
                for (Empleado empl : dep.getEmpleados()) {
                    if (empl.getSalario() > 50000 ) {
                        System.out.println(empl);
                    }
                    
                }
                   
               }

               
        System.out.println("\nSalario promedio por departamento");

        for (Departamento dep  : empresa.getDepartamentos()) {
            List<Empleado> lista = dep.getEmpleados();
            double suma = 0 ;
 
            for (Empleado emp : lista) {

                suma+= emp.getSalario();

                }
                double promedio = (suma/ lista.size()) *100.0 / 100.0;

                System.out.println(dep.getNombre()+ ": "+promedio);
               }

        } catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
            e.printStackTrace();
        }


        
    }



}
