package br.com.bestcombo.core.enderecos.dto.buscaendereco;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaEnderecoPorCEPRespostaDTO extends RespostaDTO {

    private String cep;

    private String rua;

    private String bairro;

    private String cidade;

    private String estado;

}
