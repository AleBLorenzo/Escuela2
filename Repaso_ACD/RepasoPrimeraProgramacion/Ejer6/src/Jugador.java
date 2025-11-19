
import java.io.Serializable;

public class Jugador implements  Serializable{

        private  String nombre ;
        private int racha ;
        private int racha_Maxima ;
        private int victorias ;
        private int derrotas ;
        private int empates ;

    public Jugador() {
    }

    public Jugador(int derrotas, int empates, String nombre, int racha, int racha_Maxima, int victorias) {
        this.derrotas = derrotas;
        this.empates = empates;
        this.nombre = nombre;
        this.racha = racha;
        this.racha_Maxima = racha_Maxima;
        this.victorias = victorias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRacha() {
        return racha;
    }

    public void setRacha(int racha) {
        this.racha = racha;
    }


    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador{");
        sb.append("nombre=").append(nombre);
        sb.append(", racha=").append(racha);
        sb.append(", racha_Maxima=").append(racha_Maxima);
        sb.append(", victorias=").append(victorias);
        sb.append(", derrotas=").append(derrotas);
        sb.append(", empates=").append(empates);
        sb.append('}');
        return sb.toString();
    }

    public int getRacha_Maxima() {
        return racha_Maxima;
    }

    public void setRacha_Maxima(int racha_Maxima) {
        this.racha_Maxima = racha_Maxima;
    }
}
