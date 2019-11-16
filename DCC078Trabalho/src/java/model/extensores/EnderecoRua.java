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
    public String toString()
    {
        return getTipo() + " " + super.getLogradouro() + ", " + super.getNumero() + " - "
                + super.getBairro() + ", " + super.getCidade() +  " - " + super.getEstado()
                + ", " + super.getCep();
    }
}
