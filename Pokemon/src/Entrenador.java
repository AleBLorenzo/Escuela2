
import java.util.ArrayList;


public class Entrenador  {

    public String nombre;
    public int pokeballs;
    public ArrayList<Pokemon> equipo;

    public Entrenador(String nombre, int pokeballs) {
        this.nombre = nombre;
        this.pokeballs = pokeballs;
        this.equipo = new ArrayList<>();
    }

    public void capturar(Pokemon listaPokemon) {

        double probabilidad = 0.5 * (listaPokemon.vida / listaPokemon.vidaMaxima);
        double numeroAleatorio = Math.random();

        if (numeroAleatorio < probabilidad) {
            if (equipo.size() <= 6) {
                equipo.add(listaPokemon);
                System.out.println("Has capturado a " + listaPokemon.nombre );
            } else {
                System.out.println("Estas lleno no puedes capturar más Pokemon");
            }
        } else {
            System.out.println("La captura fallo "+ pokeballs);
            pokeballs--;
        }
        if (pokeballs <= 0) {
            System.out.println("No tienes Pokeballs suficientes");
        }

    }

    public ArrayList<Pokemon> getEquipo() {
        return equipo;
    }
}