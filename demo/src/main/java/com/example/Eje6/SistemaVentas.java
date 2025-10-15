package com.example.Eje6;

import java.util.List;

public class SistemaVentas {

    private String empresa;
    private String periodo;
    private List<Region> region;

    public SistemaVentas() {
    }

    public SistemaVentas(String empresa, String periodo, List<Region> region) {
        this.empresa = empresa;
        this.periodo = periodo;
        this.region = region;
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
        return region;
    }

    public void setRegion(List<Region> region) {
        this.region = region;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SistemaVentas{");
        sb.append("empresa=").append(empresa);
        sb.append(", periodo=").append(periodo);
        sb.append(", region=").append(region);
        sb.append('}');
        return sb.toString();
    }

}
