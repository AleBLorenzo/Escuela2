package com.example;

import java.io.Serializable;

public class ConfiguracionServidor  implements  Serializable{

    public String Servidor;
    public String IP;
    public int Puerto;
    public String Inicio;

    public ConfiguracionServidor() {
    }

    public ConfiguracionServidor(String IP, String Inicio, int Puerto, String Servidor) {
        this.IP = IP;
        this.Inicio = Inicio;
        this.Puerto = Puerto;
        this.Servidor = Servidor;
    }

    public String getServidor() {
        return Servidor;
    }

    public void setServidor(String Servidor) {
        this.Servidor = Servidor;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPuerto() {
        return Puerto;
    }

    public void setPuerto(int Puerto) {
        this.Puerto = Puerto;
    }

    public String getInicio() {
        return Inicio;
    }

    public void setInicio(String Inicio) {
        this.Inicio = Inicio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConfiguracionServidor{");
        sb.append("Servidor=").append(Servidor);
        sb.append(", IP=").append(IP);
        sb.append(", Puerto=").append(Puerto);
        sb.append(", Inicio=").append(Inicio);
        sb.append('}');
        return sb.toString();
    }

}
