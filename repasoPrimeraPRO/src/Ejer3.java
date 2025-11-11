
import java.util.Scanner;

public class Ejer3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String nombre;
        int xpBase = 0;
        int nivel;
        int numeroEliminados;
        int saludRestante = 0;
        int numeroMuertes;
        int tiempoEmpleado;

        System.out.println("Nombre del jugador: ");
        nombre = sc.nextLine();

        System.out.println("XP base de la misión (número entero, mínimo 100): ");

        int xpBaseF = sc.nextInt();
        sc.nextLine();

        if (xpBaseF >= 100) {
            xpBase = xpBaseF;

        } else {
            System.out.println("numero menor de 100");
        }

        System.out.println("Nivel del jugador (número entero): ");
        nivel = sc.nextInt();
        sc.nextLine();

        System.out.println("Número de enemigos derrotados (número entero): ");
        numeroEliminados = sc.nextInt();
        sc.nextLine();

        System.out.println("Porcentaje de salud restante al finalizar (número entero entre 0 y 100): ");
        int saludRestanteF = sc.nextInt();
        sc.nextLine();

        if (saludRestanteF >= 0 && saludRestanteF <= 100) {
            saludRestante = saludRestanteF;
        } else {
            System.out.println("introduce un número entero entre 0 y 100");
        }

        System.out.println("Número de muertes durante la misión (número entero): ");
        numeroMuertes = sc.nextInt();
        sc.nextLine();

        System.out.println("Tiempo empleado en minutos (número entero): ");
        tiempoEmpleado = sc.nextInt();
        sc.nextLine();

        System.out.println("\nCalcular experiencia Total\n");
        System.out.println("Xp base: " + xpBase);

        double xpMultiplicadorBonus = (1 + (nivel * 0.1));
        double xpMultiplicadorNivel = xpBase * xpMultiplicadorBonus;
        double xpPorEnemigoEliminados = numeroEliminados * 30;

        System.out.println("Bonus nivel (" + nivel + "): " + xpBase + " * " + xpMultiplicadorBonus + " = "
                + xpMultiplicadorNivel + " XP");
        System.out.println("Bonus enemigos (" + numeroEliminados + "): " + xpMultiplicadorNivel + " * ("
                + numeroEliminados + "*" + "30" + ") = " + xpPorEnemigoEliminados + " XP");

        double xpAcumulada = xpMultiplicadorNivel + xpPorEnemigoEliminados;
        double bonusPorSalud;

        if (saludRestante > 75) {

            bonusPorSalud = (int) (xpAcumulada + (xpAcumulada * 0.15));
            System.out.println("Bonus salud (" + saludRestante + "> 75%): " + xpAcumulada + " + (" + xpAcumulada + "*"
                    + "0.15" + ") = " + bonusPorSalud + " XP");

        } else if (saludRestante <= 75 && saludRestante >= 50) {

            bonusPorSalud = (int) (xpAcumulada + (xpAcumulada * 0.08));
            System.out.println("Bonus salud (50<=" + saludRestante + "=<75): " + xpAcumulada + " + (" + xpAcumulada
                    + "*" + "0.08" + ") = " + bonusPorSalud + " XP");

        } else if (saludRestante <= 49 && saludRestante >= 25) {

            bonusPorSalud = xpAcumulada;
            System.out.println("Bonus salud (" + saludRestante + "): " + xpAcumulada + " = " + bonusPorSalud + " XP");

        } else {

            bonusPorSalud = (int) (xpAcumulada - (xpAcumulada * 0.10));
            System.out.println("Bonus salud (" + saludRestante + "): " + xpAcumulada + " - (" + xpAcumulada + "*"
                    + "0.10" + ") = " + bonusPorSalud + " XP");

        }

        double bonusPorMuertes = 0;

        if (numeroMuertes == 0) {

            bonusPorMuertes = bonusPorSalud + 200;
            System.out.println("Bonus muertes (" + numeroMuertes + "): " + bonusPorSalud + " + (200) = "
                    + bonusPorMuertes + " XP");

        } else {
            bonusPorMuertes = bonusPorSalud - (numeroMuertes * 50);
            System.out.println("Penalización muertes (" + numeroMuertes + "): " + bonusPorSalud + " - (" + numeroMuertes
                    + "*" + "50" + ") = " + bonusPorMuertes + " XP");
        }

        double bonusFinal = 0;

        if (tiempoEmpleado < 10) {

            bonusFinal = (int) (bonusPorMuertes + (bonusPorMuertes * 0.20));
            System.out.println("Bonus tiempo (>10 min): " + bonusPorMuertes + " + (" + bonusPorMuertes + "*" + "0.20"
                    + ") = " + bonusFinal + " XP");

        } else if (tiempoEmpleado > 30) {

            bonusFinal = (int) (bonusPorMuertes - (bonusPorMuertes * 0.15));
            System.out.println("Bonus tiempo (>30 min): " + bonusPorMuertes + " - (" + bonusPorMuertes + "*" + "0.15"
                    + ") = " + bonusFinal + " XP");
        } else {

            bonusFinal = bonusPorMuertes;

            System.out.println("Tiempo normal (10-30 min): Sin Cambios = " + bonusFinal + " XP");
        }

        System.out.println("\nXP FINAL: " + bonusFinal);

        double subidaDeNivel = bonusFinal / 1000;
        int nivelganado = (int) subidaDeNivel;
        System.out.println("Niveles ganados: " + nivelganado + " (" + bonusFinal + "/1000 =" + subidaDeNivel + ")");

    }

}
