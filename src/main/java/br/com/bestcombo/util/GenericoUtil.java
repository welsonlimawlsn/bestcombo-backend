package br.com.bestcombo.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import br.com.bestcombo.core.exception.InfraestruturaException;

public class GenericoUtil {

    public static Class<?> getTipoGenerico(Object o, int posicao) {
        ParameterizedType parameter = (ParameterizedType) o.getClass().getGenericSuperclass();

        return (Class<?>) parameter.getActualTypeArguments()[posicao];
    }

    public static <T> T novoObjeto(Class<T> tClass, Object... parametrosConstrutor) {
        try {
            var objects = Stream.of(parametrosConstrutor)
                    .map(Object::getClass)
                    .toArray((IntFunction<Class<?>[]>) Class[]::new);

            return tClass.getConstructor(objects).newInstance(parametrosConstrutor);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new InfraestruturaException(String.format("Não foi possivel criar um objeto de %s", tClass.getName()), e);
        }
    }

}
