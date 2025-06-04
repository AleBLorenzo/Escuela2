package Ejercitacion.src;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

 class Persona implements Comparable<Persona> {

 private String nombre ;
  private int edad ;

 public String getNombre() {
    return nombre;
}

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

 public Persona ( String nombre, int edad){
    this.nombre = nombre;
    this.edad = edad;
 }

 @Override
  public int compareTo(Persona otra) {
        return Integer.compare(this.edad, otra.edad);
    }
}
class CompararNombre implements Comparator<Persona> {
 
  @Override
  public int compare(Persona p1 , Persona p2){
    return p1.getNombre().compareTo(p2.getNombre()) ;
  }

}
public class OrdenarPersonas {
    public static void main(String[] args) {
        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Ana", 30));
        personas.add(new Persona("Luis", 24));
        personas.add(new Persona("Carlos", 42));
        personas.add(new Persona("Marta", 28));

        // Ordenar por edad usando Comparable
        Collections.sort(personas , new CompararNombre() );

        // Mostrar personas ordenadas
        System.out.println("Personas ordenadas por edad:");
        for (Persona p : personas) {
            System.out.println(p);
        }
    }
}