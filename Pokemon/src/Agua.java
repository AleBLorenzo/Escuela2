public class Agua extends Pokemon implements Ataque{


    public Agua(String name,int dano,int vida, int nivel, int vidaMaxima) {
        super(name, dano ,nivel, vida, vidaMaxima);
    }


    
    @Override
    public void atacar(Pokemon enemigo) {
      
        double danioFinal = dano;
    
        if (enemigo instanceof Fuego) {
            danioFinal *= 1.2; // +20% contra Planta
        } else if (enemigo instanceof Planta) {
            danioFinal *= 0.8; // -20% contra Agua
        }
    
        enemigo.vida -= danioFinal;
        if (enemigo.vida < 0) enemigo.vida = 0;
    
        System.out.println(this.nombre + " atacó a " + enemigo.nombre + " causando " + danioFinal + " de daño.");
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + "\nDaño: " + this.dano + "\nNivel: " + this.nivel + "\nVida: " + this.vida + "\nVidaMaxima: "
                + this.vidaMaxima;
    }

    @Override
    public void Sonido() {
        System.out.println("Glup-glup-gliiishhh");
    }
    
}
