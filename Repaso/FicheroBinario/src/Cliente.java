
import java.io.Serializable;

public class Cliente implements Serializable{


     private int ID;
     private String Nombre;
     private double Saldo;
     private boolean ClienteVIP;

    public Cliente() {
    }

    public Cliente(boolean ClienteVIP, int ID, String Nombre, double Saldo) {
        this.ClienteVIP = ClienteVIP;
        this.ID = ID;
        this.Nombre = Nombre;
        this.Saldo = Saldo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    public boolean isClienteVIP() {
        return ClienteVIP;
    }

    public void setClienteVIP(boolean ClienteVIP) {
        this.ClienteVIP = ClienteVIP;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("ID=").append(ID);
        sb.append(", Nombre=").append(Nombre);
        sb.append(", Saldo=").append(Saldo);
        sb.append(", ClienteVIP=").append(ClienteVIP);
        sb.append('}');
        return sb.toString();
    }
}
