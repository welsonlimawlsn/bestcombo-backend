package br.com.bestcombo.core.seguranca.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.bestcombo.core.pessoas.enums.TipoPessoa;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autoriza {

    TipoPessoa[] value();

}
