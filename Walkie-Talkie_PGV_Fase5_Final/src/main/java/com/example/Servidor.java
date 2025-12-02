package com.example;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.crypto.SecretKey;

public class Servidor {

    public static List<ObjectOutputStream> listaclientes = Collections
            .synchronizedList(new ArrayList<ObjectOutputStream>());

    public static void main(String[] args) throws Exception {

        // Creamos un puerto conun numero recomendable mayor de 1024
        final int PUERTO = 1025;
        String nombre = "Cliente";
        int contador = 1;

        // Creamos la instancia ServerSocket pasandole el puerto
        // Haciendo q este puerto en nustra pcse abra y se ponga en escucha
        // LLeva un Try-catch para cualputar la execion q mande

        try (ServerSocket server = new ServerSocket(PUERTO)) {
            System.out.println("Servidor listo para recibir");

            // El metodo accept boquea hazta q se connecete un cliente
            // declaramos una variable tipo socket pq esto es lo q devuelve el accept

            while (true) {

                Socket cliente = server.accept();
                contador++;
                String nombreCompleto = nombre + contador;

                GestionCliente clienteN = new GestionCliente(cliente, listaclientes, nombreCompleto);
                Thread NuevoCliente = new Thread(clienteN);

                System.out.println("Se a connectado el cliente");
                System.out.println(nombreCompleto);

                try {
                    NuevoCliente.start();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }
}
