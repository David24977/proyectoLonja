package com.lonja.hielo_barca.dto;

public class ResumenHieloDTO {
    private String nombreBarca;
    private String periodo; // Ej: De tal día a tal día
    private double totalKilos;
    private double precioUnitario;
    private double totalEuros;

    public ResumenHieloDTO() {}

    public ResumenHieloDTO(String nombreBarca, String periodo, double totalKilos, double precioUnitario) {
        this.nombreBarca = nombreBarca;
        this.periodo = periodo;
        this.totalKilos = totalKilos;
        this.precioUnitario = precioUnitario;//Editable
        this.totalEuros = totalKilos * precioUnitario;
    }

    public String getNombreBarca() {
        return nombreBarca;
    }

    public void setNombreBarca(String nombreBarca) {
        this.nombreBarca = nombreBarca;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public double getTotalKilos() {
        return totalKilos;
    }

    public void setTotalKilos(double totalKilos) {
        this.totalKilos = totalKilos;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.totalEuros = this.totalKilos * precioUnitario;
    }

    public double getTotalEuros() {
        return totalEuros;
    }
}
