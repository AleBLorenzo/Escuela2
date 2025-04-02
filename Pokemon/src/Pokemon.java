public abstract class Pokemon {

    public String nombre;
    public int nivel;
    public int vida;
    public int vidaMaxima;
    public int dano;

    public Pokemon(String nombre, int dano, int nivel, int vida, int vidaMaxima) {
        this.nombre = nombre;
        this.dano = dano;
        this.nivel = nivel;
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
    }

    public String toString() {
        return "Nombre: " + this.nombre + "\nDaño: " + this.dano + "\nNivel: " + this.nivel + "\nVida: " + this.vida
                + "\nVidaMaxima: " + this.vidaMaxima;
    };

    public abstract void Sonido();

}
