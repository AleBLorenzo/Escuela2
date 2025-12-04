
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import javax.crypto.SecretKey;

//Creamos la clase GestionCliente con la interfaz runable q implementa run 
// Aca ponemos la logica de ejecion para q cada ves q se llame a un cliente haga esta logica y poder hacerlo con varios 
// ya q cone sta clase puedo poner de entrada varios de ellos.

public class GestionCliente implements Runnable {

    Scanner sc = new Scanner(System.in);
              final String Contraseña = "1234567891234567";
      SecretKey contraseñaCifrada = Cifrador.generarClave(Contraseña, "AES");

    private static List<ObjectOutputStream> listaclientes;
    public static ObjectOutputStream getOutputStream;
    private Socket cliente;
    private String Nombre;

    public GestionCliente(Socket cliente, List<ObjectOutputStream> listaclientes, String Nombre) {
        this.cliente = cliente;
        this.listaclientes = listaclientes;
        this.Nombre = Nombre;

    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {

        try (OutputStream salida = cliente.getOutputStream();
                    ObjectOutputStream escritor = new ObjectOutputStream(salida);
                    InputStream entrada = cliente.getInputStream();
                    ObjectInputStream buffer = new ObjectInputStream(entrada)) {

            listaclientes.add(escritor);

                 Object datos;
              
              while ((datos = buffer.readObject())!= null) { 
                  
                byte[] mensaje = (byte[]) datos;

            String mensajedescifrado = Cifrador.descifrar(mensaje, contraseñaCifrada);
             

                if (mensajedescifrado.toLowerCase().equals("adios")) {

                    System.out.println("Connexion apagada");
                    System.out.println("Mensaje recibido: " + mensajedescifrado);

                    listaclientes.remove(escritor);
                    break;

                } else {
                    Broadcast(mensaje, Nombre);

                }

            }

        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());

        } catch (ClassNotFoundException ex) {
        }

    }

    public List<ObjectOutputStream> getListaclientes() {
        return listaclientes;
    }

    public void setListaclientes(List<ObjectOutputStream> listaclientes) {
        this.listaclientes = listaclientes;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public static void Broadcast(byte[] mensaje,String Nombre) {

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

