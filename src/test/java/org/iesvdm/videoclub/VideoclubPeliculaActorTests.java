package org.iesvdm.videoclub;


import org.iesvdm.videoclub.domain.Actor;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.repository.ActorRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@SpringBootTest
public class VideoclubPeliculaActorTests {

    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    ActorRepository actorRepository;
    @Test
    public void crearPeliculaActor(){

        Pelicula p = new Pelicula(0,"Pelicula1", new HashSet<>(), new HashSet<>());
        peliculaRepository.save(p);

        Actor a1 = new Actor(0, "Brad","Pitt", new HashSet<>());
        actorRepository.save(a1);
        Actor a2 = new Actor(0, "Joaquin","Phoenix", new HashSet<>());
        actorRepository.save(a2);


        p.getActores().add(a1);
        a1.getPeliculas().add(p);
        p.getActores().add(a2);
        a2.getPeliculas().add(p);


        //Y ahora un save al final
        peliculaRepository.save(p);
        actorRepository.save(a1);
        actorRepository.save(a2);

    }

    @Test @Order(2)
    public void desvincularActor()
    {
        Actor a1 = actorRepository.findById(1L).orElse(null);

        //Borramos el actor
        Pelicula pelicula = peliculaRepository.findById(1L).orElse(null);

        pelicula.getActores().remove(a1);
        a1.getPeliculas().remove(pelicula);

        actorRepository.save(a1);


    }

    @Test @Order(3)
    public void borrarActor()
    {
        Actor c = actorRepository.findById(2L).orElse(null);

        //En este caso habria que desvincular y borrar pero vamos a ver que sucede si borramos directamente
        // Pelicula pelicula = peliculaRepository.findById(1L).orElse(null);

        actorRepository.delete(c);

        //Funciona, lo borra de la tabla actor_peliculas y de actor esto es así al ser el lado debil
    }

    @Test @Order(4)
    public void borrarPelicula()
    {
        Pelicula p = peliculaRepository.findById(1L).orElse(null);
        //Al no tener ninguna pelicula vinculada podemos borrar la pelicula directamente si no habria que borrar todas las asociadas
        //Vamos a hacerlo de todas formas.

        actorRepository.deleteAllInBatch(p.getActores());

        peliculaRepository.delete(p); //<- Funciona, cuidado que en el caso de los test se elimina tras haber dejado al hijo huerfano pero está bien implementado
        //Se carga lo relacionado, para comprobarlo se puede añadir una peli 2 y actores asociados a esa pelicula para ver si afecta
    }



}
