package br.com.bestcombo.core.loja.casodeuso;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.categoria.entity.CategoriaEntity;
import br.com.bestcombo.core.enderecos.entity.EnderecoLojaEntity;
import br.com.bestcombo.core.enderecos.modelos.Endereco;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.dto.cadastroloja.CadastroLojaRequisicaoDTO;
import br.com.bestcombo.core.loja.dto.cadastroloja.CadastroLojaRespostaDTO;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.ports.dao.LojaDAO;
import br.com.bestcombo.ports.service.EnderecoService;
import br.com.bestcombo.ports.service.SegurancaService;

@CasoDeUso(CasosDeUso.CADASTRA_LOJA)
@ApplicationScoped
public class CadastraLojaCasoDeUso extends AbstractCasoDeUso<CadastroLojaRequisicaoDTO, CadastroLojaRespostaDTO> {

    @Inject
    LojaDAO lojaDAO;

    @Inject
    SegurancaService segurancaService;

    @Inject
    EnderecoService enderecoService;

    @Override
    protected void processa(CadastroLojaRequisicaoDTO requisicao, CadastroLojaRespostaDTO resposta) throws NegocioException {
        PessoaEntity usuario = segurancaService.getUsuarioLogado();

        if (!usuario.isParceiro()) {
            throw new NegocioException(Erro.TIPO_USUARIO_INVALIDO_CADASTRO_LOJA);
        }

        if (lojaDAO.buscaLojaPorCNPJ(requisicao.getCnpj()).isPresent()) {
            throw new NegocioException(Erro.LOJA_JA_CADASTRADA);
        }

        LojaEntity loja = mapperParaLojaEntity(requisicao, usuario);

        lojaDAO.persiste(loja);

        resposta.setCodigo(loja.getCodigo().toString());
    }

    private LojaEntity mapperParaLojaEntity(CadastroLojaRequisicaoDTO requisicao, PessoaEntity parceiro) throws NegocioException {
        return LojaEntity.builder()
                .cnpj(requisicao.getCnpj())
                .endereco(mapperParaEndereco(requisicao))
                .nome(requisicao.getNome())
                .parceiro(parceiro)
                .imagem(requisicao.getImagem())
                .descricao(requisicao.getDescricao())
                .categorias(mapperParaCategorias(requisicao.getCategorias()))
                .build();
    }

    private Set<CategoriaEntity> mapperParaCategorias(List<Integer> categorias) {
        return categorias.stream()
                .map(codigo -> CategoriaEntity.builder().codigo(codigo).build())
                .collect(Collectors.toSet());
    }

    private EnderecoLojaEntity mapperParaEndereco(CadastroLojaRequisicaoDTO requisicao) throws NegocioException {
        Endereco endereco = enderecoService.buscaEnderecoPorCep(requisicao.getEndereco().getCep());

        return EnderecoLojaEntity.builder()
                .cep(endereco.getCep())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .complemento(requisicao.getEndereco().getComplemento())
                .estado(endereco.getEstado())
                .numero(requisicao.getEndereco().getNumero())
                .rua(endereco.getEndereco())
                .build();
    }

}
