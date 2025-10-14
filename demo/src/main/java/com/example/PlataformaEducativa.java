package com.example;

import java.util.List;

public class PlataformaEducativa {

    private String nombre ;
    private List<Curso> cursos;
    private List<Estudiante> estudiantes;
    private List<Calificacion> calificaciones;



    @Override
    public String toString() {
        return "PlataformaEducativa [nombre=" + nombre + ", cursos=" + cursos + ", estudiantes=" + estudiantes
                + ", calificaciones=" + calificaciones + "]";
    }

    public PlataformaEducativa() {
    }

    public PlataformaEducativa(List<Calificacion> calificaciones, List<Curso> cursos, List<Estudiante> estudiantes, String nombre) {
        this.calificaciones = calificaciones;
        this.cursos = cursos;
        this.estudiantes = estudiantes;
        this.nombre = nombre;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }




}
