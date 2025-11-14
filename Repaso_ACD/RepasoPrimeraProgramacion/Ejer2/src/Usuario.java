
import java.io.Serializable;

public class Usuario implements Serializable{
    
    private int edad;
    private String nombre;
    private boolean premiun;

    public Usuario() {
    }

    public Usuario(int edad, String nombre, boolean premiun) {
        this.edad = edad;
        this.nombre = nombre;
        this.premiun = premiun;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isPremiun() {
        return premiun;
    }

    public void setPremiun(boolean premiun) {
        this.premiun = premiun;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("edad=").append(edad);
        sb.append(", nombre=").append(nombre);
        sb.append(", premiun=").append(premiun);
        sb.append('}');
        return sb.toString();
    }

}
