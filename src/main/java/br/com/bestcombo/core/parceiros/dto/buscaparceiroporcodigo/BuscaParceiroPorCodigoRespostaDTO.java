package br.com.bestcombo.core.parceiros.dto.buscaparceiroporcodigo;

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
public class BuscaParceiroPorCodigoRespostaDTO extends RespostaDTO {

    private String id;

    private String cpfCnpj;

    private String nome;

    private String email;

}
