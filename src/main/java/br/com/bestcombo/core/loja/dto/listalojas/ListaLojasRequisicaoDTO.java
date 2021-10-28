package br.com.bestcombo.core.loja.dto.listalojas;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.ws.rs.QueryParam;

import br.com.bestcombo.adapters.dao.anotacao.Coluna;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.QueryRequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@CasoDeUso(CasosDeUso.LISTA_LOJAS)
@Getter
@Setter
public class ListaLojasRequisicaoDTO extends QueryRequisicaoDTO<ListaLojasRespostaDTO> {

    @Coluna("endereco.estado")
    @QueryParam("estado")
    private String estado;

    @Coluna("endereco.cidade")
    @QueryParam("cidade")
    private String cidade;

    @Coluna("codigo")
    @QueryParam("codigo")
    private UUID codigo;

}
