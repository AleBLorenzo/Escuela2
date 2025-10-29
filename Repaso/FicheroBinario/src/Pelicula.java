
import java.io.Serializable;

public class Pelicula implements Serializable {


    private String titulo;
    private String director;
    private String año;
    private boolean disponible;

    @Override
    public String toString() {
        return "Pelicula [titulo=" + titulo + ", director=" + director + ", año=" + año + ", disponible=" + disponible
                + "]";
    }

    public Pelicula(String año, String director, boolean disponible, String titulo) {
        this.año = año;
        this.director = director;
        this.disponible = disponible;
        this.titulo = titulo;
    }

    public Pelicula() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

  

}
