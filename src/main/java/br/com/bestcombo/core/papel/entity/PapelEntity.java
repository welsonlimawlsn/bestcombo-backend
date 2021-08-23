package br.com.bestcombo.core.papel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.bestcombo.core.casosdeuso.CasoDeUsoEntity;

@Getter
@Setter
@Entity
@Table(name = "tb_papel")
@NamedQueries({
        @NamedQuery(
                name = "buscaPorCasoDeUso",
                query = "SELECT p.nome FROM PapelEntity p join p.casosDeUso cdu where cdu = :casoDeUso"
        )
})
public class PapelEntity {

    @Id
    @Column(name = "codigo_papel")
    private Integer codigo;

    @Column(name = "nome_papel")
    private String nome;

    @Column(name = "descricao_papel")
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "tb_caso_de_uso_papel",
            joinColumns = @JoinColumn(name = "codigo_papel"),
            inverseJoinColumns = @JoinColumn(name = "codigo_caso_de_uso")
    )
    private Set<CasoDeUsoEntity> casosDeUso;

}
