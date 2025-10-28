
import java.io.Serializable;

public class Cuenta implements  Serializable{

    private long NumeroCuenta;
    private String NombreTitular;
    private double SaldoActual;
    private boolean Activo;

    public Cuenta()  {
    }

    public Cuenta(boolean Activo, String NombreTitular, long NumeroCuenta, double SaldoActual) {
        this.Activo = Activo;
        this.NombreTitular = NombreTitular;
        this.NumeroCuenta = NumeroCuenta;
        this.SaldoActual = SaldoActual;
    }

    public long getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(long NumeroCuenta) {
        this.NumeroCuenta = NumeroCuenta;
    }

    public String getNombreTitular() {
        return NombreTitular;
    }

    public void setNombreTitular(String NombreTitular) {
        this.NombreTitular = NombreTitular;
    }

    public double getSaldoActual() {
        return SaldoActual;
    }

    public void setSaldoActual(double SaldoActual) {
        this.SaldoActual = SaldoActual;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean Activo) {
        this.Activo = Activo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cuenta{");
        sb.append("NumeroCuenta=").append(NumeroCuenta);
        sb.append(", NombreTitular=").append(NombreTitular);
        sb.append(", SaldoActual=").append(SaldoActual);
        sb.append(", Activo=").append(Activo);
        sb.append('}');
        return sb.toString();
    }

}
