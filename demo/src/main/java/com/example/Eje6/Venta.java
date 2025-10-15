package com.example.Eje6;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

public class Venta {

    private OffsetDateTime fecha;
    private List<Vendedor> vendedor;
    private List<Cliente> cliente;
    private List<Producto> producto;
    private double total;
    private MetodoPago metodo_pago;

    public Venta(List<Cliente> cliente, OffsetDateTime fecha, MetodoPago metodo_pago, List<Producto> producto, double total, List<Vendedor> vendedor) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.metodo_pago = metodo_pago;
        this.producto = producto;
        this.total = total;
        this.vendedor = vendedor;
    }

    public Venta() {
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public List<Vendedor> getVendedor() {
        return vendedor;
    }

    public void setVendedor(List<Vendedor> vendedor) {
        this.vendedor = vendedor;
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
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
