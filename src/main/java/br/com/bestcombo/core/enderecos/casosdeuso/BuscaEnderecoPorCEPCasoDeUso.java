package br.com.bestcombo.core.enderecos.casosdeuso;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.enderecos.dto.buscaendereco.BuscaEnderecoPorCEPRequisicaoDTO;
import br.com.bestcombo.core.enderecos.dto.buscaendereco.BuscaEnderecoPorCEPRespostaDTO;
import br.com.bestcombo.core.enderecos.modelos.Endereco;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.service.EnderecoService;

@CasoDeUso(CasosDeUso.BUSCA_ENDERECO_POR_CEP)
@ApplicationScoped
public class BuscaEnderecoPorCEPCasoDeUso extends AbstractCasoDeUso<BuscaEnderecoPorCEPRequisicaoDTO, BuscaEnderecoPorCEPRespostaDTO> {

    @Inject
    EnderecoService enderecoService;

    @Override
    protected void processa(BuscaEnderecoPorCEPRequisicaoDTO requisicao, BuscaEnderecoPorCEPRespostaDTO resposta) throws NegocioException {
        Endereco endereco = enderecoService.buscaEnderecoPorCep(requisicao.getCep());

        mapperParaResposta(endereco, resposta);
    }

    private void mapperParaResposta(Endereco endereco, BuscaEnderecoPorCEPRespostaDTO resposta) {
        resposta.setCep(endereco.getCep());
        resposta.setBairro(endereco.getBairro());
        resposta.setCidade(endereco.getCidade());
        resposta.setEstado(endereco.getEstado());
        resposta.setRua(endereco.getEndereco());
    }

}
