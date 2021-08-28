package br.com.bestcombo.core.pessoas.dto.listapessoas;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.pessoas.dto.PessoaDTO;

@Getter
@Setter
public class ListaPessoasRespostaDTO extends RespostaDTO {

    private List<PessoaDTO> pessoas;

}
