package model.extensores;

import model.abstratos.Produto;

public class ProdutoPizza extends Produto{

    public ProdutoPizza()
    {
        super();
    }
    @Override
    public String getCategoria()
    {
        return "Pizza";
    }
}
