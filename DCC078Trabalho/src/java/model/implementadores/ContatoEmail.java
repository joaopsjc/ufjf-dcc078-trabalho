/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.implementadores;

import model.interfaces.Contato;

/**
 *
 * @author Andre William
 */
public class ContatoEmail implements Contato{
    String valor;
    Long id;
    @Override
    public String getValor() {
        return valor;
    }

    @Override
    public String getTipo() {
        return "Email";
    }

    @Override
    public void setValor(String novoValor) {
        valor = novoValor;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(Long novoID) {
        id = novoID;
    }
    
}