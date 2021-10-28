package br.com.bestcombo.core.pedidos.casodeuso;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.enderecos.dto.EnderecoDTO;
import br.com.bestcombo.core.enderecos.mapper.EnderecoPessoaMapper;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.mapper.LojaMapper;
import br.com.bestcombo.core.pedidos.dto.pedidoporcodigo.BuscaPedidoPorCodigoRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.pedidoporcodigo.BuscaPedidoPorCodigoRespostaDTO;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.core.pedidos.entity.ProdutoPedidoEntity;
import br.com.bestcombo.core.pessoas.dto.PessoaDTO;
import br.com.bestcombo.core.pessoas.mapper.PessoaMapper;
import br.com.bestcombo.core.produtos.dto.ProdutoDTO;
import br.com.bestcombo.core.produtos.mapper.ProdutoMapper;
import br.com.bestcombo.ports.dao.PedidoDAO;
import br.com.bestcombo.ports.service.SegurancaService;

@ApplicationScoped
@RequiredArgsConstructor
@CasoDeUso(CasosDeUso.BUSCA_PEDIDO_POR_CODIGO)
public class BuscaPedidoPorCodigoCasoDeUso extends AbstractCasoDeUso<BuscaPedidoPorCodigoRequisicaoDTO, BuscaPedidoPorCodigoRespostaDTO> {

    final SegurancaService segurancaService;

    final PedidoDAO pedidoDAO;

    @Override
    protected void processa(BuscaPedidoPorCodigoRequisicaoDTO req, BuscaPedidoPorCodigoRespostaDTO res) throws NegocioException {
        UUID codigoUsuarioLogado = segurancaService.getCodigoUsuarioLogado();

        Optional<PedidoEntity> pedido = Optional.empty();

        if (segurancaService.isParceiro()) {
            pedido = pedidoDAO.buscaPorIdECodigoParceiro(req.getCodigo(), codigoUsuarioLogado);
        } else if (segurancaService.isCliente()) {
            pedido = pedidoDAO.buscaPorIdECodigoCliente(req.getCodigo(), codigoUsuarioLogado);
        }

        PedidoEntity pedidoEntity = pedido.orElseThrow();

        preencheResposta(res, pedidoEntity);
    }

    private void preencheResposta(BuscaPedidoPorCodigoRespostaDTO res, PedidoEntity pedidoEntity) {
        res.setCodigo(pedidoEntity.getCodigo());
        res.setCliente(mapperParaPessoa(pedidoEntity));
        res.setDataHoraAgendamento(pedidoEntity.getDataHoraAgendamento());
        res.setProdutos(pedidoEntity.getProdutos().stream().map(this::mapperParaProduto).collect(Collectors.toList()));
        res.setSituacao(pedidoEntity.getSituacao());
        res.setLoja(LojaMapper.mapperParaDTO(pedidoEntity.getLoja()));
        res.setDataHoraCadastro(pedidoEntity.getDataHoraCadastro());
        res.setValorTotal(pedidoEntity.getValorTotal());
        res.setDataHoraUltimaAtualizacao(pedidoEntity.getDataHoraUltimaAtualizacao());
        res.setMotivoCancelamento(pedidoEntity.getMotivoCancelamento());
        res.setObservacao(pedidoEntity.getObservacao());
    }

    private ProdutoDTO mapperParaProduto(ProdutoPedidoEntity produtoPedidoEntity) {
        ProdutoDTO dto = ProdutoMapper.mapperParaDTO(produtoPedidoEntity.getProduto());
        dto.setQuantidade(produtoPedidoEntity.getQuantidade());
        return dto;
    }

    private PessoaDTO mapperParaPessoa(PedidoEntity pedidoEntity) {
        PessoaDTO pessoaDTO = PessoaMapper.mapperParaDTO(pedidoEntity.getCliente());
        pessoaDTO.setEnderecos(mapperParaEndereco(pedidoEntity));
        return pessoaDTO;
    }

    private List<EnderecoDTO> mapperParaEndereco(PedidoEntity pedidoEntity) {
        return pedidoEntity.getCliente().getEnderecos().stream().map(EnderecoPessoaMapper::mapperParaDTO).collect(Collectors.toList());
    }

}
