package br.com.bestcombo.core.pessoas.casodeuso;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.enderecos.dto.EnderecoDTO;
import br.com.bestcombo.core.enderecos.mapper.EnderecoPessoaMapper;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pessoas.dto.PessoaDTO;
import br.com.bestcombo.core.pessoas.dto.listapessoas.ListaPessoasRequisicaoDTO;
import br.com.bestcombo.core.pessoas.dto.listapessoas.ListaPessoasRespostaDTO;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.core.pessoas.mapper.PessoaMapper;

public abstract class ListaPessoasCasoDeUso<REQUISICAO extends ListaPessoasRequisicaoDTO<RESPOSTA>, RESPOSTA extends ListaPessoasRespostaDTO> extends AbstractCasoDeUso<REQUISICAO, RESPOSTA> {

    @Override
    protected void processa(REQUISICAO requisicao, RESPOSTA resposta) throws NegocioException {
        List<PessoaDTO> dtos = getPessoas().stream()
                .map(this::mapperParaPessoaDTO)
                .collect(Collectors.toList());

        resposta.setPessoas(dtos);
    }

    private PessoaDTO mapperParaPessoaDTO(PessoaEntity pessoaEntity) {
        PessoaDTO pessoaDTO = PessoaMapper.mapperParaDTO(pessoaEntity);
        pessoaDTO.setEnderecos(getEnderecos(pessoaEntity));
        return pessoaDTO;
    }

    private List<EnderecoDTO> getEnderecos(PessoaEntity pessoaEntity) {
        return pessoaEntity.getEnderecos().stream()
                .map(EnderecoPessoaMapper::mapperParaDTO)
                .collect(Collectors.toList());
    }

    protected abstract Set<PessoaEntity> getPessoas();

}
