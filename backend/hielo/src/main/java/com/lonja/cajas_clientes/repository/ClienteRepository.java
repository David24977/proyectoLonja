package com.lonja.cajas_clientes.repository;

import com.lonja.cajas_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    List<Cliente> findByNombre(String nombre);
    // Buscar clientes cuyo nombre contenga una parte del texto
    List<Cliente> findByNombreContaining(String nombre);

}
