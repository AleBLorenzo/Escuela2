
import java.util.Scanner;

public class Ejer4 {

    public static void main(String[] args) {

        String NombreUser;
        String Contraseña = null;
        String ConfirmarContraseña;

        Boolean tamanoMinimo = null;
        Boolean ContieneMayuscula = null;
        Boolean ContieneMiniscula = null;
        Boolean ContieneNumeros = null;
        Boolean Coinciden = null;
        Boolean UsuarioEnContraseña = null;

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

            if (Character.isUpperCase(Contraseña.charAt(i))) {
                System.out.println("Contiene mayúsculas: SÍ [" + Contraseña.charAt(i) + "]");
                ContieneMayuscula = true;
            } else {
                System.out.println("\"Contiene mayúsculas: NO");
                ContieneMayuscula = false;
            }

            if (Character.isLowerCase(Contraseña.charAt(i))) {
                ContieneMiniscula = true;
                System.out.println("Contiene minúsculas: SÍ [" + Contraseña.charAt(i) + "]");
            } else {
                System.out.println("\"Contiene minúsculas: NO");
                ContieneMiniscula = false;
            }

            if (Character.isDigit(Contraseña.charAt(i))) {
                ContieneNumeros = true;
                System.out.println("Contiene números: SÍ [" + Contraseña.charAt(i) + "]");
            } else {
                System.out.println("\"Contiene números: NO");
                ContieneNumeros = false;
            }
        }

        if (Contraseña.equals(ConfirmarContraseña)) {
            Coinciden = true;
            System.out.println("Contraseñas coinciden: SÍ");

        } else {
            Coinciden = false;
            System.out.println("Contraseñas coinciden: NO");
        }

        if (Contraseña.contains(NombreUser)) {
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
            } else if (Contraseña.length() > 10 ) {
                
                  System.out.println("\nNivel de seguridad: MEDIA");
                System.out.println("Estado: ✓ CONTRASEÑA ACEPTADA\n");

                System.out.println("Sugerencia: Añade caracteres especiales (!@#$%&*) para hacerla más fuerte.");
            } else if (Contraseña.contains("!@#$%&*")) {

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
