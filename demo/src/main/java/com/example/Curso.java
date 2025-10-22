package com.example;

import java.time.LocalDate;
import java.util.List;

public class Curso {

    private int id ;
    private String nombre;
    private String descripcion;
    private String instructor;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precio;
    private Boolean activo;
    private List<Calificacion> calificaciones;


    public Curso() {
    }

    public Curso(Boolean activo, String descripcion, LocalDate fechaFin, LocalDate fechaInicio, int id, String instructor, String nombre, double precio ,List<Calificacion> calificaciones) {
        this.activo = activo;
        this.descripcion = descripcion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.id = id;
        this.instructor = instructor;
        this.nombre = nombre;
        this.precio = precio;
        this.calificaciones= calificaciones;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
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
        StringBuilder sb = new StringBuilder();
        sb.append("Curso{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", instructor=").append(instructor);
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", fechaFin=").append(fechaFin);
        sb.append(", precio=").append(precio);
        sb.append(", activo=").append(activo);
        sb.append(", calificaciones=").append(calificaciones);
        sb.append('}');
        return sb.toString();
    }

}
