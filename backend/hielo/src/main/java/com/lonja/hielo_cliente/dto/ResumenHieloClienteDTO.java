package com.lonja.hielo_cliente.dto;

import java.time.LocalDate;

public class ResumenHieloClienteDTO {
    private String nombreCliente;
    private double cantidadTotal;
    private LocalDate desde;
    private LocalDate hasta;


    public ResumenHieloClienteDTO(String nombreCliente, double cantidadTotal, LocalDate desde, LocalDate hasta) {
        this.nombreCliente = nombreCliente;
        this.cantidadTotal = cantidadTotal;
        this.desde = desde;
        this.hasta = hasta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(double cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
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


    @Override
    public String toString() {
        return "ResumenHieloClienteDTO{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", cantidadTotal=" + cantidadTotal +
                ", desde=" + desde +
                ", hasta=" + hasta +
                '}';
    }
}

