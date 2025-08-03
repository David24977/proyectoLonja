package com.lonja.cajas_clientes.repository;

import com.lonja.cajas_clientes.model.CajaDevolucion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CajasRepository extends JpaRepository<CajaDevolucion, Long> {

    List<CajaDevolucion> findByClienteId(Long id);

    List<CajaDevolucion> findByCliente_Nombre(String nombre);

    List<CajaDevolucion> findByCliente_NombreAndFecha(String nombre, LocalDate fecha);

    // Devoluciones entre dos fechas
    List<CajaDevolucion> findByFechaBetween(LocalDate desde, LocalDate hasta);
    List<CajaDevolucion> findByFecha(LocalDate fecha);

}
