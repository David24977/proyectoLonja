package com.lonja.hielo_barca.controller;

import com.lonja.hielo_barca.dto.ResumenHieloBarcaDiaDTO;
import com.lonja.hielo_barca.dto.ResumenHieloDTO;
import com.lonja.hielo_barca.model.Hielo;
import com.lonja.hielo_barca.service.HieloService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hielo")
public class HieloController {
    private final HieloService hieloService;

    public HieloController(HieloService hieloService) {
        this.hieloService = hieloService;
    }

    @GetMapping("/barca/{barcaId}")
    public List<Hielo> buscarPorBarcaId(@PathVariable Long barcaId){
        return hieloService.buscarPorBarcaId(barcaId);
    }

    @GetMapping("fecha/{fecha}")
    public List<Hielo>  buscarPorFecha(@PathVariable String fecha){
        return hieloService.buscarPorFecha(LocalDate.parse(fecha));
    }

    @GetMapping("/barca/{barcaId}/fecha/{fecha}")
    List<Hielo> buscarPorBarcaIdYFecha(@PathVariable Long barcaId, @PathVariable String fecha){
        return hieloService.buscarPorBarcaIdYFecha(barcaId, LocalDate.parse(fecha));
    }

    @PostMapping
    public Hielo guardarHielo(@RequestBody Hielo hielo){
        return hieloService.guardarHielo(hielo);
    }

    @DeleteMapping("/{id}")
    void eliminarHielo(@PathVariable Long id){
        hieloService.eliminarHielo(id);
    }

    @PutMapping("/{id}")
    Hielo actualizarHielo(@PathVariable Long id, @RequestBody Hielo nuevoHielo){
        return hieloService.actualizarHielo(id, nuevoHielo);

    }

    @GetMapping("/{id}")
    Hielo buscarPorId(@PathVariable Long id){
        return hieloService.buscarPorId(id);
    }

    @GetMapping("/resumen")
    public List<ResumenHieloDTO> obtenerResumen(
            @RequestParam String inicio,
            @RequestParam String fin,
            @RequestParam(defaultValue = "1.0") double precio
    ) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);
        return hieloService.obtenerResumenEntreFechas(fechaInicio, fechaFin, precio);
    }

    @GetMapping("/barca/{barcaId}/rango")
    public List<Hielo> buscarPorBarcaYFechas(
            @PathVariable Long barcaId,
            @RequestParam String desde,
            @RequestParam String hasta
    ) {
        LocalDate fechaDesde = LocalDate.parse(desde);
        LocalDate fechaHasta = LocalDate.parse(hasta);
        return hieloService.buscarPorBarcaEntreFechas(barcaId, fechaDesde, fechaHasta);
    }

    @GetMapping("/resumen-dia")
    public List<ResumenHieloBarcaDiaDTO> resumenPorDia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ) {
        return hieloService.resumenPorDia(fecha);
    }




}
