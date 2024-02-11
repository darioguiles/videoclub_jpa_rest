package org.iesvdm.videoclub.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @ManyToMany(mappedBy = "actores") //Mapeamos la relaci´pn
    @JsonIgnore
    private Set<Pelicula> peliculas = new HashSet<>();
}
