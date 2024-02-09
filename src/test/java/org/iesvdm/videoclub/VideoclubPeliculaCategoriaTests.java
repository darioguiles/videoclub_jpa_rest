package org.iesvdm.videoclub;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
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

    @Test
    public void crearPelicula(){
        Pelicula p = new Pelicula(0,"Pelicula1", new HashSet<>());
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
}
