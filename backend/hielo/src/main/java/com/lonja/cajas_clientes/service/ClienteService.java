package com.lonja.cajas_clientes.service;

import com.lonja.cajas_clientes.dto.ClienteResponseDTO;
import com.lonja.cajas_clientes.model.Cliente;

import java.util.List;

public interface ClienteService {
    // ClienteService.java
    Cliente buscarPorId(Long id);


    List<ClienteResponseDTO> buscarPorNombre(String nombre);

    List<ClienteResponseDTO> buscarPorContenidoNombre(String nombre);

    ClienteResponseDTO registrarCliente(Cliente cliente);
    void eliminarCliente(Cliente cliente);
    ClienteResponseDTO actualizarCliente(Cliente cliente, Cliente nuevoCliente);

}
