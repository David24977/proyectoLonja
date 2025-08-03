package com.lonja.hielo_cliente.controller;

import com.lonja.cajas_clientes.model.Cliente;
import com.lonja.cajas_clientes.service.ClienteService;
import com.lonja.hielo_cliente.dto.HieloClienteRequestDTO;
import com.lonja.hielo_cliente.dto.HieloClienteResponseDTO;
import com.lonja.hielo_cliente.dto.ResumenHieloClienteDTO;
import com.lonja.hielo_cliente.dto.ResumenHieloClienteDiaDTO;
import com.lonja.hielo_cliente.model.HieloCliente;
import com.lonja.hielo_cliente.service.HieloClienteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hielo-cliente")
public class HieloClienteController {

    private final HieloClienteService hieloClienteService;
    private final ClienteService clienteService;

    public HieloClienteController(HieloClienteService hieloClienteService, ClienteService clienteService) {
        this.hieloClienteService = hieloClienteService;
        this.clienteService = clienteService;
    }

    // Insertar hielo a cliente
    @PostMapping
    public HieloClienteResponseDTO insertar(@RequestBody HieloClienteRequestDTO dto) {
        Cliente cliente = clienteService.buscarPorId(dto.getClienteId());
        HieloCliente hc = new HieloCliente(cliente, dto.getCantidad(), dto.getFecha());
        HieloCliente guardado = hieloClienteService.insertarHieloCliente(hc);
        return new HieloClienteResponseDTO(
                guardado.getId(),
                guardado.getCliente().getNombre(),
                guardado.getCantidad(),
                guardado.getFecha()
        );
    }


    // Eliminar registro
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        hieloClienteService.eliminarPorId(id);
    }

    // Listado por cliente
    @GetMapping("/cliente/{clienteId}")
    public List<HieloClienteResponseDTO> buscarPorCliente(@PathVariable Long clienteId) {
        return hieloClienteService.buscarPorCliente(clienteId);
    }

    // Listado por cliente y fechas
    @GetMapping("/cliente/{clienteId}/rango")
    public List<HieloClienteResponseDTO> buscarPorClienteYFechas(
            @PathVariable Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return hieloClienteService.buscarPorClienteYFechas(clienteId, desde, hasta);
    }

    @GetMapping("/buscarPorNombre")
    public List<HieloClienteResponseDTO> buscarPorNombreCliente(@RequestParam String nombre) {
        return hieloClienteService.buscarPorNombreCliente(nombre);
    }

    @GetMapping("/buscarPorNombreYFechas")
    public List<HieloClienteResponseDTO> buscarPorNombreYFechas(
            @RequestParam String nombre,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta
    ) {
        return hieloClienteService.buscarPorNombreYFechas(nombre, desde, hasta);
    }

    //Resumen
    @GetMapping("/resumen")
    public List<ResumenHieloClienteDTO> resumenPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta
    ) {
        return hieloClienteService.resumenPorClienteYPeriodo(desde, hasta);
    }

    @GetMapping("/resumen-dia")
    public List<ResumenHieloClienteDiaDTO> resumenPorDia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ) {
        return hieloClienteService.resumenPorDia(fecha);
    }









}

