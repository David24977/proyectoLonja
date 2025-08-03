package com.lonja.hielo_cliente.service;

import com.lonja.hielo_cliente.dto.HieloClienteResponseDTO;
import com.lonja.hielo_cliente.dto.ResumenHieloClienteDTO;
import com.lonja.hielo_cliente.dto.ResumenHieloClienteDiaDTO;
import com.lonja.hielo_cliente.model.HieloCliente;
import java.time.LocalDate;
import java.util.List;

public interface HieloClienteService {
    HieloCliente insertarHieloCliente(HieloCliente hieloCliente);
    void eliminarPorId(Long id);
    List<HieloClienteResponseDTO> buscarPorCliente(Long clienteId);
    List<HieloClienteResponseDTO> buscarPorClienteYFechas(Long clienteId, LocalDate desde, LocalDate hasta);
    List<HieloClienteResponseDTO> buscarPorNombreCliente(String nombre);
    List<HieloClienteResponseDTO> buscarPorNombreYFechas(String nombre, LocalDate desde, LocalDate hasta);
    List<ResumenHieloClienteDTO> resumenPorClienteYPeriodo(LocalDate desde, LocalDate hasta);
    List<ResumenHieloClienteDiaDTO> resumenPorDia(LocalDate fecha);


}

