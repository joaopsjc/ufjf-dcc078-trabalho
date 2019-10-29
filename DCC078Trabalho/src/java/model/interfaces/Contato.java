/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;

/**
 *
 * @author andradeld
 */
public interface Contato {
    public String getValor();
    public long getId();
    public long getIdUsuario();
    public String getTipo();
    public void setValor(String novoValor);
    public void setId(Long novoId);
    public void setIdUsuario(Long novoIdUsuario);
}
