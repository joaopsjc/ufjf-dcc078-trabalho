/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ice
 */
public class PromocaoGenerica implements Promocao{

    public int obterDesconto() {
            return 10;
        }

    public String obterPromocao() {
            return "Dia das Mães";
        }
    
}
