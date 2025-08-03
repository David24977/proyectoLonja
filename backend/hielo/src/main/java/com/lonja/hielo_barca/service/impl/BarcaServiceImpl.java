package com.lonja.hielo_barca.service.impl;

import com.lonja.hielo_barca.model.Barca;
import com.lonja.hielo_barca.repository.BarcaRepository;
import com.lonja.hielo_barca.service.BarcaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//Procesa los datos definidos en las interfaces Service, aplica reglas antes de guardar o devolver
@Service
public class BarcaServiceImpl implements BarcaService {
    private final BarcaRepository barcaRepository;


    public BarcaServiceImpl(BarcaRepository barcaRepository) {
        this.barcaRepository = barcaRepository;
    }

    @Override
    public List<Barca> encontrarPorNombre(String nombre){
        return barcaRepository.findByNombre(nombre);
    }
    @Override
    public Barca encontrarPorId(Long id){
        return barcaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Barca> obtenerTodasBarcas(){
        return barcaRepository.findAll();
    }

    @Override
    public Barca guardarBarca(Barca barca) {
        if (barca.getId() != null && barcaRepository.existsById(barca.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Modifique el id: ya existe una barca con ese id."
            );
        }

        return barcaRepository.save(barca);
    }

    @Override
    public void eliminarBarca(Long id) {
        barcaRepository.deleteById(id);
    }

    // En BarcaService.java
    public List<Barca> buscarPorContenidoNombre(String nombre) {
        return barcaRepository.findAll()
                .stream()
                .filter(b -> b.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .toList();
    }




}


