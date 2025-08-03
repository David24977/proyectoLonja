package com.lonja.cajas_clientes.dto;

import java.time.LocalDate;

public class CajaDevolucionResponseDTO {

    private Long id;
    private Integer numeroCajas;
    private LocalDate fecha;
    private String nombreCliente;

    public CajaDevolucionResponseDTO() {}

    public CajaDevolucionResponseDTO(Long id, Integer numeroCajas, LocalDate fecha, String nombreCliente) {
        this.id = id;
        this.numeroCajas = numeroCajas;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroCajas() {
        return numeroCajas;
    }

    public void setNumeroCajas(Integer numeroCajas) {
        this.numeroCajas = numeroCajas;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Override
    public String toString() {
        return "CajaDevolucionResponseDTO{" +
                "id=" + id +
                ", numeroCajas=" + numeroCajas +
                ", fecha=" + fecha +
                ", nombreCliente='" + nombreCliente + '\'' +
                '}';
    }
}
