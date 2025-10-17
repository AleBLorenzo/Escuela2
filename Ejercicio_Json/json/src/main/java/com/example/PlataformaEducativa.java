package com.example;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class PlataformaEducativa {
    @Expose
    private String nombre;

    @Expose
    private List<Curso> cursos;

    @Expose
    private List<Estudiante> estudiantes;

    public PlataformaEducativa(String nombre) {
        this.nombre = nombre;
        this.cursos = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }

    public void agregarCurso(Curso curso) {
        this.cursos.add(curso);
    }

    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
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

    @Override
    public String toString() {
        return "PlataformaEducativa{nombre='" + nombre + "', cursos=" + cursos.size() +
                ", estudiantes=" + estudiantes.size() + "}";
    }
}