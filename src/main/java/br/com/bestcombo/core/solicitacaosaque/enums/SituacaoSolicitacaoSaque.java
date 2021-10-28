package br.com.bestcombo.core.solicitacaosaque.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum SituacaoSolicitacaoSaque {
    SOLICITADO(1),
    EM_ANDAMENTO(2),
    CONCLUIDO(3),
    ;

    private final Integer codigo;

    public static SituacaoSolicitacaoSaque getPorCodigo(Integer codigo) {
        return Stream.of(values())
                .filter(situacaoSolicitacaoSaque -> situacaoSolicitacaoSaque.codigo.equals(codigo))
                .findFirst()
                .orElseThrow();
    }

}
