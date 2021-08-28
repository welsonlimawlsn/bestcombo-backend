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
    TIPO_USUARIO_INVALIDO_CADASTRO_LOJA(6, "É necessarop que o usuário seja um parceira para fazer o cadastro de uma loja.");

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
