package org.iesvdm.videoclub;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@SpringBootTest
public class VideoclubPeliculaCategoriaTests {

    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    /* PRUEBA TEST DE PELICULA CATEGORIA */


    //TEST FUNCIONALES
    @Test @Order(1)
    public void crearPeliculaYCategorias(){
            //CUIDADO AL HACER TEST DE POSTMAN QUE SI SE LLAMAN IGUALES, AL SER HASHSET NOS DA ERROR!!!
            Pelicula p = new Pelicula(0,"Pelicula1", new HashSet<>(), new HashSet<>());
            peliculaRepository.save(p);

            Categoria categoria1 = new Categoria(0,"Cat1",new HashSet<>());
            categoriaRepository.save(categoria1);

            Categoria categoria2 = new Categoria(0,"Cat2",new HashSet<>());
            categoriaRepository.save(categoria2);
            //Hay inserción pero no relación

            p.getCategorias().add(categoria1);
            categoria1.getPeliculas().add(p);
            p.getCategorias().add(categoria2);
            categoria2.getPeliculas().add(p);

            //Y ahora un save al final
            peliculaRepository.save(p);
            categoriaRepository.save(categoria1);
            categoriaRepository.save(categoria2);

    }
    @Test @Order(2)
    public void desvincularCategoria()
    {
        //Actualmente estamos con la aplicación del EAGER, en caso de hacerlo con LAZY, usamos transactional o manejamos el objeto de forma precisa.
        Categoria cat1 = categoriaRepository.findById(1L).orElse(null);

        //Borramos la categoria
        Pelicula pelicula = peliculaRepository.findById(1L).orElse(null);

        pelicula.getCategorias().remove(cat1);
        cat1.getPeliculas().remove(pelicula);

        //La desvinculación solo es posible por el lado no propietario o débil
        //El lado fuerte cuenta con el mappedBy
        categoriaRepository.save(cat1);

    }


    @Test @Order(3)
    public void borrarCategoria()
    {
        Categoria c = categoriaRepository.findById(2L).orElse(null);

        //En este caso habria que desvincular y borrar pero vamos a ver que sucede si borramos directamente
       // Pelicula pelicula = peliculaRepository.findById(1L).orElse(null);

        categoriaRepository.delete(c);

        //Funciona, lo borra de la tabla categoria_peliculas y de categoria
    }

    @Test @Order(4)
    public void borrarPelicula()
    {
        Pelicula p = peliculaRepository.findById(1L).orElse(null);
        //Al no tener ninguna pelicula vinculada podemos borrar la pelicula directamente si no habria que borrar todas las asociadas
        //Vamos a hacerlo de todas formas.

        categoriaRepository.deleteAllInBatch(p.getCategorias());

        peliculaRepository.delete(p);
    }



}
