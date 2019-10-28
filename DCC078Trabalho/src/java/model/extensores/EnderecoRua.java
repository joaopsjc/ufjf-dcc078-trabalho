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
public class EnderecoRua extends Endereco{

    public EnderecoRua(int numero, long cep, long id, String lagradouro, String complemento, String bairro, String estado, String cidade) {
        super(numero, cep, id, lagradouro, complemento, bairro, estado, cidade);
    }
    
    @Override
    public String getTipo()
    {
        return "Rua";
    }
    
}
