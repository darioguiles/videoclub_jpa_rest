package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private long id;
    @Column(name = "titulo", length = 50)
    private String titulo;
    @Column(name = "descripcion", length = 150)
    private String descripcion;
    private boolean publicado;

    @OneToMany(mappedBy = "tutorial")
   private List<Comentario> comentarios;
}
