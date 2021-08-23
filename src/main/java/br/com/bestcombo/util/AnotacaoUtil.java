package br.com.bestcombo.util;

import java.lang.annotation.Annotation;

import br.com.bestcombo.core.exception.InfraestruturaException;

public class AnotacaoUtil {

    private AnotacaoUtil() {
    }

    public static <ANOTACAO extends Annotation> ANOTACAO getAnotacao(Object o, Class<ANOTACAO> anotacaoClass) {
        ANOTACAO anotacao = o.getClass().getAnnotation(anotacaoClass);

        if (anotacao == null) {
            throw new InfraestruturaException(String.format("A classe %s não possui a anotacao %s, considere adiciona-la.", o.getClass().getName(), anotacaoClass.getName()));
        }

        return anotacao;
    }

}
