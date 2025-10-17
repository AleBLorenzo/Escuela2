package com.example.Eje6;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SistemaVentas {

    private String empresa;
    private String periodo;
    private List<Region> regiones;

    public SistemaVentas() {
    }

    public SistemaVentas(String empresa, String periodo, List<Region> regiones) {
        this.empresa = empresa;
        this.periodo = periodo;
        this.regiones = regiones;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public List<Region> getRegion() {
        return regiones;
    }

    public void setRegion(List<Region> region) {
        this.regiones = region;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SistemaVentas{");
        sb.append("empresa=").append(empresa);
        sb.append(", periodo=").append(periodo);
        sb.append(", region=").append(regiones);
        sb.append('}');
        return sb.toString();
    }

}
