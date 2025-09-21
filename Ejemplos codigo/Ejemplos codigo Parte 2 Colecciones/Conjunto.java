import java.util.HashSet;

public class Conjunto {
    public static void main(String[] args) {

        //creamos un conjunto (sin duplicados, sin ordenar) con tabla hash (para accesos mas rapidos)
        HashSet<String> profes = new HashSet<>();

        // Añadir elementos
        profes.add("Clemente");
        profes.add("Miriam");
        profes.add("Alejandro");
        profes.add("Alejandro"); // Duplicado, se ignora xD
        profes.add("Ismael");
        profes.add("Raquel");
        profes.add("Sara");

        // Mostrar los profes
        System.out.println("Profesores en el conjunto de DAM: ");
        for (String nombre : profes) { //no podemos usar indices en los conjuntos
            System.out.println(nombre);
        }

        // Comprobar si existe un nombre
        String profe = "David";
        if (profes.contains(profe)) {
            System.out.println(profe + " está en el conjunto de DAM");
        }
        else {
           System.out.println(profe + " no está en el conjunto de DAM");
        }
    }
}
