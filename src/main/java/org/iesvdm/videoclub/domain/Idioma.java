package org.iesvdm.videoclub.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.Date;
import  java.util.Set;
@Entity
@Table(name="idioma")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Si utilizo @OneToMany(FetchType.LAZY) además debo usar
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_idioma")
    private Long id;
    private String nombre;
 // Omitimos para los test de Peli-Cat

    /*
    @Column(name = "ultima_actualizacion")

    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss",  shape = JsonFormat.Shape.STRING)
    private Date ultimaActualizacion;

    @OneToMany(mappedBy = "idioma")
    //@JsonIgnore
    @ToStringExclude
    private Set<Pelicula> peliculasIdioma;

    //Lado débil, del One to Many, aqui tenemos
    //la lista de peliculas y el idioma en el que está
    // Ej: el sexto sentido en Italiano, Pulp fiction...

    //El idioma original, en su mayoria el Inglés...
    @OneToMany(mappedBy = "idiomaOriginal")
    //@JsonIgnore
        //@JsonBackReference
    @ToStringExclude
    private Set<Pelicula> peliculasIdiomaOriginal;

    */



}
