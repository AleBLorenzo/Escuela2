import java.io.*;

public class FicheroBytes {
    public static void main(String[] args) {
        String nombreArchivo = "mifichero.txt";
        String mensaje = "Hola, que tal, bro";

        //fichero de bytes: salida
        try (FileOutputStream ficheroSalida = new FileOutputStream(nombreArchivo)) {
            ficheroSalida.write(mensaje.getBytes()); // Convertimos el String en bytes
            System.out.println("Mensaje escrito a fichero correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }

        // fichero de bytes: entrada
        try (FileInputStream ficheroEntrada = new FileInputStream(nombreArchivo)) {
            int byteLeido;
            System.out.print("Contenido del archivo: ");
            while ((byteLeido = ficheroEntrada.read()) != -1) {
                System.out.print((char) byteLeido); // Convertimos byte a char para mostrarlo
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
