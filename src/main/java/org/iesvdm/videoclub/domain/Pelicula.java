package org.iesvdm.videoclub.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="id_pelicula")
    private long idPelicula;


    private String titulo;

   /* datos extra
   private String descripcion;
    @Column(name = "anyo_lanzamiento")
    @JsonFormat(pattern = "yyyy",  shape = JsonFormat.Shape.STRING)
    private Date anyoLanzamiento;

    @ManyToOne()
    @JoinColumn(name = "id_idioma", nullable = false)
    private Idioma idioma;
    //Esta entidad es unica y obligatoria
    //Tenemos una relación Muchos a Uno siendo nuestro lado el del
    // uno el fuerte

    @ManyToOne()
    @JoinColumn(name = "id_idioma_original")
    private Idioma idiomaOriginal;

    @Column(name = "duracion_alquiler")
    private int duracionAlquiler;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;
    private int duracion;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;
    private String clasificacion;

    @Column(name = "caracteristicas_especiales")
    private String caracteristicasEspeciales;
*/
    @ManyToMany
    @JoinTable(
            name = "pelicula_categoria",
            joinColumns = @JoinColumn(name = "id_pelicula", referencedColumnName = "id_pelicula"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria"))

    Set<Categoria> categorias = new HashSet<>(); //<- Instanciado
    //El nombre es lo que hacemos el Mapped by en la otra
/*

    @Column(name = "ultima_actualizacion")
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss",  shape = JsonFormat.Shape.STRING)
    private Date ultimaActualizacion;
*/
//Implementación pelicula - actor
    @ManyToMany
    @JoinTable(
            name = "pelicula_actor",
            joinColumns = @JoinColumn(name = "id_pelicula", referencedColumnName = "id_pelicula"),
            inverseJoinColumns = @JoinColumn(name = "id_actor", referencedColumnName = "id_actor")) //Esto nos sirve para mayor control y con los IDs
        //V
    private Set<Actor> actores = new HashSet<>();


}
