import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Servidor {

    public static List<PrintWriter> listaclientes = Collections.synchronizedList(new ArrayList<PrintWriter>());

    public static void main(String[] args) throws Exception {

        // Creamos un puerto con un numero recomendable mayor de 1024
        final int PUERTO = 1025;
        Scanner sc = new Scanner(System.in);

        // Creamos la instancia ServerSocket pasandole el puerto
        // Haciendo q este puerto en nustra pcse abra y se ponga en escucha
        // LLeva un Try-catch para cualputar la execion q mande

        try (ServerSocket server = new ServerSocket(PUERTO)) {
            System.out.println("Servidor listo para recibir");

            // El metodo accept boquea hazta q se connecete un cliente
            // declaramos una variable tipo socket pq esto es lo q devuelve el accept

            while (true) {

                try (Socket cliente = server.accept()) {

                    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                            OutputStream salida = cliente.getOutputStream();
                            PrintWriter escritor = new PrintWriter(salida, true)) {

                        listaclientes.add(escritor);

                        GestionCliente clienteN = new GestionCliente(cliente, listaclientes);
                        Thread NuevoCliente = new Thread(clienteN);

                        while (true) {

                            String datos = buffer.readLine();

                            if (datos.toLowerCase().equals("adios")) {

                                System.out.println("Connexion apagada");
                                System.out.println("Mensaje recibido: " + datos);

                                listaclientes.remove(escritor);

                            } else {

                                for (int i = 0; i < listaclientes.size(); i++) {

                                    escritor.print(datos);
                                }
                            }

                            System.out.println("Se a connectado el cliente");

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