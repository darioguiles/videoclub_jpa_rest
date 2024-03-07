package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("v1/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamanio","!order"})
    public List<Pelicula> all() {
        log.info("Accediendo a todas las películas");
        return this.peliculaService.all();
    }


    @GetMapping(value = {"","/"}, params = {"!order"})
    public ResponseEntity<Map<String,Object>> all(@RequestParam( value = "pagina", defaultValue = "0") int pagina
            , @RequestParam(value = "tamanio" , defaultValue = "3") int tamanio) {
        log.info("Accediendo a todas las películas con paginacion");

        Map<String, Object> responseAll = this.peliculaService.all(pagina, tamanio);

        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamanio"})
    public List<Pelicula> all(@RequestParam( value = "order", defaultValue = "idPelicula,asc") String[] order) {


        log.info("Accediendo a todos los parametros con el orden " + order[0] + " y " + order[1] );

        //Problemas: titulo (error en el order[1] al estar vacio)
        // titulo, no se da ordenación y se queda vacio, el default no funciona?
        // TODO fixear estos errores o buscar alternativas

        return this.peliculaService.all(order);
    }


    @PostMapping({"","/"})
    public Pelicula newPelicula(@RequestBody Pelicula pelicula) {
        return this.peliculaService.save(pelicula);
    }

    @GetMapping("/{id}")
    public Pelicula one(@PathVariable("id") Long id) {
        return this.peliculaService.one(id);
    }

    @PutMapping("/{id}")
    public Pelicula replacePelicula(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) {
        return this.peliculaService.replace(id, pelicula);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable("id") Long id) {
        this.peliculaService.delete(id);
    }


}
