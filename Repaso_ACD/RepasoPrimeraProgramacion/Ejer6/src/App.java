
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static final Object Jugador = null;

    public static void main(String[] args) throws Exception {

         ArrayList<Jugador> lista = new ArrayList();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Ejer6/src/Datos.dat"))) {
            
             
                  Object obj;
                while ((obj = in.readObject()) != null) {
                        lista.add((Jugador) obj);
                }

        } catch (Exception e) {
            System.out.println("Error "+e);
        }

       
     
        Scanner sc = new Scanner(System.in);
        String nombre = null ;
        String entrada = null ;
        int contadorpartida = -1;
        int racha = 0;
        int racha_Maxima = 0;
        int victorias = 0;
        int derrotas = 0;
        int empates = 0;

          System.out.print("Escriba su nombre de jugador: ");
        nombre = sc.nextLine();

        while (true) {

            System.out.print("Partida "+ (contadorpartida+ 2)+": ");
            entrada = sc.nextLine(); 

            contadorpartida++;

            if (entrada.toUpperCase().equals("V")) {

                victorias++;
                racha++;

                if (racha > racha_Maxima) {
                    
                    racha_Maxima = racha;
                    
                } if (racha> 4) {
                    System.out.println("🔥 ¡"+racha+" VICTORIAS! ¡Imparable!");
                    
                }

                for (Jugador elem : lista) {
                   if (elem.getNombre().equals(nombre)) {
                      if (elem.getRacha_Maxima()< racha_Maxima) {
                          System.out.println("¡NUEVA RACHA MÁXIMA! ("+racha_Maxima+"victorias)");
                      }
                       
                   }
                }
              

            }else if (entrada.toUpperCase().equals("D")) {
                derrotas++;
                racha = 0;
            

            } else if (entrada.toUpperCase().equals("E")) {
                empates++;
                racha= 0;
     

            } else if (entrada.toUpperCase().equals("FIN")) {
                break;
            }else {
               System.out.println("Escriba una opcion valida V, D, E o FIN");
               contadorpartida--;
            }
            
        }

    
        Jugador Jugador = new Jugador(derrotas, empates, nombre, racha, racha_Maxima, victorias);
        lista.add(Jugador);


        double winrate = (((double)victorias / contadorpartida) * 100);
        double lostrate = ((double)derrotas / contadorpartida) * 100;
        double drawrate = ((double)empates / contadorpartida) * 100;

        MostrarInfo(nombre, contadorpartida, victorias, winrate, derrotas, lostrate, empates, drawrate, racha_Maxima);
        
        try (ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream("Ejer6/src/Datos.dat"))) {

            ou.writeObject(Jugador);

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        }

        


    private static void MostrarInfo(String nombre, int contadorpartida, int victorias, double winrate, int derrotas, double lostrate, int empates, double drawrate, int racha_Maxima) {
        System.out.println("\n=== RESUMEN DE SESIÓN ===");
        System.out.println("Jugador: "+nombre);
        System.out.println("\nTotal de partidas jugadas: " + (contadorpartida));
        System.out.println("Victorias : "+ victorias+" ("+winrate+"%)");
        System.out.println("Derrotas : "+ derrotas+" ("+lostrate+"%)");
        System.out.println("Empates : "+ empates+" ("+drawrate+"%)");
        System.out.println("\nRacha máxima: "+racha_Maxima+" victorias\n");

        if (winrate >=90) {
            System.out.println("Clasificación: "+Clasificacion.LEYENDA);
        }else if (winrate >=70) {
            System.out.println("Clasificación: "+Clasificacion.PRO);
        }else if (winrate >=50) {
            System.out.println("Clasificación: "+Clasificacion.COMPETENTE);
        }else if (winrate >=30) {
            System.out.println("Clasificación: "+Clasificacion.EQUILIBRADO);
        }else if (winrate <29) {
            System.out.println("Clasificación: "+Clasificacion.APRENDIZ);
        }
    }

   
    }
