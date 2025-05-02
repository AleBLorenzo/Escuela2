import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.Serializable;
import java.util.StringTokenizer;

public class Main implements Serializable {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {

        Pokemon Squirtle = new Agua("Squirtle", 15, 45, 15, 50);
        Pokemon Psyduck = new Agua("Psyduck", 18, 60, 20, 65);
        Pokemon Totodile = new Agua("Totodile", 20, 55, 18, 60);
        Pokemon Charmander = new Fuego("Charmander", 20, 55, 18, 60);
        Pokemon Growlithe = new Fuego("Growlithe", 18, 65, 22, 70);
        Pokemon Torchic = new Fuego("Torchic", 15, 50, 17, 55);
        Pokemon Bulbasaur = new Planta("Bulbasaur", 18, 65, 20, 70);
        Pokemon Chikorita = new Planta("Chikorita", 15, 48, 16, 52);
        Pokemon Treecko = new Planta("Treecko", 20, 58, 19, 62);

        ArrayList<Pokemon> equipo1 = new ArrayList<>();
        equipo1.add(Squirtle);
        equipo1.add(Psyduck);

        Scanner scanner = new Scanner(System.in);

        Entrenador entrenador = new Entrenador("Alejandro", 10, equipo1);

        Pokemon enemigo;

        ArrayList<Pokemon> listaPokemon = new ArrayList<>();
        listaPokemon.add(Squirtle);
        listaPokemon.add(Psyduck);
        listaPokemon.add(Totodile);
        listaPokemon.add(Charmander);
        listaPokemon.add(Growlithe);
        listaPokemon.add(Torchic);
        listaPokemon.add(Bulbasaur);
        listaPokemon.add(Chikorita);
        listaPokemon.add(Treecko);

        LocalDateTime TiempoI = LocalDateTime.now();
        int vecesjugadas = 0;

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Inventario");
            System.out.println("2. Atacar");
            System.out.println("3. Capturar");
            System.out.println("4. Escapar");
            System.out.println("5. Guardar Partida");
            System.out.println("6. Cargar Partida");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Inventario: " + entrenador);
                    break;
                case 2:
                    System.out.println("Elige un Pokemon para atacar:");

                    for (int i = 0; i < entrenador.equipo.size(); i++) {
                        System.out.println((i) + ". " + entrenador.equipo.get(i).nombre);
                    }

                    int eleccionAtacar = scanner.nextInt();
                    Pokemon pokemonAtaca = entrenador.equipo.get(eleccionAtacar);

                    System.out.println("Has elegido a " + pokemonAtaca.nombre);

                    Random random = new Random();
                    int eleccionEnemigo = random.nextInt(listaPokemon.size());
                    enemigo = listaPokemon.get(eleccionEnemigo);

                    System.out.println("Te encontraste a " + enemigo.nombre);
                    pokemonAtaca.atacar(enemigo);

                    if (enemigo.vida <= 0) {
                        System.out.println("Has derrotado a " + enemigo.nombre);
                        listaPokemon.remove(enemigo);
                    }
                    vecesjugadas++;
                    break;
                case 3:
                    System.out.println("Elige un Pokemon para capturar:");
                    for (int i = 0; i < listaPokemon.size(); i++) {
                        System.out.println((i) + ". " + listaPokemon.get(i).nombre);
                    }

                    int eleccionCaptura = scanner.nextInt();
                    Pokemon captura = listaPokemon.get(eleccionCaptura);
                    entrenador.capturar(captura);
                    vecesjugadas++;
                    break;
                case 4:
                    System.out.println("Has escapado");

                    return;
                case 5:
                    try {
                        LocalDateTime TiempoF = LocalDateTime.now();
                        int tiempoTranscurrido = TiempoF.getMinute() - TiempoI.getMinute();
                        System.out.println("Guardando partida");
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("datos.dat"));

                        oos.writeObject(equipo1);
                        oos.writeInt(tiempoTranscurrido);
                        oos.writeObject(TiempoI);
                        oos.write(vecesjugadas);
                        oos.close();

                        System.out.println("Partida guardada con exito");
                        System.out.println("Tiempo de juego: " + tiempoTranscurrido + " minutos");
                        System.out.println("Partida guardada el " + TiempoI);
                        System.out.println("Partida guardada con " + equipo1);
                        System.out.println("Partida guardada con " + vecesjugadas + " veces jugadas");

                    } catch (Exception e) {
                        System.err.println("Error al escribir: " + e.getMessage());
                    }

                    break;
                case 6:
                    try {
                        System.out.println("Cargando partida");
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("datos.dat"));
                        ArrayList<Pokemon> equipoCargado = (ArrayList<Pokemon>) ois.readObject();
                        int tiempoTranscurridoCargado = ois.readInt();
                        LocalDateTime TiempoICargado = (LocalDateTime) ois.readObject();
                        int vecesjugadasCargadas = ois.read();

                        System.out.println("Tiempo de juego " + tiempoTranscurridoCargado + " minutos");
                        System.out.println("Partida guardada el " + TiempoICargado);
                        System.out.println("Partida guardada con " + equipoCargado);
                        System.out.println("Partida guardada con " + vecesjugadasCargadas + " veces jugadas");

                        ois.close();
                        System.out.println("Partida cargada con exito");

                    } catch (Exception e) {
                        System.err.println("Error al leer: " + e.getMessage());
                    }

                    break;
                default:
                    System.out.println("Selecione un numero correcto");
            }
        }
    }
}