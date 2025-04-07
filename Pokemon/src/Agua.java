public class Agua extends Pokemon implements Ataque{


    public Agua(String name,int dano,int vida, int nivel, int vidaMaxima) {
        super(name, dano ,nivel, vida, vidaMaxima);
    }


    
    @Override
    public void atacar(Pokemon enemigo) {
      
        double danioFinal = dano;
    
        if (enemigo instanceof Fuego) {
            danioFinal *= 1.2; 
        } else if (enemigo instanceof Planta) {
            danioFinal *= 0.8; 
        } else if (enemigo instanceof Agua) {
            danioFinal *= 1; 
        }
        enemigo.vida -= danioFinal;
        if (enemigo.vida < 0)
            enemigo.vida = 0;
    
        System.out.println(this.nombre + " atacó a " + enemigo.nombre + " causando " + danioFinal + " de daño con  " +enemigo.vida + " de vida restante.");
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
