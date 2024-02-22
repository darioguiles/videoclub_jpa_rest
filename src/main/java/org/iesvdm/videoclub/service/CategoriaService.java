package org.iesvdm.videoclub.service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private EntityManager em;

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria replace(Long id, Categoria categoria) {

        return this.categoriaRepository.findById(id).map( p -> (id.equals(categoria.getId())  ?
                        this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new CategoriaNotFoundException(id));

    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {this.categoriaRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }


    public List<Categoria> allByQueryFiltersStream(Optional<String> buscarOpc, Optional<String> ordenarOpt) {
        StringBuilder queryBuilder = new StringBuilder("SELECT C from Categoria C");
        if (buscarOpc.isPresent())
        {
            queryBuilder.append(" ").append("WHERE C.nombre like :nombre");
        }
        if (ordenarOpt.isPresent())
        {
            if (buscarOpc.isPresent() && "asc".equalsIgnoreCase(ordenarOpt.get()) ) {
                queryBuilder.append(" ").append("ORDER BY C.nombre ASC");
            } else if(buscarOpc.isPresent() && "desc".equalsIgnoreCase(ordenarOpt.get())) {
                queryBuilder.append(" ").append("ORDER BY C.nombre DESC");
            }
        }
        //Consulta JPQL, sintaxis SQL pero con una entidad JPA
        Query query = em.createQuery(queryBuilder.toString());
        if (buscarOpc.isPresent()){
            query.setParameter("nombre", "%"+buscarOpc.get()+"%");
        }

        return query.getResultList();
    }
}
