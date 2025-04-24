package cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Validator;

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
    private ArrayList<ProdutoInfo> produtosAbrangidos;
    private LocalDate hoje= LocalDate.now();

    public Cupao(String numero, long desconto, String descricao, LocalDate dataInicio, LocalDate dataFim,
            ArrayList<ProdutoInfo> produtosAbrangidos) {
        this.numero = Validator.requireNonBlankTrimmed(numero);
        this.desconto = Validator.requireInsideRange(desconto, 0, 100);
        this.descricao = Validator.requireNonBlankTrimmed(descricao);
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

    public void setProdutosAbrangidos(ArrayList<ProdutoInfo> produtosAbrangidos) {
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
        return Collections.unmodifiableList(produtosAbrangidos);
    }

    /**
     * Indica se o cupão está válido no dia de hoje, isto é, se o dia de hoje está
     * dentro do prazo de utilização.
     * 
     * @return true se está dentro do prazo de utilização
     */
    public boolean estaValido() {
        return hoje.isEqual(dataInicio) || hoje.isEqual(dataFim) || hoje.isAfter(dataInicio) && hoje.isBefore(dataFim);
    }

    /**
     * Indica se o cupão está válido num dado dia, isto é, se esse dia está dentro
     * do prazo de utilização.
     * 
     * @param data dia em que se pretende verificar se o cupão está válido
     * @return true se a data está dentro do prazo de utilização
     */
    public boolean estaValido(LocalDate data) {
        return data.isEqual(dataInicio) || data.isEqual(dataFim) || data.isAfter(dataInicio) && data.isBefore(dataFim);
    }

    public boolean futurosValidos(){
        return hoje.isBefore(dataInicio);
    }
    public boolean futurosValidos(LocalDate data){
        return data.isBefore(dataInicio);
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
        if (!estaValido(v.getDataVenda()))
            return false;

        boolean foiUsado=false;
        List<ProdutoVendido>prod= v.getProdutosVendidos();

        for(ProdutoVendido p : prod){
            if(abrange(p)){
                aplicar(c, p);
                foiUsado=true;
            }
        }
        if(foiUsado && !v.getCupoesAplicados().contains(this)){
            v.adicionarCupao(this);
        }
        if(foiUsado)
            c.removerCupao(this);
        // TODO FEITO implementar este método
        return foiUsado;
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
        // TODO FEITO implementar este método
        return produtosAbrangidos.contains(p.getProdutoInfo()) && p.getDesconto() < desconto;
    }

    /**
     * Método auxiliar para aplicar o cupão a um produto.
     * 
     * @param c o cartão onde acumular o saldo
     * @param p o produto a ser usado
     */
    private void aplicar(Cartao c, ProdutoVendido p) {
        if (abrange(p)) {
            c.acumularSaldo(desconto);
            p.setDesconto(desconto);
        }

        // TODO FEITO implementar este método
    }

}
