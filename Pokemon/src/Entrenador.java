import java.io.Serializable;
import java.util.ArrayList;

public class Entrenador implements Serializable {

    public String nombre;
    public int pokeballs;
    public int pociones;

    public ArrayList<Pokemon> equipo = new ArrayList<>();

    public Entrenador(String nombre, int pokeballs, ArrayList<Pokemon> equipo, int pociones) {
        this.nombre = nombre;
        this.pokeballs = pokeballs;
        this.equipo = equipo;
        this.pociones = pociones;
    }

    public void capturar(Pokemon captura) {

        double probabilidad = 0.5 * ((double) captura.vida / captura.vidaMaxima);
        double numeroAleatorio = Math.random();

        if (numeroAleatorio < probabilidad) {
            if (equipo.size() <= 6) {
                equipo.add(captura);
                System.out.println("Has capturado a " + captura.toString());
            } else {
                System.out.println("Estas lleno no puedes capturar más Pokemon");
            }
        } else {
            System.out.println("La captura fallo " + pokeballs);
            pokeballs--;
        }
        if (pokeballs <= 0) {
            System.out.println("No tienes Pokeballs suficientes");
        }

    }

    public void curarPociones(Pokemon atacante, int pociones, int vidaMaxima) {

        pociones = 1 * atacante.vidaMaxima;

    }

    public String toString() {
        return "\nNombre: " + this.nombre + "\nPokeballs: " + this.pokeballs + "\nEquipo: " + this.equipo;
    }

    public ArrayList<Pokemon> getEquipo() {
        return equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPokeballs() {
        return pokeballs;
    }

    public void setPokeballs(int pokeballs) {
        this.pokeballs = pokeballs;
    }

    public void setEquipo(ArrayList<Pokemon> equipo) {
        this.equipo = equipo;
    }
}