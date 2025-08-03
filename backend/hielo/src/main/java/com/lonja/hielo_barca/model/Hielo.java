package com.lonja.hielo_barca.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "hielo")
public class Hielo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barca_id")//clave foránea
    private Barca barca;

    private Double cantidad;
    private LocalDate fecha;

    public Hielo(Long id, Barca barca, Double cantidad, LocalDate fecha) {
        this.id = id;
        this.barca = barca;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Hielo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Barca getBarca() {
        return barca;
    }

    public void setBarca(Barca barca) {
        this.barca = barca;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
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
        return "Hielo{" +
                "id=" + id +
                ", barca=" + barca.getNombre() +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}';
    }
}
