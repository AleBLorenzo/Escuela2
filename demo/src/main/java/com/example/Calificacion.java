package com.example;

import java.time.LocalDate;

import com.google.gson.annotations.SerializedName;

public class Calificacion {

    @SerializedName("estudiante_id")
    private  int estudianteid ;
    private double nota;
    private LocalDate fecha;
    private String comentarios; 

    @Override
    public String toString() {
        return "Calificacion [estudianteid="+ estudianteid +"nota=" + nota + ", fecha=" + fecha + ", comentarios=" + comentarios + "]";
    }

    public Calificacion() {
    }

    public Calificacion(int estudianteid , String comentarios, LocalDate fecha, double nota) {
     this.estudianteid = estudianteid;
        this.comentarios = comentarios;
        this.fecha = fecha;
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getEstudianteid() {
        return estudianteid;
    }

    public void setEstudianteid(int estudianteid) {
        this.estudianteid = estudianteid;
    }


    
}
