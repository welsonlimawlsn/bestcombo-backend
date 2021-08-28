package br.com.bestcombo.core.pessoas.casodeuso;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.enderecos.dto.EnderecoDTO;
import br.com.bestcombo.core.enderecos.entity.EnderecoPessoaEntity;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pessoas.dto.PessoaDTO;
import br.com.bestcombo.core.pessoas.dto.listapessoas.ListaPessoasRequisicaoDTO;
import br.com.bestcombo.core.pessoas.dto.listapessoas.ListaPessoasRespostaDTO;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;

public abstract class ListaPessoasCasoDeUso<REQUISICAO extends ListaPessoasRequisicaoDTO<RESPOSTA>, RESPOSTA extends ListaPessoasRespostaDTO> extends AbstractCasoDeUso<REQUISICAO, RESPOSTA> {

    @Override
    protected void processa(REQUISICAO requisicao, RESPOSTA resposta) throws NegocioException {
        List<PessoaDTO> dtos = getPessoas().stream()
                .map(this::mapperParaPessoaDTO)
                .collect(Collectors.toList());

        resposta.setPessoas(dtos);
    }

    private PessoaDTO mapperParaPessoaDTO(PessoaEntity pessoaEntity) {
        return PessoaDTO.builder()
                .codigo(pessoaEntity.getCodigo())
                .cpf(pessoaEntity.getCpf())
                .email(pessoaEntity.getEmail())
                .nome(pessoaEntity.getNome())
                .sobrenome(pessoaEntity.getSobrenome())
                .enderecos(getEnderecos(pessoaEntity))
                .build();
    }

    private List<EnderecoDTO> getEnderecos(PessoaEntity pessoaEntity) {
        return pessoaEntity.getEnderecos().stream()
                .map(this::mapperParaEnderecoDTO)
                .collect(Collectors.toList());
    }

    private EnderecoDTO mapperParaEnderecoDTO(EnderecoPessoaEntity enderecoPessoaEntity) {
        return EnderecoDTO.builder()
                .cep(enderecoPessoaEntity.getCep())
                .bairro(enderecoPessoaEntity.getBairro())
                .cidade(enderecoPessoaEntity.getCidade())
                .complemento(enderecoPessoaEntity.getComplemento())
                .estado(enderecoPessoaEntity.getEstado())
                .numero(enderecoPessoaEntity.getNumero())
                .rua(enderecoPessoaEntity.getRua())
                .build();
    }

    protected abstract Set<PessoaEntity> getPessoas();

}
