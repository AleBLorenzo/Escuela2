
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws Exception {

        // Declaramos el puerto del cliente y la dirrecion y el Scanner.
        final String HOST = "localhost";
        final int PUERTO = 1025;
        Scanner sc = new Scanner(System.in);

        // Creamos un socket con los datos del HOST y el PUERTO.@interface
        // try-catch para si el servidor esta en escucha si no lanza exepcion

        try (Socket emisor = new Socket(HOST, PUERTO)) {

            // Con esto sacamos la info hacia el servidor
            // Creamos el PrintWriter para facilitar el envio
            // Con el autoflush en true apra q se haga de inmediato
            try (OutputStream salida = emisor.getOutputStream();
                    PrintWriter escritor = new PrintWriter(salida, true);
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(emisor.getInputStream()))) {

                while (true) {

                    System.out.println("Escribe el mensaje a enviar: ");
                    String mensaje = sc.nextLine();

                    if (mensaje.toLowerCase().equals("adios")) {

                        System.out.println("Connexion apagada");
                         escritor.println(mensaje);
                        break;

                    } else {
                        escritor.println(mensaje);

                    }

                    String datos = buffer.readLine();

                    if (datos.toLowerCase().equals("adios")) {

                      System.out.println("Connexion apagada");
                      System.out.println("Mensaje recibido: " + datos);

                        break;
                    } else {

                        System.out.println("Mensaje recibido: " + datos);
                    }
                }

            } catch (IOException e) {

                System.out.println("Error" + e.getMessage());

            }

            sc.close();

        } catch (IOException e) {

            System.out.println("Error" + e.getMessage());

        }

    }
}
