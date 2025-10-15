package com.example.Eje6;

public class Vendedor {

    private String id;
    private String nombre;
    private double comision;

    public Vendedor() {
    }

    public Vendedor(double comision, String id, String nombre) {
        this.comision = comision;
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vendedor{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", comision=").append(comision);
        sb.append('}');
        return sb.toString();
    }


}
