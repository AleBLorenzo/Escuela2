import java.io.*;

public class StringRW {
    public static void main(String[] args) {
        try {
            /* 
              Escritura en el flujo de salida StringWriter y luego a la cadena resultado
            */
            StringWriter escritor = new StringWriter();


            // Escribir en el flujo (en memoria), similar a cuando lo hacemos en fichero
            escritor.write("Hola mundo desde StringWriter\n"); 

            String texto = escritor.toString();

            escritor.close();



            /* Lectura del texto anterior en el flujo de entrada StringReader
            */
            StringReader lector = new StringReader(texto);
            int caracter; //codigo numerico del caracter a leer

            System.out.println("Leyendo desde StringReader:");
            while ((caracter = lector.read()) != -1) {
                System.out.print((char) caracter); //convertimos codigo numérico del caracter en caracter
            }

            lector.close();

        } catch (IOException e) {
            System.out.println("Error durante la lectura o escritura: " + e.getMessage());
        }
    }
}

