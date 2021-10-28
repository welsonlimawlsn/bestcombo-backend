package br.com.bestcombo.core.parceiros.casosdeuso;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.parceiros.dto.novoparceiro.NovoParceiroRequisicaoDTO;
import br.com.bestcombo.core.parceiros.dto.novoparceiro.NovoParceiroRespostaDTO;
import br.com.bestcombo.core.pessoas.casodeuso.NovaPessoaCasoDeUso;
import br.com.bestcombo.core.pessoas.enums.TipoPessoa;

@ApplicationScoped
@Slf4j
@CasoDeUso(CasosDeUso.NOVO_PARCEIRO)
public class NovoParceiroCasoDeUso extends NovaPessoaCasoDeUso<NovoParceiroRequisicaoDTO, NovoParceiroRespostaDTO> {

    public static final int PESSOA_TIPO_PARCEIRO = 1;

    @Override
    protected Integer getTipoPessoa() {
        return TipoPessoa.PARCEIRO.getCodigo();
    }

    @Override
    protected String getGrupoPessoa() {
        return TipoPessoa.PARCEIRO.getGrupo();
    }

}

