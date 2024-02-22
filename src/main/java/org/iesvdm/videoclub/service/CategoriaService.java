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

   /* @Autowired
    private EntityManager em;*/

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



    /*
    * Este metodo se puede conformar por las 2 formas, la forma Que usamos ahora mediante
    * JPARepository de forma simple mediante métodos con formas predefinidas O con JPQL para lo cual nos ha falta StringBuilder para construir las queries
    * Query y un EntityManager.
    *
    * */
    public List<Categoria> allByQueryFiltersStream(Optional<String> buscarOpc, Optional<String> ordenarOpt) {

        List<Categoria> resultado = null;
        //StringBuilder queryBuilder = new StringBuilder("SELECT C from Categoria C");
        if (buscarOpc.isPresent())
        {
            //queryBuilder.append(" ").append("WHERE C.nombre like :nombre");
            resultado = categoriaRepository.findByNombreContainingIgnoreCase(buscarOpc.get());
        }
        if (ordenarOpt.isPresent())
        {
            if (buscarOpc.isPresent() && "asc".equalsIgnoreCase(ordenarOpt.get()) ) {
               // queryBuilder.append(" ").append("ORDER BY C.nombre ASC");
                resultado = categoriaRepository.findByNombreContainingIgnoreCaseOrderByNombreAsc(buscarOpc.get());

            } else if(buscarOpc.isPresent() && "desc".equalsIgnoreCase(ordenarOpt.get())) {
                //queryBuilder.append(" ").append("ORDER BY C.nombre DESC");
                resultado = categoriaRepository.findByNombreContainingIgnoreCaseOrderByNombreDesc(buscarOpc.get());
            }
        }
        //Consulta JPQL, sintaxis SQL pero con una entidad JPA
       /* Query query = em.createQuery(queryBuilder.toString());
        if (buscarOpc.isPresent()){
            query.setParameter("nombre", "%"+buscarOpc.get()+"%");
        }*/
        //return query.getResultList();

        return resultado;
    }
}
