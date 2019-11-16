package model;

import model.estados.ProdutoEstadoIndisponivel;
import model.interfaces.ProdutoEstado;

public class ProdutoMemento {
    private ProdutoEstado estado;

    public ProdutoMemento(ProdutoEstado estado) {
        this.estado = estado;
    }

    public ProdutoMemento() {
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
