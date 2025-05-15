public class Cubo3D {
    public static void main(String[] args) {
        int[][][] cubo = new int[3][3][3]; // z (capas), y (filas), x (columnas)

        // Inicializar con valores consecutivos
        int valor = 1;
        for (int z = 0; z < cubo.length; z++) {           // profundidad
            for (int y = 0; y < cubo[z].length; y++) {    // vertical
                for (int x = 0; x < cubo[z][y].length; x++) { // horizontal
                    cubo[z][y][x] = valor++;
                }
            }
        }

        // Mostrar el cubo
        for (int z = 0; z < cubo.length; z++) {
            System.out.println("Capa Z = " + z + ":");
            for (int y = 0; y < cubo[z].length; y++) {
                for (int x = 0; x < cubo[z][y].length; x++) {
                    System.out.print(cubo[z][y][x] + "\t");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
