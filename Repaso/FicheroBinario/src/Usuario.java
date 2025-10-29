
import java.io.Serializable;

public class Usuario implements Serializable{

    private String username;
    private Rol rol;
    private boolean activo;

    public Usuario(boolean activo, Rol rol, String username) {
        this.activo = activo;
        this.rol = rol;
        this.username = username;
    }

    public Usuario() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuario [username=" + username + ", rol=" + rol + ", activo=" + activo + "]";
    }


}
