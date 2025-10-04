package Fichero3.src;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Eje1 {
    public static void main(String[] args) throws Exception {

        try (InputStream input = new FileInputStream("src/archivo.txt");
                OutputStream salid = new FileOutputStream("src/Salida.txt")) {

            int dato = 0;
            int contador = 0;

            while ((dato = input.read()) != -1) {
                contador++;

                salid.write((char) dato);

            }
            

            System.out.println("Numero de Bytes copiados " + contador + " byte");
        }

        catch (Exception e) {
            System.out.print(e);
        }

    }
}
