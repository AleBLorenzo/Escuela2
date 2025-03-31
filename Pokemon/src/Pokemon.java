public abstract class Pokemon {

    private String nombre;
    private int nivel;
    private String vida;
    private int vidaMaxima;



    public Pokemon(String nombre, int nivel, String vida, int vidaMaxima) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
    }

 public abstract String toString();

    public abstract void Sonido();
}
