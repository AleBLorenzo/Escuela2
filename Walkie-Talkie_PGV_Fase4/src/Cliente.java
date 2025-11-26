
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import javax.crypto.SecretKey;

public class Cliente {

    public static void main(String[] args) throws Exception {

        // Declaramos el puerto del cliente y la dirrecion y el Scanner.
        final String HOST = "localhost";
        final int PUERTO = 1025;

        Scanner sc = new Scanner(System.in);
        final String Contraseña = "1234567891234567";
        SecretKey contraseñaCifrada = Cifrador.generarClave(Contraseña, "AES");

        // Creamos un socket con los datos del HOST y el PUERTO.
        // try-catch para si el servidor esta en escucha si no lanza exepcion

        try (Socket emisor = new Socket(HOST, PUERTO);
                OutputStream salida = emisor.getOutputStream();
                ObjectOutputStream escritor = new ObjectOutputStream(salida);

                InputStream entrada = emisor.getInputStream();
                ObjectInputStream buffer = new ObjectInputStream(entrada);) {

            // Con esto sacamos la info hacia el servidor
            // Creamos el PrintWriter para facilitar el envio
            // Con el autoflush en true apra q se haga de inmediato

            ReceptorMensajes receptorMensajes = new ReceptorMensajes(buffer);
            Thread hiloreceptor = new Thread(receptorMensajes);

            try {
                hiloreceptor.start();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            while (true) {

                System.out.println("Escribe el mensaje a enviar: ");
                String mensaje = sc.nextLine();

                if (mensaje.toLowerCase().equals("adios")) {

                    System.out.println("Connexion apagada");
                    byte[] cifrado = Cifrador.cifrar(mensaje, contraseñaCifrada, "AES");
                    escritor.writeObject(cifrado);
                    escritor.flush();
                    break;

                } else {
                    byte[] cifrado = Cifrador.cifrar(mensaje, contraseñaCifrada, "AES");
                    escritor.writeObject(cifrado);
                    escritor.flush();

                }

            }

        } catch (IOException e) {

            System.out.println("Error" + e.getMessage());

        }

        sc.close();

    }
}

class ReceptorMensajes implements Runnable {

    public static final String Contraseña = "1234567891234567";
    ObjectInputStream buffer;
    SecretKey contraseñaCifrada = Cifrador.generarClave(Contraseña, "AES");

    public ReceptorMensajes(ObjectInputStream buffer) {
        this.buffer = buffer;
    }

    @Override

    public void run() {

        Object datos;
        try {
            while ((datos = buffer.readObject()) != null) {

                byte[] mensaje = (byte[]) datos;

                String mensajedescifrado = Cifrador.descifrar(mensaje, contraseñaCifrada);

                System.out.println("Mensaje recibido: " + mensajedescifrado);
            }

        } catch (IOException | ClassNotFoundException e) {
        }

    }

    public ObjectInputStream getBuffer() {
        return buffer;
    }

    public void setBuffer(ObjectInputStream buffer) {
        this.buffer = buffer;
    }

}
