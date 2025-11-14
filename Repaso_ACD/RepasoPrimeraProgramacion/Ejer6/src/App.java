
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
     
        Scanner sc = new Scanner(System.in);
        String nombre ;
        String entrada = null ;
        String entrada2 = null;
        int contadorpartida = -1;
        int racha = 0;
        int racha_Maxima = 0;
        int victorias = 0;
        int derrotas = 0;
        int empates = 0;


        System.out.println("===Bienvenido===\n");
        System.out.print("Escriba su nombre de jugador: ");
        nombre = sc.nextLine();

        while (true) {

            System.out.print("Partida "+ (contadorpartida+ 2)+": ");
            entrada = sc.nextLine(); 

            contadorpartida++;

            if (entrada.equals("V")) {

                victorias++;
                racha++;

                if (racha > racha_Maxima) {
                    
                    racha_Maxima = racha;
                    
                } if (racha> 4) {
                    System.out.println("🔥 ¡"+racha+" VICTORIAS! ¡Imparable!");
                    
                }
              

            }else if (entrada.equals("D")) {
                derrotas++;
                racha = 0;
            

            } else if (entrada.equals("E")) {
                empates++;
                racha= 0;
     

            } else if (entrada.equals("FIN")) {
                break;
            }else {
               System.out.println("Escriba una opcion valida V, D, E o FIN");
            }
            
        }

        float winrate = (victorias / contadorpartida) * 100;
        float lostrate = (derrotas / contadorpartida) * 100;
        float drawrate = (empates / contadorpartida) * 100;

        System.out.println("\n=== RESUMEN DE SESIÓN ===");
        System.out.println("Jugador: "+nombre);
        System.out.println("\nTotal de partidas jugadas: " + (contadorpartida));
        System.out.println("Victorias : "+ victorias+" ("+winrate+"%)");
        System.out.println("Derrotas : "+ derrotas+" ("+lostrate+"%)");
        System.out.println("Empates : "+ empates+" ("+drawrate+"%)");
        System.out.println("\nRacha máxima: "+racha_Maxima+" victorias");


    }
}
