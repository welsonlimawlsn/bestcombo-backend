package br.com.bestcombo.core.casosdeuso;

import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.exception.InfraestruturaException;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.monitoramento.Monitoramento;
import br.com.bestcombo.util.GenericoUtil;

@Slf4j
public abstract class AbstractCasoDeUso<REQUISICAO extends RequisicaoDTO<RESPOSTA>, RESPOSTA extends RespostaDTO> {

    public static final int TIPO_RESPOSTA = 0;

    @SuppressWarnings("unchecked")
    @Transactional
    public RESPOSTA processaCasoDeUso(REQUISICAO requisicao) throws NegocioException {
        return Monitoramento.monitora(() -> {
            Class<RESPOSTA> tipoResposta = (Class<RESPOSTA>) GenericoUtil.getTipoGenerico(requisicao, TIPO_RESPOSTA);

            RESPOSTA resposta = criaResposta(tipoResposta);

            requisicao.setResposta(resposta);

            processa(requisicao, resposta);

            return resposta;
        }, log, requisicao, "processaCasoDeUso");

    }

    private RESPOSTA criaResposta(Class<RESPOSTA> tipoResposta) {
        try {
            return GenericoUtil.novoObjeto(tipoResposta);
        } catch (InfraestruturaException e) {
            throw new InfraestruturaException(e.getMessage() + ". Talvez seja necessario considerar a criação do construtor padrão do DTO da resposta.", e);
        }
    }

    protected abstract void processa(REQUISICAO req, RESPOSTA res) throws NegocioException;

}
