package com.example;

import java.io.Serializable;

public class Libro implements Serializable {

    private String isbn;
    private String titulo;
    private String autor;
    private int anoPublicacion;
    private int numeroPaginas;
    private double precio;
    private boolean disponible;

    public Libro() {
    }

    public Libro( String isbn, String titulo, String autor,int anoPublicacion, int numeroPaginas, double precio ,boolean disponible
           ) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.numeroPaginas = numeroPaginas;
        this.precio = precio;
        this.disponible = disponible;

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Libro{");
        sb.append("isbn=").append(isbn);
        sb.append(", titulo=").append(titulo);
        sb.append(", autor=").append(autor);
        sb.append(", anoPublicacion=").append(anoPublicacion);
        sb.append(", numeroPaginas=").append(numeroPaginas);
        sb.append(", precio=").append(precio);
        sb.append(", disponible=").append(disponible);
        sb.append('}');
        return sb.toString();
    }

}
