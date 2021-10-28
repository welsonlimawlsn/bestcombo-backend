package br.com.bestcombo.core.loja.dto.buscalojaporparceiro;

import lombok.Getter;
import lombok.Setter;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.enderecos.dto.EnderecoDTO;

@Getter
@Setter
public class BuscaLojaPorParceiroRespostaDTO extends RespostaDTO {

    private String codigo;

    private String cnpj;

    private String nome;

    private EnderecoDTO endereco;

    private String chavePix;

}
