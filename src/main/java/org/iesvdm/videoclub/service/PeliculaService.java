package org.iesvdm.videoclub.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PeliculaService {

    @Autowired
    EntityManager entityManager;
    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> all() {
        return this.peliculaRepository.findAll();
    }

    public Map<String, Object> all(int pagina, int tamanio) {
        //  org.springframework.data.domain public interface Pageable  Maven: org.springframework.data

        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("idPelicula").ascending());
        //Interfaces
        Page<Pelicula> pageAll = this.peliculaRepository.findAll(paginado);

        Map<String,Object> response = new HashMap<>();

        /*
        * Aqui devolvemos un Page filtrado y acotado, solo con los
        * items totales y las paginas totales
        * */

        response.put("peliculas", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

    public List<Pelicula> all(String[] order) {
        //  org.springframework.data.domain public interface Pageable  Maven: org.springframework.data

        List<Pelicula> listaAtributos = new ArrayList<>();

        String parametro = order[0];
        String ordenacion = order[1];
        String queryStr = "select P from Pelicula P";
        Query query = null;



        if (parametro!=null && !parametro.isBlank() && ordenacion!=null && !ordenacion.isBlank())
        {

            if (parametro.equals("idPelicula") || parametro.equalsIgnoreCase("id_pelicula") ){
                queryStr+=" order by P.idPelicula";
            }
            else if (parametro.equals("titulo")) {
                queryStr+=" order by P.titulo";

            }

            if (ordenacion.equalsIgnoreCase("asc"))
            {
                queryStr+=" ASC";
            } else if (ordenacion.equalsIgnoreCase("desc")) {
                queryStr+=" DESC";
            }

        }

        query = entityManager.createQuery(queryStr,Pelicula.class);


        return query.getResultList();
    }





    public Pelicula save(Pelicula pelicula) {
        return this.peliculaRepository.save(pelicula);
    }

    public Pelicula one(Long id) {
        return this.peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula replace(Long id, Pelicula pelicula) {

        return this.peliculaRepository.findById(id).map( p -> (id.equals(pelicula.getIdPelicula())  ?
                                                            this.peliculaRepository.save(pelicula) : null))
                .orElseThrow(() -> new PeliculaNotFoundException(id));

    }

    public void delete(Long id) {
        this.peliculaRepository.findById(id).map(p -> {this.peliculaRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

}
