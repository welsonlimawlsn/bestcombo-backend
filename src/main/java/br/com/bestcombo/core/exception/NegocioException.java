package br.com.bestcombo.core.exception;

import java.util.Collections;
import java.util.List;

public class NegocioException extends Exception {

    private final Erro erro;

    private List<String> mensagens;

    public NegocioException(Erro erro) {
        super(getMensagem(erro));
        this.erro = erro;
    }

    public NegocioException(Erro erro, String mensagem) {
        super(getMensagem(erro));
        this.erro = erro;
        this.mensagens = Collections.singletonList(mensagem);
    }

    public NegocioException(Erro erro, List<String> mensagens) {
        super(getMensagem(erro));
        this.erro = erro;
        this.mensagens = mensagens;
    }

    public NegocioException(Erro erro, Throwable throwable) {
        super(getMensagem(erro), throwable);
        this.erro = erro;
    }

    public NegocioException(Erro erro, String mensagem, Throwable throwable) {
        super(getMensagem(erro), throwable);
        this.erro = erro;
        this.mensagens = Collections.singletonList(mensagem);
    }

    private static String getMensagem(Erro erro) {
        return String.format("Código: %s - Mensagem: %s", erro.getCodigo(), erro.getDescricao());
    }

    public Erro getErro() {
        return erro;
    }

    public List<String> getMensagem() {
        if (mensagens == null || mensagens.isEmpty()) {
            return Collections.singletonList(erro.getDescricao());
        }

        return mensagens;
    }

}
