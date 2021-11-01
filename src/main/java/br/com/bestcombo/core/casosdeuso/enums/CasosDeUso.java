package br.com.bestcombo.core.casosdeuso.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CasosDeUso {

    NOVO_PARCEIRO(1),
    BUSCA_PARCEIRO_POR_CODIGO(2),
    LISTA_PARCEIROS(3),
    BUSCA_LOJA_POR_PARCEIRO(4),
    CADASTRA_LOJA(5),
    LISTA_LOJAS(6),
    CADASTRO_PRODUTO(7),
    LISTA_PRODUTOS(8),
    CADASTRO_CLIENTE(9),
    DOWNLOAD_IMAGEM(10),
    UPLOAD_IMAGEM(11),
    BUSCA_ENDERECO_POR_CEP(12),
    LISTA_CATEGORIAS(13),
    BUSCA_LOJA_POR_TERMO(14),
    BUSCA_PRODUTO_POR_CODIGO(15),
    NOVO_PEDIDO(16),
    ALTERA_SITUACAO_PEDIDO(17),
    ATUALIZA_PAGAMENTO_TODOS_PEDIDOS(18),
    LISTA_PEDIDOS(19),
    BUSCA_PEDIDO_POR_CODIGO(20),
    CONSULTA_SALDOS_PARCEIROS(21),
    LISTA_MOVIMENTOS_DATA_INICIO_FIM_PARCEIRO(22),
    ADICIONA_CHAVE_PIX(23),
    NOVA_SOLICITACAO_SAQUE(24),
    CONSULTA_SOLICITACAO_SAQUE_ANDAMENTO(25),
    ALTERA_SITUACAO_SOLICITACAO_SAQUE(26),
    LISTA_SOLICITACOES_SAQUE_PENDENTES(27),
    CONSULTA_SALDOS_BESTCOMBO(28),
    LISTA_MOVIMENTOS_DATA_INICIO_FIM_BESTCOMBO(29),
    EXCLUI_PRODUTO(30);

    private final Integer codigo;

    @Override
    public String toString() {
        return "CasosDeUso{" +
                "codigo=" + codigo +
                ", descricao=" + super.toString() +
                '}';
    }
}
