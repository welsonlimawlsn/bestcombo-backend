package br.com.bestcombo.core.pessoas.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TipoPessoa {

    PARCEIRO(1, "usuario_externo_parceiro", "PAPEL_PARCEIRO"),
    CLIENTE(2, "usuario_externo_cliente", "PAPEL_CLIENTE"),
    ADMINISTRADOR(2, "", "PAPEL_ADMINISTRADOR"),
    PUBLICO(3, "", "PAPEL_PUBLICO");

    private final Integer codigo;

    private final String grupo;

    private final String papel;
}
