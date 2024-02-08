package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tarjeta {
    @Id
    private long numero;
    private Date caducidad;
    @OneToOne
    @JoinColumn(name = "socio_id_fk", nullable = true, referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "FK_SOCIO"))
    private Socio socio;
}
