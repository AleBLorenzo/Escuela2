import java.io.*;

public class ByteToChar {
    public static void main(String[] args) {
        // Flujo de entrada: InputStreamReader transforma byte a texto. En este caso usa el teclado
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        // Flujo de salida: clase PrintWriter para convertir bytes a texto. En este caso usa la salida por pantalla
        PrintWriter salida = new PrintWriter(System.out, true);
        try {
            salida.println("Escribe un mensaje:");
            String resultado = entrada.readLine();

            salida.println(resultado); //mostrar por pantalla
        } catch (IOException e) {
            salida.println("Error al leer los datos.");
        }
    }
}
