package Fichero3.src;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Usuario  implements Serializable {

    private String username;
    private String passwordHash;
    private String email;
    private LocalDate fechaRegistro;
    private boolean activo;
    private Rol rol;
  

   
    public Usuario(boolean activo, String email, LocalDate fechaRegistro, String password, Rol rol, String username) {
        this.activo = activo;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
         this.passwordHash = String.valueOf(password.hashCode());
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }


 @Override
    public String toString() {
        return "Usuario [username=" + username + ", email=" + email + ", fechaRegistro=" + fechaRegistro + ", activo="
                + activo + ", rol=" + rol + "]";
    }



}
