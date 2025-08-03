package com.lonja.hielo_barca.controller;

import com.lonja.hielo_barca.model.Barca;
import com.lonja.hielo_barca.service.BarcaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controladores REST Exponen la API, recibe las peticiones y devuelve respuestas
@RestController
@RequestMapping("/api/barcas")
public class BarcaController {
    private final BarcaService barcaService;

    public BarcaController(BarcaService barcaService) {
        this.barcaService = barcaService;
    }

    //Obtener barcas por nombre
    @GetMapping("/nombre/{nombre}")
    public List<Barca> encontrarPorNombre(@PathVariable String nombre){
        return barcaService.encontrarPorNombre(nombre);
    }

    @GetMapping("/id/{id}")
    public Barca encontrarPorId(@PathVariable Long id){
        return barcaService.encontrarPorId(id);
    }

    @GetMapping
    public List<Barca> obtenerTodasBarcas(){
        return barcaService.obtenerTodasBarcas();
    }

    //Métodos post@PostMapping → indica que es una petición POST.
    //@RequestBody → Spring convierte el JSON recibido a un objeto Barca.
    //El método devuelve la barca guardada (Spring la convierte a JSON como respuesta).

    @PostMapping("/insertar")
    public Barca crearBarca(@RequestBody Barca barca) {
        return barcaService.guardarBarca(barca);
    }

    @DeleteMapping("/{id}")
    public void eliminarBarca(@PathVariable Long id){
        barcaService.eliminarBarca(id);
    }

    // Nos aseguramos de que actualiza esa barca exacta
    @PutMapping("/{id}")
    public Barca actualizarBarca(@PathVariable Long id, @RequestBody Barca barca) {
        Barca existente = barcaService.encontrarPorId(id);
        if(existente != null) {
            existente.setNombre(barca.getNombre());
            return barcaService.guardarBarca(barca);
        }
        return null;
    }

    // Buscar barcas por contenido de nombre (búsqueda parcial)
    @GetMapping("/buscar/parcial")
    public List<Barca> buscarPorContenido(@RequestParam String nombre) {
        return barcaService.buscarPorContenidoNombre(nombre);
    }









}
