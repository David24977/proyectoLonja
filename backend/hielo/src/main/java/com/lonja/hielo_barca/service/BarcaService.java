package com.lonja.hielo_barca.service;

import com.lonja.hielo_barca.model.Barca;

import java.util.List;

public interface BarcaService {
    //Creamos las interfaces a manejar
    List<Barca> encontrarPorNombre(String nombre);
    Barca encontrarPorId(Long id);
    List<Barca> obtenerTodasBarcas();
    Barca guardarBarca(Barca barca);
    void eliminarBarca(Long id);
    List<Barca> buscarPorContenidoNombre(String nombre);

}

