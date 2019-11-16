package model.extensores;

import model.abstratos.Endereco;

public class EnderecoRua extends Endereco{

    public EnderecoRua(){
        super();
    }
    
    @Override
    public String getTipo()
    {
        return "Rua";
    }
}
