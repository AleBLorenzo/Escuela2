
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
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

            try ( // Con esto sacamos la info hacia el servidor
                    OutputStream salida = emisor.getOutputStream();

                    // Creamos el PrintWriter para facilitar el envio
                    // Con el autoflush en true apra q se haga de inmediato
                    PrintWriter escritor = new PrintWriter(salida, true)) {

                System.out.println("Escribe el mensaje a enviar: ");
                String mensaje = sc.nextLine();

                escritor.println(mensaje);

            } catch (IOException e) {
            System.out.println("Error" + e.getMessage());

        }
            sc.close();

        } catch (UnknownHostException e) {
            System.out.println("Error" + e.getMessage());

        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }

    }
}
