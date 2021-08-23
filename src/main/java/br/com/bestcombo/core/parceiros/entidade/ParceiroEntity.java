package br.com.bestcombo.core.parceiros.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_parceiro")
@NamedQueries({
        @NamedQuery(
                name = "buscaPorCpfCnpjOuEmail",
                query = "SELECT p FROM ParceiroEntity p WHERE p.cpfCnpj = :cpfCnpj OR p.email = :email"
        )
})
public class ParceiroEntity {

    @Id
    @Column(name = "codigo_parceiro")
    private UUID codigo;

    @Column(name = "numero_cpf_cnpj_parceiro", unique = true)
    private String cpfCnpj;

    @Column(name = "email_parceiro")
    private String email;

    @Column(name = "nome_parceiro")
    private String nome;

    @Column(name = "data_hora_cadastro_parceiro")
    private LocalDateTime dataHoraCadastro;

    @Column(name = "senha_parceiro")
    private String senha;

    @PrePersist
    public void prePersist() {
        dataHoraCadastro = LocalDateTime.now(ZoneOffset.UTC);
    }

}
