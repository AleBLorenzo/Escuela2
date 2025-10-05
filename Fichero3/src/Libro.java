package Fichero3.src;

import java.io.Serializable;
import java.time.LocalDate;

public class Libro  implements Serializable {


    private String ISBN;
    private String titulo;
    private Autor autor ;
    private int paginas; 
    private boolean disponible;
    private LocalDate fechaPublicacion;

 

    public Libro(String ISBN, LocalDate fechaPublicacion, Autor autor, int paginas, Boolean disponible1, String titulo) {
        this.ISBN = ISBN;
        this.autor = autor;
        this.disponible = disponible;
        this.fechaPublicacion = fechaPublicacion;
        this.paginas = paginas;
        this.titulo = titulo;
    }

    public Libro() {
    }

    

    /**
     * @return int return the ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * @return String return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return Autor return the autor
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * @return int return the paginas
     */
    public int getPaginas() {
        return paginas;
    }

    /**
     * @param paginas the paginas to set
     */
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    /**
     * @return boolean return the disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * @return String return the fechaPublicacion
     */
    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * @param fechaPublicacion the fechaPublicacion to set
     */
    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

       @Override
    public String toString() {
        return "Libro [ISBN=" + ISBN + ", titulo=" + titulo + ", autor=" + autor + ", paginas=" + paginas
                + ", disponible=" + disponible + ", fechaPublicacion=" + fechaPublicacion + "]";
    }

}
