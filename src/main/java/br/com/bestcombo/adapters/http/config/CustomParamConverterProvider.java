package br.com.bestcombo.adapters.http.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import br.com.bestcombo.core.exception.InfraestruturaException;

@Provider
public class CustomParamConverterProvider implements ParamConverterProvider {

    private static final Map<Class<?>, Class<? extends ParamConverter<?>>> PARAMETERS_CONVERTER;

    static {
        PARAMETERS_CONVERTER = new HashMap<>();
        PARAMETERS_CONVERTER.put(ZonedDateTime.class, ZonedDateTimeParamConverter.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {

        Class<? extends ParamConverter<?>> converterClass = PARAMETERS_CONVERTER.get(aClass);

        if (converterClass != null) {
            try {
                return (ParamConverter<T>) converterClass.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new InfraestruturaException("Erro ao selecionar o param converter", e);
            }
        }
        return null;
    }

}
