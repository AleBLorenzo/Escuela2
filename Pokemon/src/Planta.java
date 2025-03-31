public class Planta extends Pokemon{


    public Planta(String name, String vida, int nivel, int vidaMaxima) {
        super(name, nivel, vida, vidaMaxima);
    }

   

    @Override
    public void Sonido() {
        System.out.println("Swiiish-crackle-crrrp");
    }
    
}
