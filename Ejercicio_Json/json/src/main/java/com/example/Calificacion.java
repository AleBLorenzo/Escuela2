package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Calificacion {
    @Expose
    @SerializedName("estudiante_id")
    private int estudianteId;

    @Expose
    private double nota;

    @Expose
    private Date fecha;

    @Expose
    private String comentarios;

    public Calificacion(int estudianteId, double nota, Date fecha, String comentarios) {
        this.estudianteId = estudianteId;
        this.nota = nota;
        this.fecha = fecha;
        this.comentarios = comentarios;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Calificacion{estudianteId=" + estudianteId + ", nota=" + nota + "}";
    }
}