/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jaaday
 */
public class Comparador {
    private String nomeProduto;
    private Double valorProduto;
    private String lojaVenda;
    private Double rating;
    private Double porcentagem;
    
    public Comparador(){
        rating = 0.0;
        porcentagem = 0.0;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public String getLojaVenda() {
        return lojaVenda;
    }

    public void setLojaVenda(String lojaVenda) {
        this.lojaVenda = lojaVenda;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {
        this.porcentagem = porcentagem;
    }
}
