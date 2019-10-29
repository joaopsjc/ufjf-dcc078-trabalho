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
public class ContatoNumero implements Contato{
    String valor;
    Long id;
    Long id_usuario;
    
    @Override
    public String getValor() {
        return valor;
    }
    @Override
    public String getTipo() {
        return "Numero";
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

    @Override
    public long getIdUsuario() {
        return id_usuario;
    }

    @Override
    public void setIdUsuario(Long novoIdUsuario) {
        this.id_usuario = novoIdUsuario;
    }
    
    
}
