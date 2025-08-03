package com.lonja.hielo_barca.dto;
public class ResumenHieloBarcaDiaDTO {
    private String nombreBarca;
    private double cantidadTotal;

    public ResumenHieloBarcaDiaDTO(String nombreBarca, double cantidadTotal) {
        this.nombreBarca = nombreBarca;
        this.cantidadTotal = cantidadTotal;
    }
    public String getNombreBarca() { return nombreBarca; }
    public void setNombreBarca(String nombreBarca) { this.nombreBarca = nombreBarca; }
    public double getCantidadTotal() { return cantidadTotal; }
    public void setCantidadTotal(double cantidadTotal) { this.cantidadTotal = cantidadTotal; }
}

