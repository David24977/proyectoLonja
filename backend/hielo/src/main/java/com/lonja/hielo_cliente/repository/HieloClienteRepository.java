package com.lonja.hielo_cliente.repository;

import com.lonja.hielo_cliente.model.HieloCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface HieloClienteRepository extends JpaRepository<HieloCliente, Long> {
    List<HieloCliente> findByClienteId(Long clienteId);
    List<HieloCliente> findByClienteIdAndFechaBetween(Long clienteId, LocalDate desde, LocalDate hasta);
    List<HieloCliente> findByClienteNombreIgnoreCaseContaining(String nombre);
    List<HieloCliente> findByClienteNombreIgnoreCaseContainingAndFechaBetween(
            String nombre, LocalDate desde, LocalDate hasta);
    List<HieloCliente> findByFechaBetween(LocalDate desde, LocalDate hasta);
    List<HieloCliente> findByFecha(LocalDate fecha);



}

