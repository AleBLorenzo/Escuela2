import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) throws Exception {

        // Creamos un puerto con un numero recomendable mayor de 1024
        final int PUERTO = 1025;
        Scanner sc = new Scanner(System.in);

        // Creamos la instancia ServerSocket pasandole el puerto
        // Haciendo q este puerto en nustra pcse abra y se ponga en escucha
        // LLeva un Try-catch para cualputar la execion q mande

        ServerSocket server = new ServerSocket(PUERTO);

            System.out.println("Servidor listo para recibir");

            // El metodo accept boquea hazta q se connecete un cliente
            // declaramos una variable tipo socket pq esto es lo q devuelve el accept

            Socket dato = server.accept();
                System.out.println("Se a connectado el cliente");

                // Con el getInputStream obtenemos los datos q manda el cliente
                // para facilitar la lectura lo envovlemos en un InputStreamReader
                // Creamos un BufferedReader para poder leerlo masc comodo


                while (true) {

                    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(dato.getInputStream()))) {

                        String datos = buffer.readLine();

                        if (datos.toLowerCase().equals("adios")) {

                            System.out.println("Connexion apagada");

                            break;

                        } else {

                            System.out.println("Mensaje recibido: " + datos);
                        }

                    } catch (IOException e) {
                        System.out.println("Error" + e.getMessage());

                    }

                    try ( // Con esto sacamos la info hacia el servidor
                            OutputStream salida = dato.getOutputStream();

                            // Creamos el PrintWriter para facilitar el envio
                            // Con el autoflush en true apra q se haga de inmediato
                            PrintWriter escritor = new PrintWriter(salida, true)) {

                        System.out.println("Escribe el mensaje a enviar: ");
                        String mensaje = sc.nextLine();

                        if (mensaje.toLowerCase().equals("adios")) {

                            
                            System.out.println("Connexion apagada");
                            break;

                        } else {
                            escritor.println(mensaje);

                        }

                    } catch (IOException e) {
                        System.out.println("Error" + e.getMessage());

                    }
                }

                System.out.println("Conversacion terminada");

    

            sc.close();


    }
}
