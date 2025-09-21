import java.util.TreeSet;

public class Persona implements Comparable<Persona> {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public int compareTo(Persona persona) { //compara dos personas para ordenarlas por el nombre
        return this.nombre.compareTo(persona.nombre);
    }

    @Override
    public String toString() {
        return nombre + ", " + edad + " años";
    }

    public static void main(String[] args) {
        TreeSet<Persona> personas = new TreeSet<>();
        personas.add(new Persona("Miriam", 15));
        personas.add(new Persona("Alejandro", 15));

        //al solo ordenarse por nombre, se considera un duplicado y no se añade
        personas.add(new Persona("Alejandro", 20));

        personas.add(new Persona("Raquel", 15));
        personas.add(new Persona("Clemente", 20));
        personas.add(new Persona("Ismael", 20));
        personas.add(new Persona("Sara", 20));


        System.out.println("Personas ordenadas por nombre: ");
        for (Persona p : personas) { //aparecerán ordenador por nombre
            System.out.println(p);
        }
    }
}
