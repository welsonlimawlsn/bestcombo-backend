package br.com.bestcombo.core.movimentoconta.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum TipoMovimentoConta {
    DEBITO(2, "Débito em conta"),
    CREDITO(1, "Crédito em conta"),
    ;

    private final Integer codigo;

    private final String descricao;

    public static TipoMovimentoConta getPorCodigo(Integer codigo) {
        return Arrays.stream(values())
                .filter(tipoMovimentoConta -> tipoMovimentoConta.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow();
    }

}
