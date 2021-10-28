package br.com.bestcombo.core.pedidos.dto.pedidoporcodigo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.loja.dto.LojaDTO;
import br.com.bestcombo.core.pedidos.enums.SituacaoPedido;
import br.com.bestcombo.core.pessoas.dto.PessoaDTO;
import br.com.bestcombo.core.produtos.dto.ProdutoDTO;

@Getter
@Setter
public class BuscaPedidoPorCodigoRespostaDTO extends RespostaDTO {

    private UUID codigo;

    private PessoaDTO cliente;

    private LojaDTO loja;

    private ZonedDateTime dataHoraCadastro;

    private ZonedDateTime dataHoraUltimaAtualizacao;

    private ZonedDateTime dataHoraAgendamento;

    private SituacaoPedido situacao;

    private BigDecimal valorTotal;

    private String observacao;

    private String motivoCancelamento;

    private List<ProdutoDTO> produtos;

}
