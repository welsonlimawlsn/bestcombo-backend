package br.com.bestcombo.core.pessoas.casodeuso;

import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.enderecos.entity.EnderecoPessoaEntity;
import br.com.bestcombo.core.enderecos.modelos.Endereco;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pessoas.dto.novapessoa.NovaPessoaRequisicaoDTO;
import br.com.bestcombo.core.pessoas.dto.novapessoa.NovaPessoaRespostaDTO;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.ports.dao.EnderecoPessoaDAO;
import br.com.bestcombo.ports.dao.PessoaDAO;
import br.com.bestcombo.ports.service.EnderecoService;
import br.com.bestcombo.ports.service.UsuarioOAuthService;

public abstract class NovaPessoaCasoDeUso<REQUISICAO extends NovaPessoaRequisicaoDTO<RESPOSTA>, RESPOSTA extends NovaPessoaRespostaDTO> extends AbstractCasoDeUso<REQUISICAO, RESPOSTA> {

    @Inject
    EnderecoService enderecoService;

    @Inject
    PessoaDAO pessoaDAO;

    @Inject
    EnderecoPessoaDAO enderecoPessoaDAO;

    @Inject
    UsuarioOAuthService usuarioOAuthService;

    @Override
    protected void processa(REQUISICAO requisicao, RESPOSTA resposta) throws NegocioException {
        Optional<PessoaEntity> pessoa = pessoaDAO.buscaPorCpfOuEmailOuUsuario(requisicao.getCpf(), requisicao.getEmail(), requisicao.getUsuario(), getTipoPessoa());

        if (pessoa.isPresent()) {
            throw new NegocioException(Erro.PESSOA_JA_CADASTRADA);
        }

        Endereco enderecoPorCep = enderecoService.buscaEnderecoPorCep(requisicao.getEndereco().getCep());

        String idPessoa = usuarioOAuthService.criaNovoUsuario(requisicao, getGrupoPessoa());

        PessoaEntity pessoaEntity = mapperParaPessoa(requisicao, idPessoa);
        EnderecoPessoaEntity enderecoPessoaEntity = mapperParaEndereco(requisicao, enderecoPorCep, pessoaEntity);

        pessoaDAO.persiste(pessoaEntity);
        enderecoPessoaDAO.persiste(enderecoPessoaEntity);

        resposta.setCodigo(idPessoa);
    }

    private PessoaEntity mapperParaPessoa(REQUISICAO requisicao, String codigoPessoa) {
        return PessoaEntity.builder()
                .cpf(requisicao.getCpf())
                .codigo(UUID.fromString(codigoPessoa))
                .email(requisicao.getEmail())
                .nome(requisicao.getNome())
                .sobrenome(requisicao.getSobrenome())
                .tipo(getTipoPessoa())
                .usuario(requisicao.getUsuario())
                .build();
    }

    private EnderecoPessoaEntity mapperParaEndereco(REQUISICAO requisicao, Endereco endereco, PessoaEntity pessoaEntity) {
        return EnderecoPessoaEntity.builder()
                .cep(endereco.getCep())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .rua(endereco.getEndereco())
                .complemento(requisicao.getEndereco().getComplemento())
                .numero(requisicao.getEndereco().getNumero())
                .pessoa(pessoaEntity)
                .build();
    }

    protected abstract Integer getTipoPessoa();

    protected abstract String getGrupoPessoa();

}
