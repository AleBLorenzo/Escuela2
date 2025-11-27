package com.example;

public class Acceso {

    private String fecha;
    private String hora;
    private String ip_cliente;
    private String metodo;
    private String recurso;
    private int codigo_estado;

    public Acceso() {
    }

    public Acceso(int codigo_estado, String fecha, String hora, String ip_cliente, String metodo, String recurso) {
        this.codigo_estado = codigo_estado;
        this.fecha = fecha;
        this.hora = hora;
        this.ip_cliente = ip_cliente;
        this.metodo = metodo;
        this.recurso = recurso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getIp_cliente() {
        return ip_cliente;
    }

    public void setIp_cliente(String ip_cliente) {
        this.ip_cliente = ip_cliente;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public int getCodigo_estado() {
        return codigo_estado;
    }

    public void setCodigo_estado(int codigo_estado) {
        this.codigo_estado = codigo_estado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Acceso{");
        sb.append("fecha=").append(fecha);
        sb.append(", hora=").append(hora);
        sb.append(", ip_cliente=").append(ip_cliente);
        sb.append(", metodo=").append(metodo);
        sb.append(", recurso=").append(recurso);
        sb.append(", codigo_estado=").append(codigo_estado);
        sb.append('}');
        return sb.toString();
    }
    
}
