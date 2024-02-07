package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "tutorial" ,
        schema = "videoclub_jpa",
        indexes = {@Index(name = "titulo_index", columnList = "titulo",unique = false)}

)
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_tutorial;
    @Column(name = "titulo", length = 50)
    private String titulo;
    @Column(name = "descripcion", length = 150)
    private String descripcion;

    private boolean publicado;

    @Column(name = "fechaPublicacion" ,nullable = false)
    //Metemos la anotacion fecha??
    private Date fechaPublicacion;

    @OneToMany(mappedBy = "tutorial")
   private List<Comentario> comentarios;
}
