package com.lonja.cajas_clientes.service;

import com.lonja.cajas_clientes.dto.CajaDevolucionResponseDTO;
import com.lonja.cajas_clientes.dto.ResumenCajasDTO;
import com.lonja.cajas_clientes.dto.ResumenCajasDiaDTO;
import com.lonja.cajas_clientes.model.CajaDevolucion;

import java.time.LocalDate;
import java.util.List;

public interface CajasService {
    List<CajaDevolucionResponseDTO> buscarPorClienteDTO(Long clienteId);


    List<CajaDevolucionResponseDTO> buscarNombreCliente(String nombre);

    List<CajaDevolucionResponseDTO> buscarNombreClienteYFecha(String nombre, LocalDate fecha);

    // Devoluciones entre dos fechas
    int buscarPorClienteYFechas(Long clienteId, LocalDate desde, LocalDate hasta);

    CajaDevolucionResponseDTO insertarDevolucionCajasCliente(int numeroCajas, Long clienteId, LocalDate fecha);

    void eliminarDevolucionesPorId(Long id);

    CajaDevolucionResponseDTO actualizarCajasPorId(Long id, int nuevaCantidad);

    ResumenCajasDTO obtenerResumenCajas(Long clienteId, LocalDate desde, LocalDate hasta);
    List<CajaDevolucionResponseDTO> buscarListaPorClienteYFechas(Long clienteId, LocalDate desde, LocalDate hasta);
    List<CajaDevolucionResponseDTO> buscarPorNombreYFechas(String nombre, LocalDate desde, LocalDate hasta);
    List<ResumenCajasDiaDTO> resumenPorDia(LocalDate fecha);
}
