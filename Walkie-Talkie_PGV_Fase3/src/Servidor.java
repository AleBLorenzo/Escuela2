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
            // Con el getInputStream obtenemos los datos q manda el cliente
            // para facilitar la lectura lo envovlemos en un InputStreamReader
            // Creamos un BufferedReader para poder leerlo masc comodo
            // Con esto sacamos la info hacia el servidor
            // Creamos el PrintWriter para facilitar el envio
            // Con el autoflush en true apra q se haga de inmediato

            while (true) {

                try (Socket cliente = server.accept()) {

               

                        GestionCliente clienteN = new GestionCliente(cliente, listaclientes);
                        Thread NuevoCliente = new Thread(clienteN);


                            System.out.println("Se a connectado el cliente");

                            try {
                                NuevoCliente.start();
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                 

                    } catch (IOException e) {
                        System.out.println("Error" + e.getMessage());

                    }

                    System.out.println("Conversacion terminada");

                sc.close();
            }
        }

    }
}