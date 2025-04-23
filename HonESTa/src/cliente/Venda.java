package cliente;

import java.time.LocalDate;
import java.util.List;


/**
 * Classe responsável por armazenar os produtos vendidos numa venda. Tem a data
 * em que foi realizada, os produtos e os cupões aplicados.
 */
public class Venda {
    private LocalDate dataVenda;
    private List<ProdutoVendido> produtosVendidos;
    private List<Cupao> cupoesAplicados;

    public Venda(LocalDate dataVenda, List<ProdutoVendido> produtosVendidos, List<Cupao> cupoesAplicados) {
        this.dataVenda = dataVenda;
        this.produtosVendidos = produtosVendidos;
        this.cupoesAplicados = cupoesAplicados;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public List<ProdutoVendido> getProdutosVendidos() {
        return produtosVendidos;
    }

    public List<Cupao> getCupoesAplicados() {
        return cupoesAplicados;
    }

    // public static void main(String[] args) {
    //     ProdutoInfo produto1 = new ProdutoInfo("1234567890123", "Marca A", "Modelo A", 1000);
    //     ProdutoInfo produto2 = new ProdutoInfo("1234567890124", "Marca B", "Modelo B", 2000);
    //     List<ProdutoVendido> produtosVendidos = List.of(
    //             new ProdutoVendido(produto1, 1000, 0),
    //             new ProdutoVendido(produto2, 2000, 0)
    //     );
    //     List<Cupao> cupoesAplicados = List.of(
    //             new Cupao("CUP123", 100, "Desconto de 10%", LocalDate.now(), LocalDate.now().plusDays(30), List.of(produto1))
    //     );
    //     Venda venda = new Venda(LocalDate.now(), produtosVendidos, cupoesAplicados);
    //     System.out.println("Data da venda: " + venda.getDataVenda());
    //     System.out.println("Produtos vendidos: ");
    //     for (ProdutoVendido produto : venda.getProdutosVendidos()) {
    //         System.out.println(" - " + produto.getProdutoInfo().getModelo() + " - Preço: " + produto.getPrecoVenda()
    //                 + " - Desconto: " + produto.getDesconto());
    //     }
    //     System.out.println("Cupões aplicados: ");
    //     for (Cupao cupao : venda.getCupoesAplicados()) {
    //         System.out.println(" - " + cupao.getNumero() + " - Desconto: " + cupao.getDesconto() + " - Validade: "
    //                 + cupao.getDataInicio() + " a " + cupao.getDataFim());
    //     }

    // }
}
