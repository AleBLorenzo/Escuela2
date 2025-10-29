package com.example;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Empresa implements Serializable{

    private String empresa;
    private String ubicacion;
    private List<Departamento> departamentos;

    public Empresa() {
    }

    public Empresa(List<Departamento> departamentos, String empresa, String ubicacion) {
        this.departamentos = departamentos;
        this.empresa = empresa;
        this.ubicacion = ubicacion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa{");
        sb.append("empresa=").append(empresa);
        sb.append(", ubicacion=").append(ubicacion);
        sb.append(", departamentos=").append(departamentos);
        sb.append('}');
        return sb.toString();
    }

}
