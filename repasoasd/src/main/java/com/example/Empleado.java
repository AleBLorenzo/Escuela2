package com.example;

public class Empleado {

    private int id ;
     private String nombre ;
      private int salario ;

    public Empleado(int id, String nombre, int salario) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
    }

    public Empleado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", salario=").append(salario);
        sb.append('}');
        return sb.toString();
    }

}
