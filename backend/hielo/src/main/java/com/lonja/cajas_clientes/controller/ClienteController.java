package com.lonja.cajas_clientes.controller;

import com.lonja.cajas_clientes.dto.ClienteResponseDTO;
import com.lonja.cajas_clientes.model.Cliente;
import com.lonja.cajas_clientes.service.ClienteService;
import com.lonja.cajas_clientes.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //Registrar nuevo cliente
    @PostMapping("/registrar")
    public ClienteResponseDTO registrarCliente(@RequestBody Cliente cliente) {
        return clienteService.registrarCliente(cliente);
    }

    //Actualizar cliente (solo nombre y email)
    @PutMapping("/actualizar")
    public ClienteResponseDTO actualizarCliente(@RequestParam Long id, @RequestParam String nuevoNombre, @RequestParam String nuevoEmail) {
        Cliente actual = new Cliente();
        actual.setId(id);

        Cliente nuevo = new Cliente();
        nuevo.setNombre(nuevoNombre);
        nuevo.setEmail(nuevoEmail);

        return clienteService.actualizarCliente(actual, nuevo);
    }

    // 🗑 Eliminar cliente
    @DeleteMapping("/eliminar/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        clienteService.eliminarCliente(cliente);
    }

    // Buscar por nombre exacto
    @GetMapping("/buscar")
    public List<ClienteResponseDTO> buscarPorNombre(@RequestParam String nombre) {
        return clienteService.buscarPorNombre(nombre);
    }

    // Buscar por contenido de nombre
    @GetMapping("/buscar/parcial")
    public List<ClienteResponseDTO> buscarPorContenidoNombre(@RequestParam String nombre) {
        return clienteService.buscarPorContenidoNombre(nombre);
    }

    // Obtener todos (opcional)
    @GetMapping("/todos")
    public List<ClienteResponseDTO> listarTodos() {
        return clienteService.buscarPorContenidoNombre(""); // devuelve todos si no filtras
    }



}

