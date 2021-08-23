package br.com.bestcombo.core.casosdeuso;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.bestcombo.core.papel.entity.PapelEntity;

@Getter
@Setter
@Entity
@Table(name = "tb_caso_de_uso")
public class CasoDeUsoEntity {

    @Id
    @Column(name = "codigo_caso_de_uso")
    private Integer codigo;

    @Column(name = "descricao_caso_de_uso")
    private String descricao;

    @ManyToMany(mappedBy = "casosDeUso")
    private Set<PapelEntity> papeis;

}
