
import java.util.ArrayList;


public class Entrenador  {

    public String nombre;
    public int pokeballs;
    public ArrayList<Pokemon> equipo = new ArrayList<>();
    
  
    public Entrenador(String nombre, int pokeballs, ArrayList<Pokemon> equipo) {
        this.nombre = nombre;
        this.pokeballs = pokeballs;
        this.equipo = equipo;
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