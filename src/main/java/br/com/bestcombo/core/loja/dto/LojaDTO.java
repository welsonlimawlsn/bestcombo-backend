package br.com.bestcombo.core.loja.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import br.com.bestcombo.core.produtos.dto.ProdutoDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LojaDTO {

    private UUID codigo;

    private String cnpj;

    private String nome;

    private String descricao;

    private String imagem;

    private List<ProdutoDTO> produtos;

}
