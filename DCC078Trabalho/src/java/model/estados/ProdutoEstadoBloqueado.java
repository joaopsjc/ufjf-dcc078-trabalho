package model.estados;

import model.abstratos.Produto;
import model.interfaces.ProdutoEstado;

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
    @Override
    public boolean desbloqueado(Produto produto) {
        produto.restoreFromMemento();
        return true;
    }
    
    
}
