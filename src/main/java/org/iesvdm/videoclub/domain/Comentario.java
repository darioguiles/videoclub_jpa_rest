package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Evitar duplicados con hash
    @EqualsAndHashCode.Include
    private long id_comentario;
    @Column(name = "content", length = 100)
    private String content;

    @ManyToOne
    @JoinColumn(name = "tutorial_id_fk" , nullable = false,
            referencedColumnName = "id_tutorial",
            foreignKey = @ForeignKey(name = "FK_TUTO"))
    @ToString.Exclude
    Tutorial tutorial;

}
