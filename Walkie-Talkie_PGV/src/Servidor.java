import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws Exception {

        // Creamos un puerto con un numero recomendable mayor de 1024
        final int PUERTO = 1025;
        
        // Creamos la instancia ServerSocket pasandole el puerto
        // Haciendo q este puerto en nustra pcse abra y se ponga en escucha
        // LLeva un Try-catch para cualputar la execion q mande

        try (ServerSocket server = new ServerSocket(PUERTO)) {

            System.out.println("Servidor listo para recibir");

            // El metodo accept boquea hazta q se connecete un cliente
            // declaramos una variable tipo socket pq esto es lo q devuelve el accept

            try (Socket dato = server.accept()) {
                System.out.println("Se a connectado el cliente");

                // Con el getInputStream obtenemos los datos q manda el cliente
                // para facilitar la lectura lo envovlemos en un InputStreamReader
                // Creamos un BufferedReader para poder leerlo masc comodo

                try (BufferedReader buffer = new BufferedReader(new InputStreamReader(dato.getInputStream()))) {
                    String datos = buffer.readLine();

                    System.out.println("Mensaje recibido: " + datos);

                } catch (IOException e) {
                    System.out.println("Error" + e.getMessage());

                }
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());

            }

        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());

        }

    }
}
