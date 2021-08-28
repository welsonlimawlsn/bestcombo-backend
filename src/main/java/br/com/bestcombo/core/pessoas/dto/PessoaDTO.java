package br.com.bestcombo.core.pessoas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import br.com.bestcombo.core.enderecos.dto.EnderecoDTO;
import br.com.bestcombo.core.enderecos.modelos.Endereco;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private UUID codigo;

    private String nome;

    private String sobrenome;

    private String email;

    private String cpf;

    private List<EnderecoDTO> enderecos;

}
