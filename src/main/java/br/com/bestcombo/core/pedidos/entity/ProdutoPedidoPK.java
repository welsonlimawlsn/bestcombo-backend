package br.com.bestcombo.core.pedidos.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

import br.com.bestcombo.core.produtos.entity.ProdutoEntity;

@Getter
@Setter
public class ProdutoPedidoPK implements Serializable {

    private ProdutoEntity produto;

    private PedidoEntity pedido;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProdutoPedidoPK that = (ProdutoPedidoPK) o;
        return produto != null && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
