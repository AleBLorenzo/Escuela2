package com.example.Eje6;

public class Cliente {

    private TipoCliente tipo;
    private String nombre;
    private SegmentoCliente segmento;

    public Cliente() {
    }

    public Cliente(String nombre, SegmentoCliente segmento, TipoCliente tipo) {
        this.nombre = nombre;
        this.segmento = segmento;
        this.tipo = tipo;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SegmentoCliente getSegmento() {
        return segmento;
    }

    public void setSegmento(SegmentoCliente segmento) {
        this.segmento = segmento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("tipo=").append(tipo);
        sb.append(", nombre=").append(nombre);
        sb.append(", segmento=").append(segmento);
        sb.append('}');
        return sb.toString();
    }


}
