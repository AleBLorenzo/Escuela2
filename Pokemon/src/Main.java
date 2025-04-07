import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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

        Entrenador entrenador = new Entrenador("Alejandro", 5, equipo1);

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

    

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Inventario");

            System.out.println("2. Atacar");
            System.out.println("3. Capturar");
            System.out.println("4. Escapar");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Inventario: " + entrenador);
                    break;
                case 2:
                    System.out.println("Elige un Pokémon para atacar:");

                    for (int i = 0; i < entrenador.equipo.size(); i++) {
                        System.out.println((i) + ". " + entrenador.equipo.get(i).nombre);
                    }

                    int eleccionAtacar = scanner.nextInt();
                    Pokemon pokemonAtaca = entrenador.equipo.get(eleccionAtacar);

                    System.out.println("Has elegido a " + pokemonAtaca.nombre);
                    System.out.println("Elige un Pokémon a atacar:");

                    for (int i = 0; i < listaPokemon.size(); i++) {
                        System.out.println((i) + ". " + listaPokemon.get(i).nombre);
                    }

                    int eleccionEnemigo = scanner.nextInt();
                    Pokemon enemigo = listaPokemon.get(eleccionEnemigo);

                    System.out.println("Has elegido a " + enemigo.nombre);
                    pokemonAtaca.atacar(enemigo);

                    if (enemigo.vida <= 0) {
                        System.out.println("Has derrotado a " + enemigo.nombre);
                        listaPokemon.remove(eleccionEnemigo);
                    }
                    break;
                case 3:
                    entrenador.capturar();
                    break;
                case 4:
                    System.out.println("Has escapado");
                    return;
                default:
                    System.out.println("Selecione un numero correcto");
            }
        }
    }
}