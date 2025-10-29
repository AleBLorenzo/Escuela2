package com.example;

import java.io.Serializable;
import java.util.List;

public class Departamento implements Serializable{

    private String nombre;
     private List<Proyecto> proyectos;
     private List<Empleado> empleados;

    public Departamento(List<Empleado> empleados, String nombre, List<Proyecto> proyectos) {
        this.empleados = empleados;
        this.nombre = nombre;
        this.proyectos = proyectos;
    }

    public Departamento() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Departamento{");
        sb.append("nombre=").append(nombre);
        sb.append(", proyectos=").append(proyectos);
        sb.append(", empleados=").append(empleados);
        sb.append('}');
        return sb.toString();
    }

}
