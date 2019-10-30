/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.interfaces.ProdutoEstado;

/**
 *
 * @author ice
 */
public class ProdutoMemento {
    private ProdutoEstado estado;

    public ProdutoMemento(ProdutoEstado estado) {
        this.estado = estado;
    }
    public ProdutoEstado getEstadoSalvo()
    {
        return estado;
    }
    public String toString()
    {
        return estado.getEstado();
    }
    
}
