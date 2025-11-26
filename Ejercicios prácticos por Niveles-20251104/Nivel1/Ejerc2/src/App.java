
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String Ruta;
        String SecuenciaDatos;
        int tiempoMs;
        final Map<Character, Integer> CODIGOS_TECLAS = new HashMap<>();
        final Map<Character, String> NOMBRES_TECLAS = new HashMap<>();

        CODIGOS_TECLAS.put('W', 87);
        NOMBRES_TECLAS.put('W', "Arriba");
        CODIGOS_TECLAS.put('A', 65);
        NOMBRES_TECLAS.put('A', "Izquierda");
        CODIGOS_TECLAS.put('S', 83);
        NOMBRES_TECLAS.put('S', "Abajo");
        CODIGOS_TECLAS.put('D', 68);
        NOMBRES_TECLAS.put('D', "Derecha");
        CODIGOS_TECLAS.put('J', 74);
        NOMBRES_TECLAS.put('J', "Puño");
        CODIGOS_TECLAS.put('K', 75);
        NOMBRES_TECLAS.put('K', "Patada");
        CODIGOS_TECLAS.put('L', 76);
        NOMBRES_TECLAS.put('L', "Bloqueo");
        CODIGOS_TECLAS.put(' ', 32);
        NOMBRES_TECLAS.put(' ', "Salto");
        CODIGOS_TECLAS.put('X', 16);
        NOMBRES_TECLAS.put('X', "Correr");
        CODIGOS_TECLAS.put('P', 13);
        NOMBRES_TECLAS.put('P', "Pausa");

        System.out.println("╔════════════════════════════════════════════════════════════════╗\n" + //
                "║     GENERADOR DE SECUENCIAS DE TECLAS - REPLAY SYSTEM          ║\n" + //
                "╚════════════════════════════════════════════════════════════════╝");

        System.out.print("Nombre del archivo de salida: ");
        Ruta = sc.nextLine();

        System.out.print("Secuencia de teclas (Permitidas : W, A, S, D, J, K, L, SPACE, SHIFT, ENTER):");
        SecuenciaDatos = sc.nextLine().trim().toUpperCase();

        System.out.print("Duración total (ms): ");
        tiempoMs = sc.nextInt();
        sc.nextLine();

        System.out.println("\n--- PROCESANDO SECUENCIA ---");

        List<Byte> bytesValidos = new ArrayList<>();

        for (char c : SecuenciaDatos.toCharArray()) {

                if (CODIGOS_TECLAS.containsKey(c)) {
                int codigo = CODIGOS_TECLAS.get(c);
                bytesValidos.add((byte) codigo);
                System.out.printf("Tecla '%c' (%s) → 0x%02X%n", 
                                c, NOMBRES_TECLAS.get(c), codigo);
            } else {
                System.out.printf("⚠ Advertencia: Tecla '%c' no válida (ignorada)%n", c);
            }
        }

    }
}
