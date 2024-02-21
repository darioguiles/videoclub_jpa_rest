package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    //Esto es diferente de aplicación Query ->
    public List<Categoria> findByNombreContainingIgnoreCase (String titulo);
    public List<Categoria> findByNombreContainingIgnoreCaseOrderByNombreAsc (String titulo);

    public List<Categoria> findByNombreContainingIgnoreCaseOrderByNombreDesc (String titulo);

}
