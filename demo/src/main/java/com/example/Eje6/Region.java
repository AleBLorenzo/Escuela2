package com.example.Eje6;

import java.util.List;

public class Region {

    private String nombre;
    private String pais;
    private String moneda;
    private List<Venta> ventas;

   

    public Region() {
    }

    public Region(String moneda, String nombre, String pais, List<Venta> ventas) {
        this.moneda = moneda;
        this.nombre = nombre;
        this.pais = pais;
        this.ventas = ventas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Region{");
        sb.append("nombre=").append(nombre);
        sb.append(", pais=").append(pais);
        sb.append(", moneda=").append(moneda);
        sb.append(", ventas=").append(ventas);
        sb.append('}');
        return sb.toString();
    }

   

}
