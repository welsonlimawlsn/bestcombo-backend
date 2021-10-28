package br.com.bestcombo.core.pedidos.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SituacaoPedidoEnumConverter implements AttributeConverter<SituacaoPedido, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SituacaoPedido attribute) {
        return attribute.getCodigo();
    }

    @Override
    public SituacaoPedido convertToEntityAttribute(Integer dbData) {
        return SituacaoPedido.getPorCodigo(dbData);
    }

}
