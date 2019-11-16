package model.extensores;

import model.abstratos.Endereco;

public class EnderecoAvenida extends Endereco{

    public EnderecoAvenida(){
        super();
    }
    
    @Override
    public String getTipo()
    {
        return "Avenida";
    }
}
