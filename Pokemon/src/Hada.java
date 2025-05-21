import java.io.Serializable;

public class Hada extends Pokemon implements Ataque, Serializable, Cura {

    public Hada(String name, int dano, int vida, int nivel, int vidaMaxima) {
        super(name, dano, nivel, vida, vidaMaxima);
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

        System.out.println(this.nombre + " atacó a " + enemigo.nombre + " causando " + danioFinal + " de daño con  "
                + enemigo.vida + " de vida restante.");
    }

    @Override
    public void curar(Pokemon atacante) {
        double curacion = vida * 0.2;

        if (vida <= vidaMaxima) {
            vida += curacion;
            System.out.println("Pokemon curado");
        } else if (vida == vidaMaxima) {

            System.out.println("Esta lleno de vida");
        }

        System.out.println(this.nombre + " se curo con  " + atacante.vida);

    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + "\nDaño: " + this.dano + "\nNivel: " + this.nivel + "\nVida: " + this.vida
                + "\nVidaMaxima: "
                + this.vidaMaxima;
    }

    @Override
    public void Sonido() {
        System.out.println("Glup-glup-gliiishhh");
    }

}
