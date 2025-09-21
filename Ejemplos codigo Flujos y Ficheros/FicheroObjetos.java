import java.io.*;

// Clase Persona que implementa Serializable para poder ser leida/escrita a fichero
class Persona implements Serializable {
    private String nombre;
    private String dni;

    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }
}

public class FicheroObjetos {
    public static void main(String[] args) {

        Persona p1 = new Persona("Pepito", "12345678Z");
        Persona p2 = new Persona("Maria", "87654321A");

        // Escribir los objetos a fichero
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("personas.dat"))) {
            oos.writeObject(p1);
            oos.writeObject(p2);
            System.out.println("Personas guardadas correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }

        // Leer los objetos en hasta que se llegue al final del fichero
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personas.dat"))) {
            while (true) {
                Persona p = (Persona) ois.readObject();
                System.out.println("Nombre: " + p.getNombre() + ", DNI: " + p.getDni());
            }
        } catch (EOFException e) {
            System.out.println("Fin del archivo.");
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Clase no encontrada: " + e.getMessage());
        }
    }
}
