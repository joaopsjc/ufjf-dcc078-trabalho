package model.estados;

import model.abstratos.Produto;
import model.interfaces.ProdutoEstado;

public class ProdutoEstadoIndisponivel implements ProdutoEstado{

    @Override
    public String getEstado() {
        return "Indisponivel";
    }

    @Override
    public boolean disponivel(Produto produto) {
        produto.setEstado(new ProdutoEstadoDisponivel());
        return true;
    }

    @Override
    public boolean indisponivel(Produto produto) {
        return false;
    }

    @Override
    public boolean bloqueado(Produto produto) {
        produto.saveToMemento();
        produto.setEstado(new ProdutoEstadoBloqueado());
        return true;
    }

    @Override
    public boolean desbloqueado(Produto produto) {
        return false;
    }
    
}
