/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.estados;

import model.Produto;
import model.interfaces.ProdutoEstado;

/**
 *
 * @author JoaoPSJC
 */
public class ProdutoEstadoBloqueado implements ProdutoEstado{
    
    @Override
    public String getEstado() {
        return "Bloqueado";
    }
    @Override
    public boolean disponivel(Produto produto) {
        return false;
    }

    @Override
    public boolean indisponivel(Produto produto) {
        return false;
    }

    @Override
    public boolean bloqueado(Produto produto) {
        return false;
    }
    
    
}
