package br.com.bestcombo.core.casosdeuso.anotacao;

import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;

import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@RequiredArgsConstructor
public class CasoDeUsoSeletor implements CasoDeUso {

    private final CasosDeUso casoDeUso;

    @Override
    public CasosDeUso value() {
        return casoDeUso;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return CasoDeUso.class;
    }

}
