package com.lonja.hielo_cliente.service.impl;

import com.lonja.hielo_cliente.dto.HieloClienteResponseDTO;
import com.lonja.hielo_cliente.dto.ResumenHieloClienteDiaDTO;
import com.lonja.hielo_cliente.model.HieloCliente;
import com.lonja.hielo_cliente.repository.HieloClienteRepository;
import com.lonja.hielo_cliente.service.HieloClienteService;
import org.springframework.stereotype.Service;
import com.lonja.hielo_cliente.dto.ResumenHieloClienteDTO;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HieloClienteServiceImpl implements HieloClienteService {

    private final HieloClienteRepository repository;

    public HieloClienteServiceImpl(HieloClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public HieloCliente insertarHieloCliente(HieloCliente hieloCliente) {
        return repository.save(hieloCliente);
    }

    @Override
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<HieloClienteResponseDTO> buscarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId)
                .stream()
                .map(hc -> new HieloClienteResponseDTO(
                        hc.getId(),
                        hc.getCliente().getNombre(),
                        hc.getCantidad(),
                        hc.getFecha()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<HieloClienteResponseDTO> buscarPorClienteYFechas(Long clienteId, LocalDate desde, LocalDate hasta) {
        return repository.findByClienteIdAndFechaBetween(clienteId, desde, hasta)
                .stream()
                .map(hc -> new HieloClienteResponseDTO(
                        hc.getId(),
                        hc.getCliente().getNombre(),
                        hc.getCantidad(),
                        hc.getFecha()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<HieloClienteResponseDTO> buscarPorNombreCliente(String nombre) {
        return repository.findByClienteNombreIgnoreCaseContaining(nombre)
                .stream()
                .map(hc -> new HieloClienteResponseDTO(
                        hc.getId(),
                        hc.getCliente().getNombre(),
                        hc.getCantidad(),
                        hc.getFecha()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<HieloClienteResponseDTO> buscarPorNombreYFechas(String nombre, LocalDate desde, LocalDate hasta) {
        return repository.findByClienteNombreIgnoreCaseContainingAndFechaBetween(nombre, desde, hasta)
                .stream()
                .map(hc -> new HieloClienteResponseDTO(
                        hc.getId(),
                        hc.getCliente().getNombre(),
                        hc.getCantidad(),
                        hc.getFecha()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResumenHieloClienteDTO> resumenPorClienteYPeriodo(LocalDate desde, LocalDate hasta) {
        List<HieloCliente> lista = repository.findByFechaBetween(desde, hasta);
        return lista.stream()
                .collect(Collectors.groupingBy(hc -> hc.getCliente().getNombre(), Collectors.summingDouble(HieloCliente::getCantidad)))
                .entrySet().stream()
                .map(e -> new ResumenHieloClienteDTO(
                        e.getKey(), // nombreCliente
                        e.getValue(), // suma total
                        desde,
                        hasta
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResumenHieloClienteDiaDTO> resumenPorDia(LocalDate fecha) {
        List<HieloCliente> registros = repository.findByFecha(fecha);

        // Agrupa por cliente y suma la cantidad
        return registros.stream()
                .collect(Collectors.groupingBy(
                        hc -> hc.getCliente().getNombre(),
                        Collectors.summingDouble(HieloCliente::getCantidad)
                ))
                .entrySet().stream()
                .map(e -> new ResumenHieloClienteDiaDTO(e.getKey(), e.getValue()))
                .toList();
    }








}


