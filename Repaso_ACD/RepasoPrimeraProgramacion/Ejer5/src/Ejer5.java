
import java.util.Scanner;

public class Ejer5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String nombre;

        int oro = 0;
        int orogastado = 0;
        int pocionVida = 0;
        int pocionMana = 0;
        int EspadaHierro = 0;
        int EscudoMadera = 0;
        int ArmaduraCuero = 0;

        System.out.println("Nombre del Jugador");
        nombre = sc.nextLine();

        System.out.println("Oro inician Disponible :");
        oro = sc.nextInt();

        System.out.println("\n=== TIENDA DEL AVENTURERO ===\n");

        int oroinicial = oro;

        while (true) {

            System.out.println("  1. Poción de Vida (50 oro)\r\n" + //
                    " 2. Poción de Maná (40 oro)\r\n" + //
                    " 3. Espada de Hierro (150 oro)\r\n" + //
                    " 4. Escudo de Madera (100 oro)\r\n" + //
                    " 5. Armadura de Cuero (200 oro)\r\n" + //
                    " 6. Ver inventario y saldo\r\n" + //
                    " 7. Salir de la tienda");

            System.out.println(" \nTu oro actual: [" + oro + "]\r\n" + //
                    " ¿Qué deseas comprar?");

            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:

                    if (oro >= 50) {

                        orogastado += 50;
                        pocionVida++;
                        oro -= 50;

                    } else {
                        System.out.println("Saldo insuficiente");
                    }

                    break;
                case 2:
                    if (oro >= 40) {
                        orogastado += 40;
                        pocionMana++;
                        oro -= 40;
                    } else {
                        System.out.println("Saldo insuficiente");
                    }

                    break;

                case 3:
                    if (oro >= 150) {
                        orogastado += 150;
                        EspadaHierro++;
                        oro -= 150;

                    } else {
                        System.out.println("Saldo insuficiente");
                    }
                    break;

                case 4:
                    if (oro >= 100) {
                        orogastado += 100;
                        EscudoMadera++;
                        oro -= 100;

                    } else {
                        System.out.println("Saldo insuficiente");
                    }
                    break;
                case 5:
                    if (oro >= 200) {
                        orogastado += 200;
                        ArmaduraCuero++;
                        oro -= 200;
                    } else {
                        System.out.println("Saldo insuficiente");
                    }
                    break;
                case 6:

                    System.out.println("=== TU INVENTARIO ===\r\n" + //
                            "Pociones de Vida: " + pocionVida + "\r\n" + //
                            "Pociones de Maná: " + pocionMana + "\r\n" + //
                            "Espadas de Hierro: " + EspadaHierro + "\r\n" + //
                            "Escudos de Madera: " + EscudoMadera + "\r\n" + //
                            "Armaduras de Cuero: " + ArmaduraCuero + "\r\n" + //
                            "\r\n" + //
                            "Oro gastado: " + orogastado + "\r\n" + //
                            "Oro restante: " + oro);

                    break;
                case 7:
                    System.out.println("=== RESUMEN DE COMPRA ===\r\n" + //
                            "¡Gracias por tu visita, Arthas!\r\n" + //
                            "\r\n" + //
                            "Compras realizadas:");

                    if (pocionVida >= 1) {
                        int oropocionVida = pocionVida * 50;

                        System.out.println("- Pociones de Vida: " + pocionVida + " (" + oropocionVida + " oro)");

                    }
                    if (pocionMana >= 1) {
                        int oropocionMana = pocionMana * 40;

                        System.out.println("- Pociones de Mana: " + pocionMana + " (" + oropocionMana + " oro)");

                    }
                    if (EspadaHierro >= 1) {
                        int oropocionEspada = EspadaHierro * 150;

                        System.out.println("- Espadas de Hierro: " + EspadaHierro + " (" + oropocionEspada + " oro)");

                    }
                    if (EscudoMadera >= 1) {
                        int oropocionMadera = EscudoMadera * 100;

                        System.out.println("- Escudos de Madera: " + EscudoMadera + " (" + oropocionMadera + " oro)");

                    }
                    if (ArmaduraCuero >= 1) {
                        int oropocionArmadura = ArmaduraCuero * 200;

                        System.out.println("- Armaduras de Cuero: " + ArmaduraCuero + " (" + oropocionArmadura + " oro)");

                    }

                    System.out.println("-----------------------------\r\n" + //
                            "Total gastado: " + orogastado + " oro\r\n" + //
                            "Oro inicial: " + oroinicial + " oro\r\n" + //
                            "Oro restante: " + oro + " oro\r\n" + //
                            "\r\n" + //
                            "¡Buena suerte en tus aventuras!");

                    return;

                default:
                    System.out.println("Opción no válida. Por favor, elige una opción entre 1 y 7.");
                    break;

            }
        }

    }

}
