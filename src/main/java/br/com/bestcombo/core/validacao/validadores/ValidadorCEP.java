package br.com.bestcombo.core.validacao.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.bestcombo.core.validacao.CEP;
import br.com.bestcombo.util.EnderecoUtil;

public class ValidadorCEP implements ConstraintValidator<CEP, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        return EnderecoUtil.cepEhValido(value);
    }

}
