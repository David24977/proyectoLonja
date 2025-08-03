package com.lonja.cajas_clientes.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cajasdev")
public class CajaDevolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numeroCajas;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public CajaDevolucion(Long id, int numeroCajas, LocalDate fecha, Cliente cliente) {
        this.id = id;
        this.numeroCajas = numeroCajas;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public CajaDevolucion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroCajas() {
        return numeroCajas;
    }

    public void setNumeroCajas(int numeroCajas) {
        this.numeroCajas = numeroCajas;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public String toString() {
        return "CajaDevolucion{" +
                "id=" + id +
                ", numeroCajas=" + numeroCajas +
                ", fecha=" + fecha +
                ", cliente=" + cliente +
                '}';
    }
}
