package com.example;

import java.io.Serializable;

public class RegistroInvetario implements Serializable{

    private int id ;
    private String descripcion;
    private double valor ;

    public RegistroInvetario() {
    }

    public RegistroInvetario(String descripcion, int id, double valor) {
        this.descripcion = descripcion;
        this.id = id;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RegistroInvetario{");
        sb.append("id=").append(id);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", valor=").append(valor);
        sb.append('}');
        return sb.toString();
    }


}
