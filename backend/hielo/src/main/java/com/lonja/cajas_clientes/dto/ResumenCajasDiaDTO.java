package com.lonja.cajas_clientes.dto;

public class ResumenCajasDiaDTO {
    private String nombreCliente;
    private int totalCajas;
    public ResumenCajasDiaDTO(String nombreCliente, int totalCajas) {
        this.nombreCliente = nombreCliente;
        this.totalCajas = totalCajas;
    }
    public String getNombreCliente() { return nombreCliente; }
    public int getTotalCajas() { return totalCajas; }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setTotalCajas(int totalCajas) {
        this.totalCajas = totalCajas;
    }
}
