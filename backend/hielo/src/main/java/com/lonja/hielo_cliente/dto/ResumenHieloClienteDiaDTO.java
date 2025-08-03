package com.lonja.hielo_cliente.dto;
public class ResumenHieloClienteDiaDTO {
    private String nombreCliente;
    private double cantidadTotal;

    public ResumenHieloClienteDiaDTO(String nombreCliente, double cantidadTotal) {
        this.nombreCliente = nombreCliente;
        this.cantidadTotal = cantidadTotal;
    }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public double getCantidadTotal() { return cantidadTotal; }
    public void setCantidadTotal(double cantidadTotal) { this.cantidadTotal = cantidadTotal; }
}
