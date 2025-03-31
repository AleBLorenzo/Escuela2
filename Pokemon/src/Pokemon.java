public abstract class Pokemon {

    public String nombre;
    public int nivel;
    public String vida;
    public int vidaMaxima;



    public Pokemon(String nombre, int nivel, String vida, int vidaMaxima) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
    }

    

    public  String toString(){
        return "Nombre: " + this.nombre + "\nNivel: " + this.nivel + "\nVida: " + this.vida + "\nVidaMaxima: " + this.vidaMaxima + "\n"+getClass().getName();
    };

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


    public String getVida() {
        return vida;
    }


    public void setVida(String vida) {
        this.vida = vida;
    }


    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

}
