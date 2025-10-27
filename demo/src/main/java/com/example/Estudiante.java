package com.example;

import java.time.LocalDate;

import com.google.gson.annotations.SerializedName;

public class Estudiante {

    private  int id;
    private String nombre;
    private transient  String email;

    @SerializedName ("fecha_nacimiento")
    private LocalDate fechaNacimiento;
    
    private String pais;

    @Override
    public String toString() {
        return "Estudiante [id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", pais=" + pais
                + "]";
    }

    public Estudiante() {
    }

    public Estudiante(String email, LocalDate fechaNacimiento, int id, String nombre, String pais) {
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    public  int getId() {
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    


}
