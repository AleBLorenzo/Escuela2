package com.example.Eje6;

import java.time.LocalDateTime;
import java.util.List;

public class Venta {

    private LocalDateTime fecha;
    private Vendedor vendedor;
    private Cliente cliente;
    private List<Producto> producto;
    private double total;
    private MetodoPago metodo_pago;

    public Venta(Cliente cliente, LocalDateTime fecha, MetodoPago metodo_pago, List<Producto> producto, double total,Vendedor vendedor) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.metodo_pago = metodo_pago;
        this.producto = producto;
        this.total = total;
        this.vendedor = vendedor;
    }

    public Venta() {
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public MetodoPago getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(MetodoPago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta{");
        sb.append("fecha=").append(fecha);
        sb.append(", vendedor=").append(vendedor);
        sb.append(", cliente=").append(cliente);
        sb.append(", producto=").append(producto);
        sb.append(", total=").append(total);
        sb.append(", metodo_pago=").append(metodo_pago);
        sb.append('}');
        return sb.toString();
    }
     

}
