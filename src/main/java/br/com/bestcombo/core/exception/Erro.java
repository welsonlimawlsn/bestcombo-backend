package br.com.bestcombo.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Erro {

    CEP_INVALIDO(1, "CEP inválido."),
    ATRIBUTO_INVALIDO(2, "Atributo inválido."),
    PARCEIRO_NAO_ENCONTRADO(3, "Parceiro não encontrado."),
    PESSOA_JA_CADASTRADA(4, "Pessoa já cadastrada."),
    LOJA_NAO_ENCONTRADA(5, "Loja não encontrada."),
    TIPO_USUARIO_INVALIDO_CADASTRO_LOJA(6, "É necessarop que o usuário seja um parceira para fazer o cadastro de uma loja."),
    LOJA_JA_CADASTRADA(7, "Essa loja já está cadastrada."),
    LOJA_INVALIDA(8, "Está loja está invalida"),
    QUANTIDADE_ARQUIVOS_INVALIDA(9, "Quantidade de arquivos inválida."),
    ARQUIVO_NAO_ENCONTRADO(10, "Arquivo não encontrado."),
    PRODUTO_NAO_ENCONTRADO(11, "Produto não encontrado."),
    PEDIDO_NAO_PODE_SER_ACEITO(12, "Pedido não pode mais ser aceito"),
    PEDIDO_NAO_ENCONTRADO(13, "Pedido não encontrado"),
    PEDIDO_INVALIDO_CRIAR_MOVIMENTO(14, "Pedido inválido para criar movimento na conta"),
    MOVIMENTO_JA_EFETIVADO(15, "Movimento já efetivado"),
    MOVIMENTO_FUTURO_NEGATIVO(16, "Não é permitido movimento futuro negativo"),
    MOTIVO_RECUSA_OBRIGATORIO(17, "Em caso de recusar o pedido, é necessario justificar o motivo"),
    SITUACAO_INVALIDA(18, "Situação inválida"),
    CHAVE_PIX_NAO_PODE_SER_ALTERADA(19, "Chave pix, depois de inserida, não pode ser alterada"),
    JA_POSSUI_UMA_SOLICITACAO_EM_ANDAMENTO(20, "Já possui uma solicitação de saque andamento"),
    NENHUMA_SOLICITACAO_EM_ANDAMENTO(21, "Nenhuma solicitação de saque em andamento");

    private final Integer codigo;

    private final String descricao;

    @Override
    public String toString() {
        return "Erro{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
