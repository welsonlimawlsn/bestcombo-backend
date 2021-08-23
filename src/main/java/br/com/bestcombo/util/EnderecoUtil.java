package br.com.bestcombo.util;

import java.util.regex.Pattern;

public class EnderecoUtil {

    public static final Pattern CEO_COM_FORMATACAO = Pattern.compile("\\d{5}-\\d{3}");

    public static final Pattern CEP_SEM_FORMATACAO = Pattern.compile("\\d{8}");

    private EnderecoUtil() {
    }

    public static boolean cepEhValido(String cep) {
        if (cep == null || cep.isEmpty()) {
            return false;
        }
        return CEO_COM_FORMATACAO.matcher(cep).matches() || CEP_SEM_FORMATACAO.matcher(cep).matches();
    }

}
