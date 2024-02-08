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
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "DNI", length = 15, nullable = false)
    private String DNI;
    @Column(name = "nombre", length = 15)
    private String nombre;
    private String apellidos;

    @OneToOne
    private Tarjeta tarjeta;
}
