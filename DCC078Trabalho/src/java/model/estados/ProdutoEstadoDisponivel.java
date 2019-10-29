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
public class ProdutoEstadoDisponivel implements ProdutoEstado{
    public String getEstado() {
        return "Disponivel";
    }
    @Override
    public boolean diposnivel(Produto produto) {
        return false;
    }

    @Override
    public boolean indisponivel(Produto produto) {
        produto.setEstado(new ProdutoEstadoIndisponivel());
        return true;
    }

    @Override
    public boolean bloqueado(Produto produto) {
        produto.setEstado(new ProdutoEstadoBloqueado());
        return true;
    }
}
