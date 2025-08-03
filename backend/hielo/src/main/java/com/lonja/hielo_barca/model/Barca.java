package com.lonja.hielo_barca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//Model creamos la clase que queremos manejar la entidad principal, Entity
@Entity
@Table(name = "barca")
public class Barca {
    @Id
    private Long id;
    private String nombre;

    public Barca(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Barca() {
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


    @Override
    public String toString() {
        return "Barca: " +
                "id: " + id +
                ", nombre= " + nombre;
    }
}
