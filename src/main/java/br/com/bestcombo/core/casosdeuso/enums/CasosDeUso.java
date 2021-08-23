package br.com.bestcombo.core.casosdeuso.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CasosDeUso {

    NOVO_PARCEIRO(1),
    BUSCA_PARCEIRO_POR_CODIGO(2);

    private final Integer codigo;

    @Override
    public String toString() {
        return "CasosDeUso{" +
                "codigo=" + codigo +
                ", descricao=" + super.toString() +
                '}';
    }
}
