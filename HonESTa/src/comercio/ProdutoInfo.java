package comercio;

/**
 * Armazena a informação de um produto, como o código de barras, a marca, o
 * modelo e o preço atual.
 */
public class ProdutoInfo {
    private String codigoBarras;
    private String marca;
    private String modelo;
    private long precoAtual;

    public ProdutoInfo(String codigoBarras, String marca, String modelo, long precoAtual) {
        this.codigoBarras = codigoBarras;
        this.marca = marca;
        this.modelo = modelo;
        this.precoAtual = setPrecoAtual(precoAtual);
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public long getPrecoAtual() {
        return precoAtual;
    }
    
    public long setPrecoAtual(long precoAtual) {
        this.precoAtual = precoAtual;
        return this.precoAtual;
    }

    public String toString() {
        return "ProdutoInfo [codigoBarras=" + codigoBarras + ", marca=" + marca + ", modelo=" + modelo
                + ", precoAtual=" + precoAtual + "]";
    }
    
}
