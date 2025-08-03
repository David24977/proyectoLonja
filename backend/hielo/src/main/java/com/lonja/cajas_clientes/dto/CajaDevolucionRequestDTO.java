package com.lonja.cajas_clientes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CajaDevolucionRequestDTO {
    @NotNull(message = "El número de cajas es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 caja devuelta")
    private Integer numeroCajas;
    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    public Integer getNumeroCajas() {
        return numeroCajas;
    }

    public void setNumeroCajas(Integer numeroCajas) {
        this.numeroCajas = numeroCajas;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

