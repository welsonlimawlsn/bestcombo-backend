package br.com.bestcombo.core.movimentoconta.mapper;

import java.util.function.Function;

import br.com.bestcombo.core.movimentoconta.dto.MovimentoContaDTO;
import br.com.bestcombo.core.movimentoconta.entity.MovimentoContaEntity;
import br.com.bestcombo.core.movimentoconta.enums.TipoMovimentoConta;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;

public class MovimentoContaMapper {

    public static MovimentoContaDTO mapperParaDTO(MovimentoContaEntity entity) {
        return MovimentoContaDTO.builder()
                .codigoPedido(nullable(entity.getPedido(), PedidoEntity::getCodigo, null))
                .codigo(entity.getCodigo())
                .dataHora(entity.getDataHora())
                .tipoMovimento(entity.getTipoMovimento())
                .dataHoraEfetivacao(entity.getDataHoraEfetivacao())
                .descricao(entity.getDescricao())
                .efetivado(entity.getEfetivado())
                .valor(entity.getTipoMovimento().equals(TipoMovimentoConta.CREDITO) ? entity.getValor() : entity.getValor().negate())
                .build();
    }

    public static  <T, R> R nullable(T t, Function<T, R> ifNotNull, R ifNull) {
        return t != null ? ifNotNull.apply(t) : ifNull;
    }

}
