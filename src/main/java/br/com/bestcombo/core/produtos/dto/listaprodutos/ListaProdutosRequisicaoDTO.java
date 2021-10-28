package br.com.bestcombo.core.produtos.dto.listaprodutos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.ws.rs.QueryParam;

import br.com.bestcombo.adapters.dao.anotacao.Coluna;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.QueryRequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.LISTA_PRODUTOS)
public class ListaProdutosRequisicaoDTO extends QueryRequisicaoDTO<ListaProdutosRespostaDTO> {

    @Coluna("loja.codigo")
    @QueryParam("codigoEstabelecimento")
    private UUID codigoEstabelecimento;

}
