package br.com.bestcombo.core.movimentoconta.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TipoMovimentoContaEnumConverter implements AttributeConverter<TipoMovimentoConta, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoMovimentoConta attribute) {
        return attribute.getCodigo();
    }

    @Override
    public TipoMovimentoConta convertToEntityAttribute(Integer dbData) {
        return TipoMovimentoConta.getPorCodigo(dbData);
    }

}
