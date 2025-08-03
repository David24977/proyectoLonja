package com.lonja.hielo_barca.service;

import com.lonja.hielo_barca.dto.ResumenHieloDTO;
import com.lonja.hielo_barca.model.Hielo;
import com.lonja.hielo_barca.dto.ResumenHieloBarcaDiaDTO;
import java.time.LocalDate;
import java.util.List;

public interface HieloService {
    List<Hielo> buscarPorBarcaId(Long barcaId);

    List<Hielo> buscarPorFecha(LocalDate fecha);

    List<Hielo> buscarPorBarcaIdYFecha(Long barcaId, LocalDate fecha);

    Hielo guardarHielo(Hielo hielo);
    void eliminarHielo(Long id);
    Hielo actualizarHielo(Long id, Hielo nuevoHielo);
    Hielo buscarPorId(Long id);
    List<ResumenHieloDTO> obtenerResumenEntreFechas(LocalDate inicio, LocalDate fin, double precioUnitario);
    List<Hielo> buscarPorBarcaEntreFechas(Long barcaId, LocalDate desde, LocalDate hasta);
    List<ResumenHieloBarcaDiaDTO> resumenPorDia(LocalDate fecha);

}
