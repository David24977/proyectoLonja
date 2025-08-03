package com.lonja.cajas_clientes.service.impl;

import com.lonja.cajas_clientes.dto.CajaDevolucionResponseDTO;
import com.lonja.cajas_clientes.dto.ResumenCajasDTO;
import com.lonja.cajas_clientes.model.CajaDevolucion;
import com.lonja.cajas_clientes.model.Cliente;
import com.lonja.cajas_clientes.repository.CajasRepository;
import com.lonja.cajas_clientes.repository.ClienteRepository;
import com.lonja.cajas_clientes.service.CajasService;
import com.lonja.cajas_clientes.service.EmailService;
import com.lonja.cajas_clientes.dto.ResumenCajasDiaDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
    public class CajasImpl implements CajasService {

        private final CajasRepository cajasRepository;
        private final ClienteRepository clienteRepository;
        private final EmailService emailService;

        public CajasImpl(CajasRepository cajasRepository, ClienteRepository clienteRepository, EmailService emailService) {
            this.cajasRepository = cajasRepository;
            this.clienteRepository = clienteRepository;
            this.emailService = emailService;
        }

        @Override
        public CajaDevolucionResponseDTO insertarDevolucionCajasCliente(int numeroCajas, Long clienteId, LocalDate fecha) {
            // Buscar cliente
            Cliente cliente = clienteRepository.findById(clienteId)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));

            // Crear objeto CajaDevolucion
            CajaDevolucion caja = new CajaDevolucion();
            caja.setNumeroCajas(numeroCajas);
            caja.setFecha(fecha);
            caja.setCliente(cliente);

            // Guardar en base de datos
            CajaDevolucion cajaGuardada = cajasRepository.save(caja);
            // Enviar email al cliente tras guardar la devolución
            String destinatario = cliente.getEmail();
            String asunto = "Devolución de cajas registrada";
            String cuerpo = "Hola " + cliente.getNombre() + ",\n\n"
                    + "Tu devolución de " + numeroCajas + " cajas ha sido registrada correctamente el " + fecha + ".\n\n"
                    + "Saludos,\nLonja de Cullera";
            emailService.enviarEmail(destinatario, asunto, cuerpo);


            // Convertir a DTO para evitar recursividad
            return mapToResponseDTO(cajaGuardada);
        }

        private CajaDevolucionResponseDTO mapToResponseDTO(CajaDevolucion caja) {
            return new CajaDevolucionResponseDTO(
                    caja.getId(),
                    caja.getNumeroCajas(),
                    caja.getFecha(),
                    caja.getCliente().getNombre() // solo devolvemos el nombre
            );
        }


        @Override
        public List<CajaDevolucionResponseDTO> buscarPorClienteDTO(Long clienteId) {
            return cajasRepository.findByClienteId(clienteId)
                    .stream()
                    .map(this::mapToResponseDTO) // <-- Aquí es donde hacemos el "map" de entidad a DTO
                    .toList();
        }


        @Override
        public List<CajaDevolucionResponseDTO> buscarNombreCliente(String nombre) {
            return cajasRepository.findAll()
                    .stream()
                    .filter(cd -> cd.getCliente().getNombre().equalsIgnoreCase(nombre))
                    .map(this::mapToResponseDTO)
                    .toList();
        }

        @Override
        public List<CajaDevolucionResponseDTO> buscarNombreClienteYFecha(String nombre, LocalDate fecha) {
            return cajasRepository.findAll()
                    .stream()
                    .filter(cd -> cd.getFecha().equals(fecha))
                    .filter(cd -> cd.getCliente().getNombre().equalsIgnoreCase(nombre))
                    .map(this::mapToResponseDTO)
                    .toList();
        }

        @Override
        public int buscarPorClienteYFechas(Long clienteId, LocalDate desde, LocalDate hasta) {
            return cajasRepository.findAll()
                    .stream()
                    .filter(cd -> cd.getCliente().getId().equals(clienteId))
                    .filter(cd -> !cd.getFecha().isBefore(desde) && !cd.getFecha().isAfter(hasta))
                    .mapToInt(CajaDevolucion::getNumeroCajas)
                    .sum();
        }




        @Override
        public void eliminarDevolucionesPorId(Long id) {
            cajasRepository.deleteById(id);

        }


        @Override
        public CajaDevolucionResponseDTO actualizarCajasPorId(Long id, int nuevaCantidad) {
            // Busca la devolución por su id
            return cajasRepository.findById(id)
                    .map(cd -> {
                        cd.setNumeroCajas(nuevaCantidad);           // Actualiza la cantidad
                        CajaDevolucion actualizada = cajasRepository.save(cd); // Guarda en DB
                        // Mapea a DTO antes de devolver
                        return mapToResponseDTO(actualizada);
                    })
                    .orElseThrow(() -> new RuntimeException("Devolución no encontrada con id: " + id));
        }



        @Override
        public ResumenCajasDTO obtenerResumenCajas(Long clienteId, LocalDate desde, LocalDate hasta) {
            Cliente cliente = clienteRepository.findById(clienteId)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            int total = cajasRepository.findByClienteId(clienteId).stream()
                    .filter(cd -> !cd.getFecha().isBefore(desde) && !cd.getFecha().isAfter(hasta))
                    .mapToInt(CajaDevolucion::getNumeroCajas)
                    .sum();

            return new ResumenCajasDTO(
                    cliente.getNombre(),
                    desde,
                    hasta,
                    total
            );
        }

        // Devuelve lista de devoluciones en rango
        @Override
        public List<CajaDevolucionResponseDTO> buscarListaPorClienteYFechas(Long clienteId, LocalDate desde, LocalDate hasta) {
            return cajasRepository.findAll().stream()
                    .filter(cd -> cd.getCliente().getId().equals(clienteId))
                    .filter(cd -> !cd.getFecha().isBefore(desde) && !cd.getFecha().isAfter(hasta))
                    .map(this::mapToResponseDTO)
                    .toList();
        }

        //Devuelve cajas por nombre cliente entre 2 fechas
        @Override
        public List<CajaDevolucionResponseDTO> buscarPorNombreYFechas(String nombre, LocalDate desde, LocalDate hasta) {
            return cajasRepository.findAll().stream()
                    .filter(cd -> cd.getCliente().getNombre().toLowerCase().contains(nombre.toLowerCase()))
                    .filter(cd -> !cd.getFecha().isBefore(desde) && !cd.getFecha().isAfter(hasta))
                    .map(this::mapToResponseDTO)
                    .toList();
        }

        @Override
        public List<ResumenCajasDiaDTO> resumenPorDia(LocalDate fecha) {
            List<CajaDevolucion> devoluciones = cajasRepository.findByFecha(fecha);
            return devoluciones.stream()
                    .collect(Collectors.groupingBy(
                            c -> c.getCliente().getNombre(),
                            Collectors.summingInt(CajaDevolucion::getNumeroCajas)
                    ))
                    .entrySet().stream()
                    .map(e -> new ResumenCajasDiaDTO(e.getKey(), e.getValue()))
                    .toList();
        }




    }

