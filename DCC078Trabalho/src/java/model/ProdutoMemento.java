/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.estados.ProdutoEstadoIndisponivel;
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

    ProdutoMemento() {
        this.estado = new ProdutoEstadoIndisponivel();
    }
    
    public ProdutoEstado getEstadoSalvo()
    {
        return estado;
    }
    @Override
    public String toString()
    {
        return estado.getEstado();
    }
    
}
