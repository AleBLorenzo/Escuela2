
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

//Creamos la clase GestionCliente con la interfaz runable q implementa run 
// Aca ponemos la logica de ejecion para q cada ves q se llame a un cliente haga esta logica y poder hacerlo con varios 
// ya q cone sta clase puedo poner de entrada varios de ellos.

public class GestionCliente implements Runnable {

    Scanner sc = new Scanner(System.in);

    private List<PrintWriter> listaclientes;
    private Socket cliente;

    public GestionCliente(Socket cliente, List<PrintWriter> listaclientes ){
        this.cliente = cliente;
        this.listaclientes = listaclientes;
       
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                OutputStream salida = cliente.getOutputStream();
                PrintWriter escritor = new PrintWriter(salida, true)) {

            listaclientes.add(escritor);

            for (int i = 0; i < listaclientes.size(); i++) {

                System.out.println("Se a unido " + escritor);
            }

            while (true) {

                String datos = buffer.readLine();

                if (datos.toLowerCase().equals("adios")) {

                    System.out.println("Connexion apagada");
                    System.out.println("Mensaje recibido: " + datos);

                    listaclientes.remove(escritor);
                    break;

                } else {

                    for (PrintWriter pw : listaclientes) {

                        pw.println(escritor);
                    }
                }

            }

        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());

        }

    }

    public List<PrintWriter> getListaclientes() {
        return listaclientes;
    }

    public void setListaclientes(List<PrintWriter> listaclientes) {
        this.listaclientes = listaclientes;
    }



}
