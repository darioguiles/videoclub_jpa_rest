package org.iesvdm.videoclub.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesvdm.videoclub.domain.Categoria;

@Data
@NoArgsConstructor
public class CategoriaDTO extends Categoria {

    public CategoriaDTO(Categoria categoria){
        super(categoria.getId(), categoria.getNombre(), categoria.getPeliculas());
        this.conteo_pelicula=categoria.getPeliculas().size();
    }

   private int conteo_pelicula;

}
