/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interfaces;

import model.abstratos.Produto;

/**
 *
 * @author JoaoPSJC
 */
public interface ProdutoEstado{
    public String getEstado();
    public boolean disponivel(Produto produto);
    public boolean indisponivel(Produto produto);
    public boolean bloqueado(Produto produto);
    public boolean desbloqueado(Produto produto);
}
