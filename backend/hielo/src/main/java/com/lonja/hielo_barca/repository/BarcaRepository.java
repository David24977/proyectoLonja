package com.lonja.hielo_barca.repository;

import com.lonja.hielo_barca.model.Barca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//Es como el DAO donde definimos las consultas SQL, acceso directo a la base de datos(CRUD)
public interface BarcaRepository extends JpaRepository<Barca, Long> {
    List<Barca> findByNombre(String nombre);
    Optional<Barca> findById(Long id);

}
