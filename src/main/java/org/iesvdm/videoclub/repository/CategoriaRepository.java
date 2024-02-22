package org.iesvdm.videoclub.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    //Esto es diferente de aplicación Query ->
    public List<Categoria> findByNombreContainingIgnoreCase (String titulo);
    public List<Categoria> findByNombreContainingIgnoreCaseOrderByNombreAsc (String titulo);
    public List<Categoria> findByNombreContainingIgnoreCaseOrderByNombreDesc (String titulo);
    public long getAllByPeliculas(Set<Pelicula> peliculas); // <- metodo para sacar el conteo al pasarle el set??


}
