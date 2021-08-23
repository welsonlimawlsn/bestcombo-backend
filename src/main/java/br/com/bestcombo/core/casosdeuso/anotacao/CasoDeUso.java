package br.com.bestcombo.core.casosdeuso.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Qualifier
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CasoDeUso {

    CasosDeUso value();

}
