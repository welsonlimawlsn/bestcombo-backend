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
    CADASTRO_PRODUTO(7);

    private final Integer codigo;

    @Override
    public String toString() {
        return "CasosDeUso{" +
                "codigo=" + codigo +
                ", descricao=" + super.toString() +
                '}';
    }
}
