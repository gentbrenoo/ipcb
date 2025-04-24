package cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsável por armazenar os produtos vendidos numa venda. Tem a data
 * em que foi realizada, os produtos e os cupões aplicados.
 */
public class Venda {

    private LocalDate dataVenda;
    private ArrayList<ProdutoVendido> produtosVendidos = new ArrayList<ProdutoVendido>();
    private ArrayList<Cupao> cupoesAplicados = new ArrayList<Cupao>();

    public Venda(LocalDate dataVenda, ArrayList<ProdutoVendido> produtosVendidos, ArrayList<Cupao> cupoesAplicados) {
        this.dataVenda = dataVenda;
        this.produtosVendidos = produtosVendidos;
        this.cupoesAplicados = cupoesAplicados;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public List<ProdutoVendido> getProdutosVendidos() {

        return Collections.unmodifiableList(produtosVendidos);
    }

    public List<Cupao> getCupoesAplicados() {
        return Collections.unmodifiableList(cupoesAplicados);
    }

    public void adicionarCupao(Cupao cupao) {
        if (!cupoesAplicados.contains(cupao)) {
            cupoesAplicados.add(cupao);
        }
    }

    public void adicionarProduto(ProdutoVendido produto) {
        produtosVendidos.add(produto);
    }

    public long getTotal() {
        long total = 0;
        for (ProdutoVendido produto : produtosVendidos) {
            total += produto.getPrecoVenda();
        }

        return total;
    }

}
