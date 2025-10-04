package Fichero3.src;

import java.io.Serializable;

public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Marca;
    private String Modelo;
    private int Ano;
    private Double Precio;
    private Combustible Combustible;

    public Vehiculo(int Ano, Combustible Combustible, String Marca, String Modelo, Double Precio) {
        this.Ano = Ano;
        this.Combustible = Combustible;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.Precio = Precio;
    }

     public Vehiculo() {
       
    }
    
    


    /**
     * @return String return the Marca
     */
    public String getMarca() {
        return Marca;
    }

    /**
     * @param Marca the Marca to set
     */
    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    /**
     * @return String return the Modelo
     */
    public String getModelo() {
        return Modelo;
    }

    /**
     * @param Modelo the Modelo to set
     */
    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    /**
     * @return int return the Ano
     */
    public int getAno() {
        return Ano;
    }

    /**
     * @param Ano the Ano to set
     */
    public void setAno(int Ano) {
        this.Ano = Ano;
    }

    /**
     * @return Double return the Precio
     */
    public Double getPrecio() {
        return Precio;
    }

    /**
     * @param Precio the Precio to set
     */
    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }

    /**
     * @return Combustible return the Combustible
     */
    public Combustible getCombustible() {
        return Combustible;
    }

    /**
     * @param Combustible the Combustible to set
     */
    public void setCombustible(Combustible Combustible) {
        this.Combustible = Combustible;
    }

    @Override
    public String toString() {
        return "Marca: " + Marca + "  Modelo: " + Modelo +"  Año: " + Ano +"  Precio: " + Precio + " euros" + "  Combustible: " + Combustible;
    }

    

}
