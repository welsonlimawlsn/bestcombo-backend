package br.com.bestcombo.core.solicitacaosaque.mapper;

import br.com.bestcombo.core.solicitacaosaque.dto.SolicitacaoSaqueDTO;
import br.com.bestcombo.core.solicitacaosaque.entity.SolicitacaoSaqueEntity;

public class SolicitacaoSaqueMapper {

    public static SolicitacaoSaqueDTO mapperParaDTO(SolicitacaoSaqueEntity entity) {
        return SolicitacaoSaqueDTO.builder()
                .chavePix(entity.getChavePix())
                .codigo(entity.getCodigo())
                .situacaoSolicitacaoSaque(entity.getSituacaoSolicitacaoSaque())
                .dataCadastro(entity.getDataCadastro())
                .dataUltimaAtualizacao(entity.getDataUltimaAtualizacao())
                .valor(entity.getValor())
                .build();
    }

}
