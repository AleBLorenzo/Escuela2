
import java.util.Scanner;

public class Ejer4 {

    public static void main(String[] args) {

        String NombreUser;
        String Contraseña = null;
        String ConfirmarContraseña;

        Boolean tamanoMinimo = false;
        Boolean ContieneMayuscula = false;
        Boolean ContieneMiniscula = false;
        Boolean ContieneNumeros = false;
        Boolean Coinciden = false;
        Boolean UsuarioEnContraseña = false;
        Boolean ContieneEspecial = false;

        
        String especiales = "!@#$%&*";

        Scanner sc = new Scanner(System.in);

        System.out.println("Escriba el nombre de Usuario: ");
        NombreUser = sc.nextLine();

        System.out.println("Escriba la contraseña del Usuario: ");
        Contraseña = sc.nextLine();

        System.out.println("Escriba la contraseña de confirmacion del Usuario: ");
        ConfirmarContraseña = sc.nextLine();

        System.out.println("\n=== VALIDACIÓN DE CONTRASEÑA ===\n");

        if (Contraseña.length() < 8) {
            tamanoMinimo = false;
            System.out.println("introduce un mínimo de 8 caracteres");

        } else {
            System.out.println("Longitud mínima (8 caracteres): SÍ [" + Contraseña.length() + "caracteres]");
            tamanoMinimo = true;
        }

        for (int i = 0; i < Contraseña.length(); i++) {

            char c = Contraseña.charAt(i);

                 if (Character.isUpperCase(c)) {
                ContieneMayuscula = true;
            }
            if (Character.isLowerCase(c)) {
                ContieneMiniscula = true;
            }
            if (Character.isDigit(c)) {
                ContieneNumeros = true;
            }
            if (especiales.indexOf(c) != -1) {
                ContieneEspecial = true;
            }
        }

           if (ContieneMayuscula) {
            System.out.println("Contiene mayúsculas: SÍ");
        } else {
            System.out.println("Contiene mayúsculas: NO");
        }

        if (ContieneMiniscula) {
            System.out.println("Contiene minúsculas: SÍ");
        } else {
            System.out.println("Contiene minúsculas: NO");
        }

        if (ContieneNumeros) {
            System.out.println("Contiene números: SÍ");
        } else {
            System.out.println("Contiene números: NO");
        }

        if (Contraseña.equals(ConfirmarContraseña)) {
            Coinciden = true;
            System.out.println("Contraseñas coinciden: SÍ");

        } else {
            Coinciden = false;
            System.out.println("Contraseñas coinciden: NO");
        }

        if (Contraseña.contains(NombreUser.toLowerCase())) {
            UsuarioEnContraseña = false;
            System.out.println(" Contiene nombre de usuario: SÍ [Contiene '" + NombreUser + "']");
        } else {
            UsuarioEnContraseña = true;
            System.out.println(" Contiene nombre de usuario: NO");
        }

        if (tamanoMinimo && ContieneMayuscula && ContieneMiniscula && ContieneNumeros &&Coinciden && UsuarioEnContraseña) {
            
            if (Contraseña.length() < 10 ) {
                System.out.println("\nNivel de seguridad: DÉBIL");
                System.out.println("Estado: ✓ CONTRASEÑA ACEPTADA\n");

                System.out.println("Nota: Considera usar más de 10 caracteres para mayor seguridad.");
            } else if (Contraseña.length() >= 10 ) {
                
                  System.out.println("\nNivel de seguridad: MEDIA");
                System.out.println("Estado: ✓ CONTRASEÑA ACEPTADA\n");

                System.out.println("Sugerencia: Añade caracteres especiales (!@#$%&*) para hacerla más fuerte.");
            } else if (Contraseña.length() >= 12 && ContieneEspecial) {

                 System.out.println("\nNivel de seguridad: FUERTE");
                System.out.println("Estado: ✓ CONTRASEÑA ACEPTADA\n");

                System.out.println("¡Excelente! Tu contraseña es muy segura.");
            }
        } else{
            System.err.println("\nNivel de seguridad: N/A");
            System.err.println("Estado: ✗ CONTRASEÑA RECHAZADA\n");

            System.err.println("Problemas encontrados:");

            if (!tamanoMinimo) {
                System.out.println("- Debe tener al menos 8 caracteres");
            } else if (!ContieneMayuscula) {
                System.out.println("- Debe contener al menos una mayúscula");
                
            }else if (!ContieneMiniscula) {
                System.out.println("- Debe contener al menos una minúscula");
                
            }else if (!ContieneNumeros) {
                System.out.println("- Debe contener al menos un número");
                
            }else if (!Coinciden) {
                   System.out.println("- Debe coicidir con la Confirmación");
                
            }else if (!UsuarioEnContraseña) {
                   System.out.println("- No debe contener tu nombre de usuario");
            }
        }
    }

}
