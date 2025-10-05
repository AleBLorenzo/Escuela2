package Fichero3.src;

import java.io.Serializable;
import java.time.LocalDate;

public class Autor implements Serializable {

    private String nombre;
    private String apellidos;
    private String nacionalidad;
    private LocalDate fechaN;

 

    public Autor(String apellidos, String nacionalidad, String nombre, LocalDate fechaN) {
        this.apellidos = apellidos;
        this.fechaN = fechaN;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
    }

    public Autor() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaN() {
        return fechaN;
    }

    public void setFechaN(LocalDate fechaN) {
        this.fechaN = fechaN;
    }

       @Override
    public String toString() {
        return "Autor [nombre=" + nombre + ", apellidos=" + apellidos + ", nacionalidad=" + nacionalidad + ", fechaN="
                + fechaN + "]";
    }

}
