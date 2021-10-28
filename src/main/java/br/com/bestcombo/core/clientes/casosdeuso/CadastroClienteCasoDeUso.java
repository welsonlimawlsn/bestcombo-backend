package br.com.bestcombo.core.clientes.casosdeuso;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.clientes.dto.cadastrocliente.CadastroClienteRequisicaoDTO;
import br.com.bestcombo.core.clientes.dto.cadastrocliente.CadastroClienteRespostaDTO;
import br.com.bestcombo.core.pessoas.casodeuso.NovaPessoaCasoDeUso;
import br.com.bestcombo.core.pessoas.enums.TipoPessoa;

@CasoDeUso(CasosDeUso.CADASTRO_CLIENTE)
@ApplicationScoped
public class CadastroClienteCasoDeUso extends NovaPessoaCasoDeUso<CadastroClienteRequisicaoDTO, CadastroClienteRespostaDTO> {

    @Override
    protected Integer getTipoPessoa() {
        return TipoPessoa.CLIENTE.getCodigo();
    }

    @Override
    protected String getGrupoPessoa() {
        return TipoPessoa.CLIENTE.getGrupo();
    }

}
