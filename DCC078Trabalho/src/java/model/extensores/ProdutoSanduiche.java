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

    public ProdutoSanduiche()
    {
        super();
    }
    @Override
    public String getCategoria()
    {
        return "Sanduiche";
    }
}
