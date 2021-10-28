package br.com.bestcombo.core.categoria.dto.listacategorias;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.categoria.dto.CategoriaDTO;

@Getter
@Setter
public class ListaCategoriasRespostaDTO extends RespostaDTO {

    private List<CategoriaDTO> categorias;

}
