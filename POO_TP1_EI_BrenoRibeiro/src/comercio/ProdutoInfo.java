package comercio;

import util.Validator;

/**
 * Armazena a informação de um produto, como o código de barras, a marca, o
 * modelo e o preço atual.
 */
public class ProdutoInfo {
    private String codigoBarras;
    private String marca;
    private String modelo;
    public long precoAtual;

    public ProdutoInfo(String codigoBarras, String marca, String modelo, long precoAtual) {
        this.codigoBarras = Validator.requireNonBlankTrimmed(codigoBarras);
        this.marca = Validator.requireNonBlankTrimmed(marca);
        this.modelo = Validator.requireNonBlankTrimmed(modelo);
        this.precoAtual = Validator.requirePositiveOrZero(precoAtual);
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
        return Validator.requirePositiveOrZero(precoAtual);
    }
    
}
