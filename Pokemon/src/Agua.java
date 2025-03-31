public class Agua extends Pokemon{


    public Agua (String name, String vida, int nivel, int vidaMaxima) {
        super(name, nivel, vida, vidaMaxima);
    }


    @Override
    public void Sonido() {
        System.out.println("Glup-glup-gliiishhh");
    }
    
}
