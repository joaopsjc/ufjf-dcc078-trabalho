/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.extensores;

import model.abstratos.Produto;

/**
 *
 * @author Andre William
 */
public class ProdutoPizza extends Produto{

    public ProdutoPizza(Long id, String nome, String descricao, Double preco) {
        super(id, nome, descricao, preco);
    }

    public ProdutoPizza(Long id, String nome, String descricao, int quantidade, Double preco) {
        super(id, nome, descricao, quantidade, preco);
    }

    public ProdutoPizza(Long id, String nome, String descricao, int quantidade, Double preco, Long id_empresa) {
        super(id, nome, descricao, quantidade, preco, id_empresa);
    }
    public ProdutoPizza()
    {
            
    }
    @Override
    public String getCategoria()
    {
        return "Pizza";
    }
}
