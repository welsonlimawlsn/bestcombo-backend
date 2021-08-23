package br.com.bestcombo.core.casosdeuso;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.UnsatisfiedResolutionException;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUsoSeletor;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.exception.InfraestruturaException;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;
import br.com.bestcombo.ports.service.RequisicaoService;
import br.com.bestcombo.ports.service.SegurancaService;
import br.com.bestcombo.util.AnotacaoUtil;

@Slf4j
@ApplicationScoped
public class ProcessadorCasoDeUsoImpl implements ProcessadorCasoDeUso {

    @Inject
    @Any
    Instance<AbstractCasoDeUso<?, ?>> casosDeUso;

    @Inject
    RequisicaoService requisicaoService;

    @Inject
    SegurancaService segurancaService;

    @Override
    public <REQUISICAO extends RequisicaoDTO<RESPOSTA>, RESPOSTA extends RespostaDTO> RESPOSTA processa(REQUISICAO requisicao) throws NegocioException {
        requisicaoService.validaRequisicao(requisicao);

        segurancaService.verificaAutorizacao(requisicao);

        AbstractCasoDeUso<REQUISICAO, RESPOSTA> casoDeUso = getCasoDeUso(requisicao);

        return casoDeUso.processaCasoDeUso(requisicao);
    }

    @SuppressWarnings("unchecked")
    private <REQUISICAO extends RequisicaoDTO<RESPOSTA>, RESPOSTA extends RespostaDTO> AbstractCasoDeUso<REQUISICAO, RESPOSTA> getCasoDeUso(REQUISICAO requisicao) {
        CasoDeUso anotacao = AnotacaoUtil.getAnotacao(requisicao, CasoDeUso.class);
        try {
            return (AbstractCasoDeUso<REQUISICAO, RESPOSTA>) casosDeUso.select(new CasoDeUsoSeletor(anotacao.value())).get();
        } catch (UnsatisfiedResolutionException e) {
            throw new InfraestruturaException(String.format("Nenhum caso de uso encontrado com a anotacao %s com %s", anotacao.annotationType().getName(), anotacao.value()), e);
        }
    }

}
