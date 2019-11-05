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
public class EnderecoAvenida extends Endereco{

    public EnderecoAvenida(String numero, String cep, long id, String lagradouro, String complemento, String bairro, String estado, String cidade) {
        super(numero, cep, id, lagradouro, complemento, bairro, estado, cidade);
    }
    public EnderecoAvenida(){
        super();
    }
    
    @Override
    public String getTipo()
    {
        return "Avenida";
    }
}
