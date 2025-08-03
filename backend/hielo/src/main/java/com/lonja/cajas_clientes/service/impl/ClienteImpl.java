package com.lonja.cajas_clientes.service.impl;

import com.lonja.cajas_clientes.dto.ClienteResponseDTO;
import com.lonja.cajas_clientes.model.Cliente;
import com.lonja.cajas_clientes.repository.ClienteRepository;
import com.lonja.cajas_clientes.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.util.List;

@Service
public class ClienteImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //Para que no distinga mayúsculas de minúsculas y acentos al buscar en contenido nombre
    private String normalizar(String texto) {
        if (texto == null) return "";
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toLowerCase();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    @Override
    public List<ClienteResponseDTO> buscarPorNombre(String nombre) {
        return clienteRepository.findAll()
                .stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                .map(c -> new ClienteResponseDTO(c.getId(), c.getNombre(), c.getEmail()))
                .toList();
    }


    @Override
    public List<ClienteResponseDTO> buscarPorContenidoNombre(String nombre) {
        return clienteRepository.findAll()
                .stream()
                .filter(c -> c.getNombre() != null &&
                        normalizar(c.getNombre()).contains(normalizar(nombre))
                )
                .map(c -> new ClienteResponseDTO(c.getId(), c.getNombre(), c.getEmail()))
                .toList();
    }

    @Override
    public ClienteResponseDTO registrarCliente(Cliente cliente) {
        // Busca si existe cliente con ese id
        if (cliente.getId() != null && clienteRepository.existsById(cliente.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Modifique el id: ya existe un cliente con ese id."
            );
        }
        Cliente guardado = clienteRepository.save(cliente);
        return new ClienteResponseDTO(guardado.getId(), guardado.getNombre(), guardado.getEmail());
    }


    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepository.delete(cliente);

    }

    // Actualiza solo el nombre del cliente y el email, si solo uno de los dos, el que no se quiere modificar, se pone null
// Si se desea cambiar el ID, debe eliminarse el cliente y crearse uno nuevo. Por mantener la persistencia de datos
    @Override
    public ClienteResponseDTO actualizarCliente(Cliente cliente, Cliente nuevoCliente) {
        return clienteRepository.findById(cliente.getId())
                .map(c -> {
                    if (nuevoCliente.getNombre() != null) {
                        c.setNombre(nuevoCliente.getNombre());
                    }
                    if (nuevoCliente.getEmail() != null) {
                        c.setEmail(nuevoCliente.getEmail());
                    }
                    Cliente actualizado = clienteRepository.save(c);
                    return new ClienteResponseDTO(actualizado.getId(), actualizado.getNombre(), actualizado.getEmail());
                }).orElse(null);
    }






}
