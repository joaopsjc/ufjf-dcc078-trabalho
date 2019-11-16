/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.extensores;

import model.abstratos.Endereco;

/**
 *
 * @author Andre William
 */
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
