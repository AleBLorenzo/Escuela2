package com.example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Empleado {

   private int id ;
   private String nombre;
   private String email;
   private double salario;
   private String fechaContratacion;
   private Boolean activo;
   private List<String> habilidades;


    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombre=" + nombre + ", email=" + email + ", salario=" + salario
                + ", fechaContratacion=" + fechaContratacion + ", activo=" + activo + ", habilidades=" + habilidades
                + "]";
    }

    public Empleado() {
    }

    public Empleado(Boolean activo, String email, String fechaContratacion, List<String> habilidades, int id, String nombre, double salario) {
        this.activo = activo;
        this.email = email;
        this.fechaContratacion = fechaContratacion;
        this.habilidades = habilidades;
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
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

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }

}
