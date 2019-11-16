package model.extensores;

import model.abstratos.Endereco;

public class EnderecoFazenda extends Endereco{

    public EnderecoFazenda(){
        super();
    }
    
    @Override
    public String getTipo()
    {
        return "Fazenda";
    }
}
