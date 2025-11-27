package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        extraertxt();

        extraercsv();

        try  {
            
            BufferedReader rd = new BufferedReader(new FileReader("src/main/resources/productos.json"));

            JSONTokener to = new JSONTokener(rd);

            JSONArray datos = new JSONArray(to);

            for (int i = 0; i < datos.length(); i++) {
                
                 JSONObject obj = datos.getJSONObject(i);
                System.out.println(obj.getString("nombre"));
            }
            
    

            
        } catch (Exception e) {
        }


    }

    private static void extraertxt() {
        try (BufferedReader rd = new BufferedReader(new FileReader("src/main/resources/datos.txt"))) {
            
            ArrayList<String> contenido = new ArrayList<>();

            String line;
            int contador = 0;
            while ((line = rd.readLine()) != null) {

                contenido.add(line);

                contador++;

            }
            System.out.println("Número de líneas: " + contador);
            System.out.println("Contenido: ");

            for (int i = 0; i < contenido.size(); i++) {

                System.out.println(contenido.get(i).toString());
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private static void extraercsv() throws NumberFormatException {
        try (BufferedReader rd = new BufferedReader(new FileReader("src/main/resources/empleados.csv"))) {
            
            
            ArrayList<Empleado> datos = new ArrayList<>();
            
            String line;
            
            rd.readLine();
            double salariototal =0;
            
            while ((line = rd.readLine()) != null) {
                
                String[] dato = line.split(",");
                int id = Integer.parseInt(dato[0]);
                String nombre = dato[1];
                int salario = Integer.parseInt(dato[2]);
                salariototal+=salario;
                datos.add(new Empleado(id, nombre, salario));
            }
            
            System.out.println("\nEmpleados leídos: "+ datos.size());
            
            double media = salariototal / datos.size();
            System.out.println("Salario medio: "+media);
            
        } catch (IOException e) {
            
            
            e.printStackTrace();
        }
    }
}