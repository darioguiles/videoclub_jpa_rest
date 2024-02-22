package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Pelicula;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    //
    public List<Pelicula> findByTituloContainingIgnoreCaseOrderByTituloDesc(String titulo);

    public Page<Pelicula> findAllBy (Pageable paginado);

}
