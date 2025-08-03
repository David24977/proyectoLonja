package com.lonja.hielo_cliente.model;

import com.lonja.cajas_clientes.model.Cliente;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HieloCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private double cantidad;

    private LocalDate fecha;

    // Constructores
    public HieloCliente() {}

    public HieloCliente(Cliente cliente, double cantidad, LocalDate fecha) {
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }


    @Override
    public String toString() {
        return "HieloCliente{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}';
    }
}

