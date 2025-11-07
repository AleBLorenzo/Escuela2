public class Usuario {

    private String Nombre;
    private int Edad;
    private boolean premium;

    public Usuario() {
    }

    public Usuario(int Edad, String Nombre, boolean premium) {
        this.Edad = Edad;
        this.Nombre = Nombre;
        this.premium = premium;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("Nombre=").append(Nombre);
        sb.append(", Edad=").append(Edad);
        sb.append(", premium=").append(premium);
        sb.append('}');
        return sb.toString();
    }
}
