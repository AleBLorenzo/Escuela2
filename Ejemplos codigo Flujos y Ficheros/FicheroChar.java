import java.io.*;

public class FicheroChar {
    public static void main(String[] args) {
        String fileName = "mifichero.txt";

        /*
            Escribe en fichero.
            FileWriter escribe caracter a caracter, es más lento.
            Para más eficiencia usamos un buffer que guarda temporalmente en memoria para luego volcarlo en el fichero.
        */
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("Hola, mundo!");
            bw.newLine();
            bw.write("que tal estas");
            bw.close();
            System.out.println("Fichero escrito correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }

        /* Lee de fichero */
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String linea;
            System.out.println("Leemos del fichero:");

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }
}
