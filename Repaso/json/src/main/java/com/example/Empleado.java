package com.example;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Empleado implements Serializable {
    
    private int id;
    private String nombre;
    private String email;
    private double salario;
    private LocalDate fechaContratacion;
    private boolean activo;
    private List<String> habilidades;

    public Empleado(boolean activo, String email, LocalDate fechaContratacion, List<String> habilidades, int id, String nombre, double salario) {
        this.activo = activo;
        this.email = email;
        this.fechaContratacion = fechaContratacion;
        this.habilidades = habilidades;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empleado{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", email=").append(email);
        sb.append(", salario=").append(salario);
        sb.append(", fechaContratacion=").append(fechaContratacion);
        sb.append(", activo=").append(activo);
        sb.append(", habilidades=").append(habilidades);
        sb.append('}');
        return sb.toString();
    }

}
