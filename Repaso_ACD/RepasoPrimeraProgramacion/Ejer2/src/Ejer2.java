
import java.util.Scanner;

public class Ejer2 {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        String nombre;
        int edad ;
        boolean premium = false ;

        System.out.println("Nombre de Usuario");
        nombre = sc.nextLine();

        System.out.println("Edad (número entero)");
        edad = sc.nextInt();
        sc.nextLine();

         if (edad >= 18 ) {
              System.out.println("¿Es suscriptor premium? (respuesta: SI o NO)");
        String dato = sc.nextLine();

        if (dato.toLowerCase().equals("si")) {
            premium = true;
        } else if (dato.toLowerCase().equals("no")){
             premium = false;
        } else if (dato.toLowerCase().equals("si")&& dato.toLowerCase().equals("no")) {
            System.out.println("Introduce los datos correctos");
            sc.close();
        }
         } else{

            premium = false;
         }
       
  
        sc.close();
        Usuario nuevo = new Usuario(edad, nombre, premium);

        System.out.println("¡Hola "+ nuevo.getNombre()+"!");
        System.out.println("==============");

        if (nuevo.getEdad()< 12) {
            System.out.println("Categoría: "+ Categoria.INFANTIL);
            System.out.println("Edad: "+ nuevo.getEdad());
            System.out.println("\nModos disponibles:");
            System.out.println("- Modo Infantil\n");
            System.out.println("NOTA: Contenido adaptado y seguro para niños.");
            System.out.println("¡Diviértete jugando!");

        } else   if (nuevo.getEdad()>= 12 && nuevo.getEdad() <= 15) {
            System.out.println("Categoría: "+ Categoria.ADOLECENTE);
            System.out.println("Edad: "+ nuevo.getEdad());
            System.out.println("\nModos disponibles:");
            System.out.println("- Modo Adolescente");
            System.out.println("- Modo Infantil\n");
            System.out.println("NOTA: Algunos contenidos pueden requerir supervisión parental.");
            System.out.println("¡Disfruta del juego!");
            
        } else   if (nuevo.getEdad()>= 16 && nuevo.getEdad() <= 17) {
            System.out.println("Categoría: "+ Categoria.JOVEN);
            System.out.println("Edad: "+ nuevo.getEdad());
            System.out.println("\nModos disponibles:");
            System.out.println("- Modo Joven");
            System.out.println("- Modo Adolescente");
            System.out.println("- Modo Infantil\n");
            System.out.println("NOTA: Acceso a contenido con violencia moderada.");
            System.out.println("¡Buena suerte!");
        }
            else if (nuevo.getEdad() >= 18) {
            System.out.println("Categoría: "+ Categoria.ADULTO);
            System.out.println("Edad: "+ nuevo.getEdad());
            System.out.println("\nModos disponibles:");
            System.out.println("- Modo Adulto");
            System.out.println("- Modo Joven");
            System.out.println("- Modo Adolescente");
            System.out.println("- Modo Infantil\n");

            if (premium) {
                System.out.println("⭐ USUARIO PREMIUM ⭐");
                System.out.println("Acceso prioritario a servidores");
                System.out.println("Sin publicidad\n");
            } 

            System.out.println("NOTA: Acceso completo a todo el contenido del juego.");
            System.out.println("¡Bienvenido de nuevo!");
        }


    }
}
