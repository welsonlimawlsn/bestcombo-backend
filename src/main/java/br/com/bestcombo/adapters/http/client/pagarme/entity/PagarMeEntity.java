package br.com.bestcombo.adapters.http.client.pagarme.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_pagarme_pedido")
public class PagarMeEntity implements Serializable {

    @Id
    @Column(name = "codigo_pedido")
    private UUID codigoPedido;

    @Column(name = "codigo_cartao")
    private String codigoCartao;

    @Column(name = "codigo_transacao")
    private String codigoTransacao;

}
