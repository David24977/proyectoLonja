package com.lonja.hielo_cliente.dto;

import java.time.LocalDate;

public class HieloClienteRequestDTO {
    private Long clienteId;
    private double cantidad;
    private LocalDate fecha;

    public HieloClienteRequestDTO(Long clienteId, double cantidad, LocalDate fecha) {
        this.clienteId = clienteId;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public HieloClienteRequestDTO(){}

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    @Override
    public String toString() {
        return "HieloClienteRequestDTO{" +
                "clienteId=" + clienteId +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}';
    }
}
