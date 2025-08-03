package com.lonja.cajas_clientes.controller;

import com.lonja.cajas_clientes.dto.CajaDevolucionRequestDTO;
import com.lonja.cajas_clientes.dto.CajaDevolucionResponseDTO;
import com.lonja.cajas_clientes.dto.ResumenCajasDTO;
import com.lonja.cajas_clientes.dto.ResumenCajasDiaDTO;
import com.lonja.cajas_clientes.service.CajasService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cajas")
public class CajasController {

    private final CajasService cajasService;

    public CajasController(CajasService cajasService) {
        this.cajasService = cajasService;
    }

    // Insertar una devolución
    @PostMapping("/insertar")
    public CajaDevolucionResponseDTO insertar(@RequestBody @Valid CajaDevolucionRequestDTO dto) {
        return cajasService.insertarDevolucionCajasCliente(
                dto.getNumeroCajas(),
                dto.getClienteId(),
                dto.getFecha()
        );
    }

    // Eliminar una devolución por ID
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        cajasService.eliminarDevolucionesPorId(id);
    }

    // Actualizar devolución (cantidad)
    @PutMapping("/actualizar/{id}")
    public CajaDevolucionResponseDTO actualizar(
            @PathVariable Long id,
            @RequestParam int nuevaCantidad) {
        return cajasService.actualizarCajasPorId(id, nuevaCantidad);
    }

    // Obtener devoluciones por cliente
    @GetMapping("/cliente/{clienteId}")
    public List<CajaDevolucionResponseDTO> devolucionesPorCliente(@PathVariable Long clienteId) {
        return cajasService.buscarPorClienteDTO(clienteId);
    }

    // Obtener resumen por cliente entre fechas
    @GetMapping("/resumen")
    public ResumenCajasDTO resumen(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return cajasService.obtenerResumenCajas(clienteId, desde, hasta);
    }

    @GetMapping("/buscarPorNombre")
    public List<CajaDevolucionResponseDTO> buscarPorNombre(@RequestParam String nombre) {
        return cajasService.buscarNombreCliente(nombre);
    }

    @GetMapping("/buscarPorNombreYFecha")
    public List<CajaDevolucionResponseDTO> buscarPorNombreYFecha(@RequestParam String nombre, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return cajasService.buscarNombreClienteYFecha(nombre, fecha);
    }

    @GetMapping("/totalPorClienteYFechas")
    public int totalPorClienteYFechas(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return cajasService.buscarPorClienteYFechas(clienteId, desde, hasta);
    }

    @GetMapping("/buscarPorClienteYFechas")
    public List<CajaDevolucionResponseDTO> buscarPorClienteYFechas(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return cajasService.buscarListaPorClienteYFechas(clienteId, desde, hasta);
    }

    @GetMapping("/buscarPorNombreYFechas")
    public List<CajaDevolucionResponseDTO> buscarPorNombreYFechas(
            @RequestParam String nombre,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return cajasService.buscarPorNombreYFechas(nombre, desde, hasta);
    }

    @GetMapping("/resumen-dia")
    public List<ResumenCajasDiaDTO> resumenPorDia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ) {
        return cajasService.resumenPorDia(fecha);
    }






}
