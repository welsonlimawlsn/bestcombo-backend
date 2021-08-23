package br.com.bestcombo.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Erro {

    CEP_INVALIDO(1, "CEP inválido."),
    ATRIBUTO_INVALIDO(2, "Atributo inválido."),
    PARCEIRO_NAO_ENCONTRADO(3, "Parceiro não encontrado.");

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
