package com.lonja.cajas_clientes.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "cliente")
public class Cliente {
    @Id
    private Long id;
    private String nombre;
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<CajaDevolucion> devoluciones = new ArrayList<>();




    public Cliente(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email; }

    public List<CajaDevolucion> getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(List<CajaDevolucion> devoluciones) {
        this.devoluciones = devoluciones;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", devoluciones=" + devoluciones +
                '}';
    }
}

