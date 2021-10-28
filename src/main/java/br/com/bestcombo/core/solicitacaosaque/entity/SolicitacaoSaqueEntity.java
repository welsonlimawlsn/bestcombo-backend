package br.com.bestcombo.core.solicitacaosaque.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.solicitacaosaque.enums.SituacaoSolicitacaoSaque;
import br.com.bestcombo.core.solicitacaosaque.enums.SituacaoSolicitacaoSaqueConverter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_solicitacao_saque")
public class SolicitacaoSaqueEntity {

    @Id
    @Column(name = "codigo_solicitacao_saque")
    private UUID codigo;

    @Convert(converter = SituacaoSolicitacaoSaqueConverter.class)
    @Column(name = "codigo_situacao_solicitacao_saque")
    private SituacaoSolicitacaoSaque situacaoSolicitacaoSaque;

    @Column(name = "data_solicitacao_saque")
    private ZonedDateTime dataCadastro;

    @Column(name = "data_ultima_atualizacao_solicitacao_saque")
    private ZonedDateTime dataUltimaAtualizacao;

    @ManyToOne
    @JoinColumn(name = "codigo_loja_solicitacao_saque")
    private LojaEntity loja;

    @Column(name = "chave_pix_solicitacao_saque")
    private String chavePix;

    @Column(name = "valor_solicitacao_saque")
    private BigDecimal valor;

    @PrePersist
    public void prePersist() {
        this.codigo = UUID.randomUUID();
        this.situacaoSolicitacaoSaque = SituacaoSolicitacaoSaque.SOLICITADO;
        this.dataCadastro = ZonedDateTime.now(ZoneOffset.UTC);
        this.dataUltimaAtualizacao = ZonedDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    public void preUpdate() {
        this.dataUltimaAtualizacao = ZonedDateTime.now(ZoneOffset.UTC);
    }

    public void setSituacaoSolicitacaoSaque(SituacaoSolicitacaoSaque situacaoSolicitacaoSaque) throws NegocioException {
        boolean isSituacaoInvalidaParaEmAndamento = this.situacaoSolicitacaoSaque.equals(SituacaoSolicitacaoSaque.SOLICITADO) && !situacaoSolicitacaoSaque.equals(SituacaoSolicitacaoSaque.EM_ANDAMENTO);
        boolean isSituacaoInvalidaParaConcluido = this.situacaoSolicitacaoSaque.equals(SituacaoSolicitacaoSaque.EM_ANDAMENTO) && !situacaoSolicitacaoSaque.equals(SituacaoSolicitacaoSaque.CONCLUIDO);

        if (isSituacaoInvalidaParaEmAndamento || isSituacaoInvalidaParaConcluido || this.situacaoSolicitacaoSaque.equals(SituacaoSolicitacaoSaque.CONCLUIDO)){
            throw new NegocioException(Erro.SITUACAO_INVALIDA);
        }

        this.situacaoSolicitacaoSaque = situacaoSolicitacaoSaque;
    }

}
