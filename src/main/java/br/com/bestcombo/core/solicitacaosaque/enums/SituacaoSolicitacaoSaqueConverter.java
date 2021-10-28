package br.com.bestcombo.core.solicitacaosaque.enums;

import javax.persistence.AttributeConverter;

public class SituacaoSolicitacaoSaqueConverter implements AttributeConverter<SituacaoSolicitacaoSaque, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SituacaoSolicitacaoSaque attribute) {
        return attribute.getCodigo();
    }

    @Override
    public SituacaoSolicitacaoSaque convertToEntityAttribute(Integer dbData) {
        return SituacaoSolicitacaoSaque.getPorCodigo(dbData);
    }

}
