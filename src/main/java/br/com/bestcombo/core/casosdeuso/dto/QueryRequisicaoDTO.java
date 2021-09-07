package br.com.bestcombo.core.casosdeuso.dto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.QueryParam;

import br.com.bestcombo.adapters.dao.anotacao.Coluna;
import br.com.bestcombo.core.exception.InfraestruturaException;

public class QueryRequisicaoDTO<RESPOSTA extends RespostaDTO> extends RequisicaoDTO<RESPOSTA> {

    public Map<String, Object> getQueryParameters() {

        return Stream.of(this.getClass().getDeclaredFields())
                .filter(this::isValidQueryParameter)
                .collect(Collectors.toMap(this::getAtributo, this::getValor));
    }

    private Object getValor(Field field) {
        try {
            return this.getClass().getMethod(getNameGetterMethod(field)).invoke(this);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new InfraestruturaException("Ocorreu um erro ao montar os query parameters de " + this.getClass().getName(), e);
        }
    }

    private String getNameGetterMethod(Field field) {
        return "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    private String getAtributo(Field field) {
        return field.getAnnotation(Coluna.class).value();
    }

    private boolean isValidQueryParameter(Field f) {
        return f.isAnnotationPresent(QueryParam.class) && f.isAnnotationPresent(Coluna.class) && getValor(f) != null;
    }

}
