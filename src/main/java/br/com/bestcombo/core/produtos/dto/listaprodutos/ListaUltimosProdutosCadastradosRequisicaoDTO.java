package br.com.bestcombo.core.produtos.dto.listaprodutos;

import lombok.Getter;
import lombok.Setter;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@CasoDeUso(CasosDeUso.LISTA_ULTIMOS_PRODUTOS_CADASTRADOS)
@Getter
@Setter
public class ListaUltimosProdutosCadastradosRequisicaoDTO extends RequisicaoDTO<ListaUltimosProdutosCadastradosRespostaDTO> {

}
