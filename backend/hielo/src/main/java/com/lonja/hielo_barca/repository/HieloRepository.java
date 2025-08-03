package com.lonja.hielo_barca.repository;

import com.lonja.hielo_barca.model.Hielo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HieloRepository extends JpaRepository<Hielo, Long> {
    List<Hielo> findByBarcaId(Long barcaId);

    List<Hielo> findByFecha(LocalDate fecha);

    List<Hielo> findByBarcaIdAndFecha(Long barcaId, LocalDate fecha);



}
