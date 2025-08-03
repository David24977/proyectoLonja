package com.lonja.cajas_clientes.dto;

import java.time.LocalDate;

public class ResumenCajasDTO {
    private String nombreCliente;
    private LocalDate desde;
    private LocalDate hasta;
    private int totalCajas;

    public ResumenCajasDTO() {}

    public ResumenCajasDTO(String nombreCliente, LocalDate desde, LocalDate hasta, int totalCajas) {
        this.nombreCliente = nombreCliente;
        this.desde = desde;
        this.hasta = hasta;
        this.totalCajas = totalCajas;
    }

    // Getters y setters
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public LocalDate getDesde() {
        return desde;
    }

    public void setDesde(LocalDate desde) {
        this.desde = desde;
    }

    public LocalDate getHasta() {
        return hasta;
    }

    public void setHasta(LocalDate hasta) {
        this.hasta = hasta;
    }

    public int getTotalCajas() {
        return totalCajas;
    }

    public void setTotalCajas(int totalCajas) {
        this.totalCajas = totalCajas;
    }
}
