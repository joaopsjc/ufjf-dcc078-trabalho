/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author andradeld
 */
public class Pedido {
    private Long id;
    private Produto produto;
    private String observacao;
    private Entregador entregador;
    private PedidoEstado estado;
}
