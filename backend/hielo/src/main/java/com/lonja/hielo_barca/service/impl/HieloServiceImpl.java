package com.lonja.hielo_barca.service.impl;

import com.lonja.hielo_barca.dto.ResumenHieloBarcaDiaDTO;
import com.lonja.hielo_barca.dto.ResumenHieloDTO;
import com.lonja.hielo_barca.model.Hielo;
import com.lonja.hielo_barca.repository.HieloRepository;
import com.lonja.hielo_barca.service.HieloService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HieloServiceImpl implements HieloService {
    private final HieloRepository hieloRepository;

    public HieloServiceImpl(HieloRepository hieloRepository) {
        this.hieloRepository = hieloRepository;
    }

    @Override
    public List<Hielo> buscarPorBarcaId(Long barcaId) {
        return hieloRepository.findAll()
                .stream()
                .filter(h -> h.getBarca().getId().equals(barcaId))
                .toList();
    }

    @Override
    public List<Hielo> buscarPorFecha(LocalDate fecha) {
        return hieloRepository.findAll()
                .stream()
                .filter(h -> h.getFecha().equals(fecha))
                .toList();
    }

    @Override
    public List<Hielo> buscarPorBarcaIdYFecha(Long barcaId, LocalDate fecha) {
        return hieloRepository.findAll()
                .stream()
                .filter(h -> h.getFecha().equals(fecha))
                .filter(h -> h.getBarca().getId()
                        .equals(barcaId))
                .toList();
    }

    @Override
    public Hielo guardarHielo(Hielo hielo) {
        return hieloRepository.save(hielo);
    }

    @Override
    public void eliminarHielo(Long id) {
        hieloRepository.deleteById(id);

    }

    @Override
    public Hielo actualizarHielo(Long id, Hielo nuevoHielo) {
        return hieloRepository.findById(id)
                .map(h -> {
                    h.setCantidad(nuevoHielo.getCantidad());
                    h.setFecha(nuevoHielo.getFecha());
                    h.setBarca(nuevoHielo.getBarca());
                    return hieloRepository.save(h);
                }).orElse(null);
    }

    @Override
    public Hielo buscarPorId(Long id) {
        return hieloRepository.findById(id).orElse(null);
    }

    @Override
    public List<ResumenHieloDTO> obtenerResumenEntreFechas(LocalDate inicio, LocalDate fin, double precioUnitario) {
        return hieloRepository.findAll().stream()
                .filter(h -> !h.getFecha().isBefore(inicio) && !h.getFecha().isAfter(fin))
                .collect(Collectors.groupingBy(h -> h.getBarca().getNombre()))
                .entrySet().stream()
                .map(entry -> {
                    String nombreBarca = entry.getKey();
                    double totalKilos = entry.getValue().stream()
                            .mapToDouble(Hielo::getCantidad)
                            .sum();
                    double totalEuros = totalKilos * precioUnitario;
                    return new ResumenHieloDTO(
                            nombreBarca,
                            String.format("Del %s al %s", inicio, fin),
                            totalKilos,
                            precioUnitario);
                })
                .toList();
    }

    public List<Hielo> buscarPorBarcaEntreFechas(Long barcaId, LocalDate desde, LocalDate hasta) {
        return hieloRepository.findAll()
                .stream()
                .filter(h -> h.getBarca().getId().equals(barcaId))
                .filter(h -> !h.getFecha().isBefore(desde) && !h.getFecha().isAfter(hasta))
                .toList();
    }

    @Override
    public List<ResumenHieloBarcaDiaDTO> resumenPorDia(LocalDate fecha) {
        List<Hielo> registros = hieloRepository.findByFecha(fecha);

        return registros.stream()
                .collect(Collectors.groupingBy(
                        h -> h.getBarca().getNombre(),
                        Collectors.summingDouble(Hielo::getCantidad)
                ))
                .entrySet().stream()
                .map(e -> new ResumenHieloBarcaDiaDTO(e.getKey(), e.getValue()))
                .toList();
    }




}

