
import java.io.Serializable;

public class Jugador implements Serializable {

    private String nombre;
    private int xpBase;
    private int nivel;
    private int numeroEliminados;
    private int saludRestante;
    private int numeroMuertes;
    private int tiempoEmpleado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getXpBase() {
        return xpBase;
    }

    public void setXpBase(int xpBase) {
        this.xpBase = xpBase;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNumeroEliminados() {
        return numeroEliminados;
    }

    public void setNumeroEliminados(int numeroEliminados) {
        this.numeroEliminados = numeroEliminados;
    }

    public int getSaludRestante() {
        return saludRestante;
    }

    public void setSaludRestante(int saludRestante) {
        this.saludRestante = saludRestante;
    }

    public int getNumeroMuertes() {
        return numeroMuertes;
    }

    public void setNumeroMuertes(int numeroMuertes) {
        this.numeroMuertes = numeroMuertes;
    }

    public int getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(int tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public Jugador(String nombre, int xpBase, int nivel, int numeroEliminados, int saludRestante, int numeroMuertes,
            int tiempoEmpleado) {
        this.nombre = nombre;
        this.xpBase = xpBase;
        this.nivel = nivel;
        this.numeroEliminados = numeroEliminados;
        this.saludRestante = saludRestante;
        this.numeroMuertes = numeroMuertes;
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public Jugador() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador{");
        sb.append("nombre=").append(nombre);
        sb.append(", xpBase=").append(xpBase);
        sb.append(", nivel=").append(nivel);
        sb.append(", numeroEliminados=").append(numeroEliminados);
        sb.append(", saludRestante=").append(saludRestante);
        sb.append(", numeroMuertes=").append(numeroMuertes);
        sb.append(", tiempoEmpleado=").append(tiempoEmpleado);
        sb.append('}');
        return sb.toString();
    }

}
