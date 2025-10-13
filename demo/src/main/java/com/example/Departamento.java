package com.example;

import java.io.Serializable;
import java.util.List;

public class Departamento {

   private String nombre ;
  private  List<Empleado> empleados;

 
    @Override
    public String toString() {
        return "Departamento [nombre=" + nombre + ", empleados=" + empleados + "]";
    }

    public Departamento() {
    }

    public Departamento(List<Empleado> empleados, String nombre) {
        this.empleados = empleados;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
   
}
