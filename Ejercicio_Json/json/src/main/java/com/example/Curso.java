package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Curso {
    @Expose
    private int id;

    @Expose
    private String nombre;

    @Expose
    private String descripcion;

    @Expose
    private String instructor;

    @Expose
    @SerializedName("fecha_inicio")
    private Date fechaInicio;

    @Expose
    @SerializedName("fecha_fin")
    private Date fechaFin;

    @Expose
    private double precio;

    @Expose
    private boolean activo;

    @Expose
    private List<Calificacion> calificaciones;

    public Curso(int id, String nombre, String descripcion, String instructor,
            Date fechaInicio, Date fechaFin, double precio, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.instructor = instructor;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.activo = activo;
        this.calificaciones = new ArrayList<>();
    }

    public void agregarCalificacion(Calificacion calificacion) {
        this.calificaciones.add(calificacion);
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    @Override
    public String toString() {
        return "Curso{id=" + id + ", nombre='" + nombre + "', instructor='" + instructor + "'}";
    }
}