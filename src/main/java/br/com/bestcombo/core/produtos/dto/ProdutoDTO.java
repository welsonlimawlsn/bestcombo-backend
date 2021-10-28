package br.com.bestcombo.core.produtos.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import br.com.bestcombo.core.categoria.dto.CategoriaDTO;

@Getter
@Setter
@Builder
public class ProdutoDTO {

    private UUID codigo;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Integer quantidadePessoas;

    private String imagem;

    private Collection<CategoriaDTO> categorias;

    private UUID codigoLoja;

    private Integer quantidade;

}
