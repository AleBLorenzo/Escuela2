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

            // El metodo accept boquea hazta q se connecete un cliente
            // declaramos una variable tipo socket pq esto es lo q devuelve el accept

            while (true) {

                Socket cliente = server.accept();
                contador++;

                try (OutputStream salida = cliente.getOutputStream();
                    ObjectOutputStream escritor = new ObjectOutputStream(salida);) {

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

                    EnvioMensajes envio = new EnvioMensajes(escritor);

                    Thread hiloreceptor = new Thread(envio);

                    try {
                        hiloreceptor.start();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }

        }
    }

  
}
 class EnvioMensajes implements Runnable {
                
        final String HOST = "localhost";
        final int PUERTO = 1025;
        Scanner sc = new Scanner(System.in);

        String Contraseña = "1234567891234567";
        ObjectOutputStream buffer;
        SecretKey contraseñaCifrada = Cifrador.generarClave(Contraseña, "AES");
        String nombre = "[ANUNCIO DEL SERVIDOR]: ";

        public EnvioMensajes(ObjectOutputStream buffer) {
            this.buffer = buffer;
        }

        @Override

        public void run() {

            try (Socket emisor = new Socket(HOST, PUERTO);
                OutputStream salida = emisor.getOutputStream();
                ObjectOutputStream escritor = new ObjectOutputStream(salida);
               ) {
                while (true) {

                    String mensaje = sc.nextLine();

                    if (mensaje.toLowerCase().equals("/broadcast")) {

                        System.out.print("Escribe el mensaje a mandar :");
                        String mensajecliente = sc.nextLine();
                        System.out.println("Connexion apagada");
                        String mensajeCompleto = nombre + mensajecliente;
                        byte[] cifrado = Cifrador.cifrar(mensajeCompleto, contraseñaCifrada, "AES");
                        escritor.write(cifrado);
                        escritor.flush();
                    } else {

                    }

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        public ObjectOutputStream getBuffer() {
            return buffer;
        }

        public void setBuffer(ObjectOutputStream buffer) {
            this.buffer = buffer;
        }
    }
