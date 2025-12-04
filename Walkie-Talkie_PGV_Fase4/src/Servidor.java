import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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

    Scanner sc = new Scanner(System.in);

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

                EnvioMensajes envio = new EnvioMensajes(listaclientes);
                Thread hiloreceptor = new Thread(envio);

                try {
                    hiloreceptor.start();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            // El metodo accept boquea hazta q se connecete un cliente
            // declaramos una variable tipo socket pq esto es lo q devuelve el accept

        }
    }

}

class EnvioMensajes implements Runnable {

    static List<ObjectOutputStream> listaclientes;

    Scanner sc = new Scanner(System.in);
    String Contraseña = "1234567891234567";

    SecretKey contraseñaCifrada = Cifrador.generarClave(Contraseña, "AES");
    String nombre = "[ANUNCIO DEL SERVIDOR]: ";

    public EnvioMensajes(List<ObjectOutputStream> listaclientes) {

        this.listaclientes = listaclientes;
    }

    @Override

    public void run() {

        while (true) {

            System.out.println("Escriba un comando: ");
            String mensaje = sc.nextLine();

            if (mensaje.toLowerCase().equals("/broadcast")) {

                System.out.println("Escribe el mensaje a mandar :");
                String mensajecliente = sc.nextLine();
                String mensajeCompleto = nombre + mensajecliente;
                byte[] cifrado = Cifrador.cifrar(mensajeCompleto, contraseñaCifrada, "AES");
                GestionCliente.Broadcast(cifrado, nombre);

            } else {
                System.out.println("Comando desconocido");
            }
        }

    }

    public static void Broadcast(byte[] mensaje, String Nombre) {

        for (ObjectOutputStream pw : listaclientes) {

            try {
                pw.writeObject(mensaje);
                pw.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
