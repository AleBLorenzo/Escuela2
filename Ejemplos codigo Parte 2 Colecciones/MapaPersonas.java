import java.util.HashMap;
import java.util.Map;


public class MapaPersonas {
    public static void main(String[] args) {
        // Mapa con clave DNI y valor nombre
        HashMap<String, String> personas = new HashMap<>();
        personas.put("12345678A", "Pepito");
        personas.put("98765432B", "Juan");
        personas.put("55555555C", "Carlos");
        System.out.println("\nTotal de personas registradas: " + personas.size());

        // Recorrido 1: entrada (clave y valor)
        System.out.println("Recorrido con entradas (k, v):");
        for (Map.Entry<String, String> entrada : personas.entrySet()) {
            System.out.println("DNI " + entrada.getKey() + ": " + entrada.getValue());
        }

        // Recorrido 2: por clave
        System.out.println("\nRecorrido por clave:");
        for (String dni : personas.keySet()) {
            System.out.println("DNI " + dni + ": " + personas.get(dni));
        }

        // Recorrido 3: por valores
        System.out.println("\nRecorrido por valor:");
        for (String nombre : personas.values()) {
            System.out.println("Nombre: " + nombre);
        }

        // Busqueda
        String dniBuscado = "12345678A";
        if (personas.containsKey(dniBuscado)) {
            System.out.println("\nExiste el DNI " + dniBuscado + ": " + personas.get(dniBuscado));
        } else {
            System.out.println("\nNo se encontró el DNI " + dniBuscado);
        }

        // Eliminar
        personas.remove("55555555C");
        System.out.println("\nSe ha eliminado el DNI 55555555C.");
        System.out.println("\nTotal de personas registradas: " + personas.size());
    }
}
