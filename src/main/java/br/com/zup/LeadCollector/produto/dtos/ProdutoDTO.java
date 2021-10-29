package br.com.zup.LeadCollector.produto.dtos;

public class ProdutoDTO {
    private String nomeProduto;
    private double valor;

    public ProdutoDTO() {
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
