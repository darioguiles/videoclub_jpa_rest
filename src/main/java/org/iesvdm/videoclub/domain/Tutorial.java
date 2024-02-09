package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
/*@Table(
        name = "tutorial" ,
        schema = "videoclub_jpa",
        indexes = {@Index(name = "titulo_index", columnList = "titulo",unique = false)}

)*/
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    private String titulo;
  /*  Comentamos para los tests
  @Column(name = "descripcion", length = 150)
    private String descripcion;

    private boolean publicado;

    @Column(name = "fechaPublicacion" ,nullable = false)
    //Metemos la anotacion fecha??
    private Date fechaPublicacion;*/

    @OneToMany(mappedBy = "tutorial", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
   private Set<Comentario> comentarios;
}
