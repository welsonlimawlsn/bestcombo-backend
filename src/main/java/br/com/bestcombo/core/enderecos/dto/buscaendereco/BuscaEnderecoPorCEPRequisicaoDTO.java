package br.com.bestcombo.core.enderecos.dto.buscaendereco;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.PathParam;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.validacao.CEP;

@Getter
@Setter
@CasoDeUso(CasosDeUso.BUSCA_ENDERECO_POR_CEP)
public class BuscaEnderecoPorCEPRequisicaoDTO extends RequisicaoDTO<BuscaEnderecoPorCEPRespostaDTO> {

    @NotEmpty(message = "O CEP é obrigatório.")
    @CEP(message = "O CEP informado é invalido.")
    @PathParam("cep")
    private String cep;

}
