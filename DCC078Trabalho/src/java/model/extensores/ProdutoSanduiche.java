package model.extensores;

import model.abstratos.Produto;

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
