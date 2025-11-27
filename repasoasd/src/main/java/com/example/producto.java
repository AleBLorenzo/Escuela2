package com.example;

public class producto {

    private String nombre;
    private Double precio;

    public producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public producto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
