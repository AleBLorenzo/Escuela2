package com.example.Eje6;

public class Producto {

    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio_unitario;
    private double descuento;

    public Producto() {
    }

    public Producto(int cantidad, String codigo, double descuento, String nombre, double precio_unitario) {
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.descuento = descuento;
        this.nombre = nombre;
        this.precio_unitario = precio_unitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{");
        sb.append("codigo=").append(codigo);
        sb.append(", nombre=").append(nombre);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", precio_unitario=").append(precio_unitario);
        sb.append(", descuento=").append(descuento);
        sb.append('}');
        return sb.toString();
    }

}
