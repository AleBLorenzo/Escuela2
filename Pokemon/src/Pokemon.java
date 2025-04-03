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

    public void atacar(Pokemon enemigo) {
        // Lógica del ataque
        System.out.println("Atacando a " + enemigo.getNombre());
    }

    public abstract void Sonido();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

}
