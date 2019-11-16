package model.implementadores;

import model.interfaces.Promocao;

public class PromocaoDiaMaes implements Promocao{
    private final String nome = "Promoção Dia das Mães";
    private final String tipo = "DiaMaes";
    
    @Override
    public int obterDesconto() {
            return 20;
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
