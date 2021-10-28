package br.com.bestcombo.core.pedidos.casodeuso;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.mapper.LojaMapper;
import br.com.bestcombo.core.pedidos.dto.PedidoDTO;
import br.com.bestcombo.core.pedidos.dto.listapedidos.ListaPedidosRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.listapedidos.ListaPedidosRespostaDTO;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.ports.dao.PedidoDAO;
import br.com.bestcombo.ports.service.SegurancaService;

@ApplicationScoped
@RequiredArgsConstructor
@CasoDeUso(CasosDeUso.LISTA_PEDIDOS)
public class ListaPedidosCasoDeUso extends AbstractCasoDeUso<ListaPedidosRequisicaoDTO, ListaPedidosRespostaDTO> {

    final SegurancaService segurancaService;

    final PedidoDAO pedidoDAO;

    @Override
    protected void processa(ListaPedidosRequisicaoDTO req, ListaPedidosRespostaDTO res) throws NegocioException {
        UUID codigoUsuarioLogado = segurancaService.getCodigoUsuarioLogado();

        Map<String, Object> queryParameters = req.getQueryParameters();

        if (segurancaService.isCliente()) {
            queryParameters.put("cliente.codigo", codigoUsuarioLogado);
        }

        if (segurancaService.isParceiro()) {
            queryParameters.put("loja.parceiro.codigo", codigoUsuarioLogado);
        }

        Collection<PedidoEntity> pedidos = pedidoDAO.lista(queryParameters);

        res.setPedidos(pedidos.stream()
                .map(pedido -> PedidoDTO.builder()
                        .situacao(pedido.getSituacao())
                        .data(pedido.getDataHoraCadastro())
                        .dataAgendamento(pedido.getDataHoraAgendamento())
                        .codigo(pedido.getCodigo())
                        .valor(pedido.getValorTotal())
                        .loja(LojaMapper.mapperParaDTO(pedido.getLoja()))
                        .build())
                .collect(Collectors.toList()));
    }

}
