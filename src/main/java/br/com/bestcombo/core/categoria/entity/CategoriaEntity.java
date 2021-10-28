package br.com.bestcombo.core.categoria.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_categoria")
@Entity
public class CategoriaEntity {

    @Id
    @Column(name = "codigo_categoria")
    private Integer codigo;

    @Column(name = "nome_categoria")
    private String nome;

    @Column(name = "codigo_tipo_servico")
    private Integer tipoServico;

}
