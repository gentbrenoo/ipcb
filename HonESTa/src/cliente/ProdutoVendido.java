package cliente;

import comercio.ProdutoInfo;

/**
 * Representa um produto vendido numa compra. Atenção, esta classe não contém a
 * informação sobre o produto propriamente dito (para isso usa um ProdutoInfo),
 * mas sim sobre as condições de venda desse produto, nomeadamente o preço usado
 * na altura e o desconto.
 */
public class ProdutoVendido {
    private ProdutoInfo produtoInfo;
    private long precoVenda;
    private long desconto;

    public ProdutoVendido(ProdutoInfo produtoInfo, long precoVenda, long desconto) {
        this.produtoInfo = produtoInfo;
        this.precoVenda = precoVenda;
        this.desconto = desconto;
    }

    public ProdutoInfo getProdutoInfo() {
        return produtoInfo;
    }

    public long getPrecoVenda() {
        return precoVenda;
    }

    public long getDesconto() {
        return desconto;
    }

    public void setDesconto(long desconto) {
        this.desconto = desconto;
    }

    
}
