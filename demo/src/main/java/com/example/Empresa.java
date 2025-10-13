package com.example;

import java.io.Serializable;
import java.util.List;

public class Empresa {

   private String empresa;
   private List<Departamento> departamentos;

    public Empresa(List<Departamento> departamentos, String empresa) {
        this.departamentos = departamentos;
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Empresa [empresa=" + empresa + ", departamento=" + departamentos + "]";
    }

    public Empresa() {
    }



    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }




}
