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
public class ProdutoSanduiche extends Produto{

    public ProdutoSanduiche(Long id, String nome, String descricao, Double preco) {
        super(id, nome, descricao, preco);
    }

    public ProdutoSanduiche(Long id, String nome, String descricao, int quantidade, Double preco) {
        super(id, nome, descricao, quantidade, preco);
    }

    public ProdutoSanduiche(Long id, String nome, String descricao, int quantidade, Double preco, Long id_empresa) {
        super(id, nome, descricao, quantidade, preco, id_empresa);
    }
    public ProdutoSanduiche()
    {
        
    }
    @Override
    public String getCategoria()
    {
        return "Sanduiche";
    }
}
