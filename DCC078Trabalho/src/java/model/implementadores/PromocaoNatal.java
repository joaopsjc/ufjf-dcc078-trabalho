package model.implementadores;

import model.interfaces.Promocao;

public class PromocaoNatal implements Promocao{
    private final String nome = "Promoção de Natal";
    private final String tipo = "Natal";
    
    @Override
    public int obterDesconto() {
            return 10;
    }
    @Override
    public String obterPromocao() {
            return nome;
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

}
