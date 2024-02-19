package org.iesvdm.videoclub.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="actor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include

    @Column(name = "id_actor")
    private long idActor;

    private String nombre;
    private String apellidos;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Pelicula> peliculas = new HashSet<>();
}
