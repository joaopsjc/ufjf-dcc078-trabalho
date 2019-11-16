package model.interfaces;

import model.abstratos.Produto;

public interface ProdutoEstado{
    public String getEstado();
    public boolean disponivel(Produto produto);
    public boolean indisponivel(Produto produto);
    public boolean bloqueado(Produto produto);
    public boolean desbloqueado(Produto produto);
}
