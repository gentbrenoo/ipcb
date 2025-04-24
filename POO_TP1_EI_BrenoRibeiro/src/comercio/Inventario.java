package comercio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cliente.Cartao;
import cliente.Cupao;


/**
 * Classe que representa o inventário da empresa, desde os produtos, até aos
 * cartões e cupões. Responsável por armazenar todos esses dados, bem como
 * fornecer métodos de pesquisa que usem os códigos para descobrir os produtos.
 */
public class Inventario {
    
    private ArrayList<ProdutoInfo> produtos= new ArrayList<>();
    private ArrayList<Cartao> cartoes = new ArrayList<>();
    private ArrayList<Cupao> cupoes= new ArrayList<>();

    public Inventario(ArrayList<ProdutoInfo> produtos, ArrayList<Cartao> cartoes, ArrayList<Cupao> cupoes) {
        this.produtos = produtos;
        this.cartoes = cartoes;
        this.cupoes = cupoes;
    }

    public Inventario (){

    }


    /**
     * Retorna qual o produto que tem um dado código de barras
     * 
     * @param codigoBarras o código de barras do produto que se pretende
     * @return o produto com o código de barras, ou null caso não exista
     */
    public ProdutoInfo getProduto(String codigoBarras) {
        for (ProdutoInfo produto : produtos) {
            if (produto.getCodigoBarras().equals(codigoBarras)) {
                return produto;
            }
        }
        return null;
    }
    //metodo para retornar lista de produtos do inventario
    public List<ProdutoInfo> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    public List<Cartao> getCartoes() {
        return Collections.unmodifiableList(cartoes);
    }


    public List<Cupao> getCupoes() {
        return Collections.unmodifiableList(cupoes);
    }

    /**
     * Retorna o cartão com um dado número
     * 
     * @param numero o número do cartão a procurar
     * @return o cartão com o número pedido, ou null caso não exista
     */
    public Cartao getCartao(String numero) {
        for(Cartao crt : cartoes){
            if (crt.getId().equals(numero))
                return crt;
        }
        return null;
    }

    /**
     * Retorna o cupão com um dado número
     * 
     * @param numero o número do cupão a procurar
     * @return o cupão com o número pedido, ou null caso não exista
     */
    public Cupao getCupao(String numero) {
        for(Cupao cupao : cupoes){
            if (cupao.getNumero().equals(numero))
                return cupao;
        }
        return null;
    }

    public void adicionarProdutos(List<ProdutoInfo> p){
        produtos.addAll(p);
    }
    public void adicionarCupoes(List<Cupao> c){
        cupoes.addAll(c);
    }
    public void adicionarCartoes(List<Cartao> c){
        cartoes.addAll(c);
    }
    public void associarCupao(String numeroCartao, String numeroCupao){
        Cartao cartao = getCartao(numeroCartao);
        Cupao cupao = getCupao(numeroCupao);

        if(cartao!=null && cupao != null){
            cartao.addCupao(cupao);
        }
    }
}
