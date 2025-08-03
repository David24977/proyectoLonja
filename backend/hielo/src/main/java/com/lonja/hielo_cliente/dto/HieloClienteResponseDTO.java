package com.lonja.hielo_cliente.dto;

import com.lonja.hielo_barca.model.Barca;

import java.time.LocalDate;

public class HieloClienteResponseDTO {
    private Long id;
    private String nombreCliente;
    private double cantidad;
    private LocalDate fecha;

    public HieloClienteResponseDTO(Long id, String nombreCliente, double cantidad, LocalDate fecha) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }


}

