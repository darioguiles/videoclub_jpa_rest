package org.iesvdm.videoclub;


import org.iesvdm.videoclub.domain.Actor;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.repository.ActorRepository;
import org.iesvdm.videoclub.repository.PeliculaRepository;
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


        //Y ahora un save al final
        peliculaRepository.save(p);


    }
}
