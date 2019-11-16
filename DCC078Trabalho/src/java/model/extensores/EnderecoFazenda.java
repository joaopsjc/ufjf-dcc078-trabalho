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
    public String toString()
    {
        return getTipo() + " " + super.getLogradouro() + ", " + super.getNumero() + " - "
                + super.getBairro() + ", " + super.getCidade() +  " - " + super.getEstado()
                + ", " + super.getCep();
    }
}
