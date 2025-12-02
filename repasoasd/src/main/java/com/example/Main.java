package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        ArrayList<ConfiguracionServidor> configuracion = new ArrayList<>();
        ArrayList<Acceso> lsitaacceso = new ArrayList<>();
        ArrayList<Errores> listaerrores = new ArrayList<>();

        try (BufferedReader rd = new BufferedReader(new FileReader("src/main/resources/config.txt"))) {

            String nombre = null;
            String ip = null;
            int puerto = 0;
            String fecha = null;

            String datos;
            while ((datos = rd.readLine()) != null) {

                if (datos.startsWith("Servidor:")) {
                    nombre = datos.split(":")[1].trim();
                } else if (datos.startsWith("IP:")) {
                    ip = datos.split(":")[1].trim();
                } else if (datos.startsWith("Puerto:")) {
                    puerto = Integer.parseInt(datos.split(":")[1].trim());
                } else if (datos.startsWith("Inicio:")) {
                    fecha = datos.split(":")[1].trim();
                }

            }

            configuracion.add(new ConfiguracionServidor(nombre, ip, puerto, fecha));

            System.out.println("Configuración del servidor:");
            System.out.println("- Nombre: " + nombre);
            System.out.println("- IP: " + ip);
            System.out.println("- Puerto: " + puerto);
            System.out.println("- Inicio: " + fecha);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try (BufferedReader rs = new BufferedReader(new FileReader("src/main/resources/accesos.csv"))) {

            rs.readLine();

            String datos;

            String fecha = null;
            String hora = null;
            String ip_cliente = null;
            String metodo = null;
            String recurso = null;
            int codigo_estado = 0;

            while ((datos = rs.readLine()) != null) {

                fecha = datos.split(",")[0].trim();
                hora = datos.split(",")[1].trim();
                ip_cliente = datos.split(",")[2].trim();
                metodo = datos.split(",")[3].trim();
                recurso = datos.split(",")[4].trim();
                codigo_estado = Integer.parseInt(datos.split(",")[5].trim());

                lsitaacceso.add(new Acceso(codigo_estado, fecha, hora, ip_cliente, metodo, recurso));

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try (BufferedReader rs = new BufferedReader(new FileReader("src/main/resources/errores.json"))) {

            StringBuilder sb = new StringBuilder();
            String linea;

            String timestamp = null;
            String tipo = null;
            String mensaje = null;
            String gravedad = null;

            while ((linea = rs.readLine()) != null) {
                sb.append(linea);
            }

            String datos = sb.toString();

            JSONTokener toker = new JSONTokener(datos);

            JSONObject primero = new JSONObject(toker);

            JSONArray array = primero.getJSONArray("errores");

            for (int i = 0; i < array.length(); i++) {

                JSONObject objeto = array.getJSONObject(i);

                timestamp = objeto.getString("timestamp");
                tipo = objeto.getString("tipo");
                mensaje = objeto.getString("mensaje");
                gravedad = objeto.getString("gravedad");

                listaerrores.add(new Errores(gravedad, mensaje, timestamp, tipo));
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try (BufferedWriter wr = new BufferedWriter(new FileWriter("Datos.txt"))) {

         ConfiguracionServidor conf = configuracion.get(0);

            wr.write("=== INFORME DE ACTIVIDAD DEL SERVIDOR ===");
            wr.newLine();
            wr.write("Configuración:");
            wr.newLine();
            wr.write("- Nombre: " + conf.getServidor());
            wr.newLine();
            wr.write("- IP: " + conf.getIP());
            wr.newLine();
            wr.write("- Puerto: " + conf.getPuerto());
            wr.newLine();
            wr.newLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}