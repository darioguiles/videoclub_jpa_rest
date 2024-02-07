package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
