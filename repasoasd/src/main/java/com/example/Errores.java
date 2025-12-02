package com.example;

public class Errores {

   private String timestamp;
    private String tipo;
     private String mensaje;
      private String gravedad;

    public Errores(String gravedad, String mensaje, String timestamp, String tipo) {
        this.gravedad = gravedad;
        this.mensaje = mensaje;
        this.timestamp = timestamp;
        this.tipo = tipo;
    }

    public Errores() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Errores{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", tipo=").append(tipo);
        sb.append(", mensaje=").append(mensaje);
        sb.append(", gravedad=").append(gravedad);
        sb.append('}');
        return sb.toString();
    }


}
