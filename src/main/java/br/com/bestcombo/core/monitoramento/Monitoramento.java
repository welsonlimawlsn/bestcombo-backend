package br.com.bestcombo.core.monitoramento;

import org.slf4j.Logger;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.util.AnotacaoUtil;

public class Monitoramento {

    public static void monitoraVoid(MonitoravelVoid monitoravel, Logger logger, RequisicaoDTO<?> requisicaoDTO, String metodo) throws NegocioException {
        long inicio = System.currentTimeMillis();
        CasoDeUso anotacao = AnotacaoUtil.getAnotacao(requisicaoDTO, CasoDeUso.class);
        logInicio(logger, requisicaoDTO, metodo, anotacao);
        monitoravel.monitora();
        long fim = System.currentTimeMillis();
        logFim(logger, requisicaoDTO, metodo, inicio, fim, anotacao);
    }

    public static <T> T monitora(Monitoravel<T> monitoravel, Logger logger, RequisicaoDTO<?> requisicaoDTO, String metodo) throws NegocioException {
        long inicio = System.currentTimeMillis();
        CasoDeUso anotacao = AnotacaoUtil.getAnotacao(requisicaoDTO, CasoDeUso.class);
        logInicio(logger, requisicaoDTO, metodo, anotacao);
        T result = monitoravel.monitora();
        long fim = System.currentTimeMillis();
        logFim(logger, requisicaoDTO, metodo, inicio, fim, anotacao);
        return result;
    }

    private static void logFim(Logger logger, RequisicaoDTO<?> requisicaoDTO, String metodo, long inicio, long fim, CasoDeUso anotacao) {
        logger.info("MONITORAMENTO | FIM | Tempo: {}ms | Caso de Uso: {} | Requisicao: {} | Método: {}", fim - inicio, anotacao.value().toString(), requisicaoDTO.getId(), metodo);
    }

    private static void logInicio(Logger logger, RequisicaoDTO<?> requisicaoDTO, String metodo, CasoDeUso anotacao) {
        logger.info("MONITORAMENTO | INICIO | Requisicao: {} | Método: {} | Caso de Uso: {}", requisicaoDTO.getId(), metodo, anotacao.value().toString());
    }

    public interface MonitoravelVoid {

        void monitora() throws NegocioException;

    }

    public interface Monitoravel<T> {

        T monitora() throws NegocioException;

    }

}
