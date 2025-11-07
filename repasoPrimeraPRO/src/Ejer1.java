
import java.util.Scanner;

public class Ejer1 {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        String nombre;
        int puntuacion;
        int enemigos_eliminados;
        int tiempo_empleado;
        boolean nivel = false;

        System.out.println("Introduzca nombre del jugador");
        nombre = sc.nextLine();

        System.out.println("Puntuación base (número entero)");
        puntuacion = sc.nextInt();
        sc.nextLine();

        System.out.println("Número de enemigos eliminados (número entero)");
        enemigos_eliminados = sc.nextInt();
        sc.nextLine();

        System.out.println("Tiempo empleado el segundos (número entero)");
        tiempo_empleado = sc.nextInt();
        sc.nextLine();

        System.out.println("Completo el nivel ? (Si o No)");

        String dato = sc.nextLine();
        if ("si".equals(dato.toLowerCase())) {
            nivel = true;

        } else if ("no".equals(dato.toLowerCase())) {
            nivel = false;

        } else if (!"no".equals(dato.toLowerCase()) && !"no".equals(dato.toLowerCase())) {

            System.out.println("Introduce una centencia válida");

        }

        sc.close();
        System.out.println();
        System.out.println("Bienvenido : " + nombre);
        System.out.println("===================");
        System.out.println("Puntuacion base : " + puntuacion + " Puntos");

        int BonificacionPorEnemigo = 50;
        int PuntosPorEnemigosEliminados = enemigos_eliminados * BonificacionPorEnemigo;

        System.out.println(
                "Bonificación por enemigos: (" + enemigos_eliminados + "): " + PuntosPorEnemigosEliminados + " Puntos");

        int puntuacionPorTiempo;
        if (tiempo_empleado < 120) {
            puntuacionPorTiempo = 120;

        } else {
            puntuacionPorTiempo = 0;
        }
        System.out.println("Bonificación por velocidad : +" + puntuacionPorTiempo + " Puntos");

        int puntuacionPorNivel = 0;
        if (nivel) {
            puntuacionPorNivel = 500;
            System.out.println("Bonificación por nivel completado: +" + puntuacionPorNivel + " Puntos");
        } else {
            System.out.println("Nivel no completado " + puntuacionPorNivel + " Puntos adicionales");

        }

        int PuntuacionTotal = PuntosPorEnemigosEliminados + puntuacionPorTiempo + puntuacionPorNivel;
        System.out.println("-------------------");
        System.out.println("PUNTUACIÓN FINAL: " + PuntuacionTotal);

        String[] FracesRamdonAprovado = { "¡Excelente trabajo!",
                "¡Muy bien hecho!",
                "¡Sigue así!",
                "¡Gran esfuerzo!",
                "¡Lo lograste!",
                "¡Tu dedicación dio resultados!",
                "¡Impresionante!",
                "¡Eres un ejemplo a seguir!",
                "¡Buen trabajo, se nota tu esfuerzo!",
                "¡Felicitaciones, aprobaste con éxito!" };

        String[] FracesRamdonDesaprovado = { "¡Sigue intentándolo!",
                "No te rindas, la próxima será mejor.",
                "Cada error es una oportunidad para aprender.",
                "¡Tú puedes lograrlo!",
                "No te preocupes, sigue practicando.",
                "El esfuerzo constante da resultados.",
                "Fallar no es el final, es parte del proceso.",
                "Aprende de este intento y mejora.",
                "¡Ánimo, la próxima vez te irá mejor!",
                "No te desanimes, estás en el camino correcto." };

        int NumeroRmdon = (int) (Math.random() * 10);

        if (puntuacionPorTiempo == 0 && puntuacionPorNivel == 0) {

            System.out.println(FracesRamdonDesaprovado[NumeroRmdon]);

        } else {

            System.out.println(FracesRamdonAprovado[NumeroRmdon]);

        }
    }

}
