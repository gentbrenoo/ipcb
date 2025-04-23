package cliente;

import java.time.LocalDate;
import java.util.List;

import comercio.ProdutoInfo;

/**
 * Classe que representa um cupão emitido pela cadeia de lojas HonESta. Este
 * cupão pode ser associado a um ou mais cartões de fidelização. Cada cupão dá
 * direito a um desconto (em cartão) na compra dos produtos que abrange.
 */
public class Cupao {
    private String numero;
    private long desconto;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<ProdutoInfo> produtosAbrangidos;

    public Cupao(String numero, long desconto, String descricao, LocalDate dataInicio, LocalDate dataFim,
            List<ProdutoInfo> produtosAbrangidos) {
        this.numero = numero;
        this.desconto = desconto;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.produtosAbrangidos = produtosAbrangidos;
    }

    
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }


    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }


    public void setProdutosAbrangidos(List<ProdutoInfo> produtosAbrangidos) {
        this.produtosAbrangidos = produtosAbrangidos;
    }


    public String getNumero() {
        return numero;
    }


    public long getDesconto() {
        return desconto;
    }


    public String getDescricao() {
        return descricao;
    }


    public LocalDate getDataInicio() {
        return dataInicio;
    }


    public LocalDate getDataFim() {
        return dataFim;
    }


    public List<ProdutoInfo> getProdutosAbrangidos() {
        return produtosAbrangidos;
    }


    /**
     * Indica se o cupão está válido no dia de hoje, isto é, se o dia de hoje está
     * dentro do prazo de utilização.
     * 
     * @return true se está dentro do prazo de utilização
     */
    public boolean estaValido() {
        LocalDate hoje = LocalDate.now();
        if (hoje.isAfter(dataInicio) && hoje.isBefore(dataFim)) {
            return true;
        } else if (hoje.isEqual(dataInicio) || hoje.isEqual(dataFim)) {
            return true;
        }
        
        return false;
    }

    /**
     * Indica se o cupão está válido num dado dia, isto é, se esse dia está dentro
     * do prazo de utilização.
     * 
     * @param data dia em que se pretende verificar se o cupão está válido
     * @return true se a data está dentro do prazo de utilização
     */
    public boolean estaValido(LocalDate data) {
        if (data.isAfter(dataInicio) && data.isBefore(dataFim)) {
            return true;
        } else if (data.isEqual(dataInicio) || data.isEqual(dataFim)) {
            return true;
        }
        return false;
    }

    /**
     * Aplica o cupão de desconto a um cartão e a uma venda. Se a venda contiver
     * algum dos produtos abrangidos pelo cupão, este é dado como tendo sido
     * aplicado. Se for aplicado deve ser removido do cartão.
     * 
     * @param c o cartão a ser usado na venda
     * @param v a venda a ser processada
     * @return true se o cupão foi aplicado na venda
     */
    public boolean aplicar(Cartao c, Venda v) {
        if(estaValido()) {
            for (ProdutoVendido p : v.getProdutosVendidos()) {
                if (abrange(p)) {
                    aplicar(c, p);
                    return true;
                }
            }
        }
        if(estaValido(v.getDataVenda())) {
            for (ProdutoVendido p : v.getProdutosVendidos()) {
                if (abrange(p)) {
                    aplicar(c, p);
                    return true;
                }
            }
        }
        
        // TODO implementar este método
        return false;
    }

    /**
     * Indica se o produto indicado é abrangido pelo cupão. O produto é abrangido
     * pelo cupão se fizer parte da lista dos produtos e não tiver já sido aplicado
     * ao produto um desconto maior do que o dado pelo cupão.
     * 
     * @param p o produto a testar
     * @return true, se o produto é abrangido pela cupão
     */
    public boolean abrange(ProdutoVendido p) {
        if(produtosAbrangidos.contains(p.getProdutoInfo())) {
            if (p.getDesconto() < desconto) {
                return true;
            }
        }
        if(produtosAbrangidos.contains(p.getProdutoInfo()) && p.getDesconto() == desconto) {
            return true;
        }

        // TODO implementar este método
        return false;
    }

    /**
     * Método auxiliar para aplicar o cupão a um produto.
     * 
     * @param c o cartão onde acumular o saldo
     * @param p o produto a ser usado
     */
    private void aplicar(Cartao c, ProdutoVendido p) {
        long valorDesconto = p.getPrecoVenda() * desconto / 100;

        p.setDesconto(desconto);

        c.acumularSaldo(valorDesconto);
        // TODO implementar este método
    }
}
