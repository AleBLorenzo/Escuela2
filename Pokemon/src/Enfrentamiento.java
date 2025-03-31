
import java.lang.reflect.Array;

public class Enfrentamiento extends Pokemon {


    ArrayList<Pokemon> pokemones = new ArrayList<>(),
   
        pokemones.add(new Fuego("Charmander", "55", 18, 60));
        pokemones.add(new Agua("Squirtle", "50", 16, 55));
        pokemones.add(new Planta("Bulbasaur", "45", 15, 50));
        add(new Fuego("Growlithe", "65", 22, 70));
        add(new Agua("Psyduck", "50", 17, 55));
        add(new Planta("Chikorita", "60", 20, 65));
        add(new Fuego("Torchic", "50", 17, 55));
  
    
    public void atacar (){
        System.out.println("Atacando al enemigo");
        
    }


}
