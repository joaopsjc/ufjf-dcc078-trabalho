package helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EntregadorChainResponsibility;
import model.Pedido;
import model.PedidoProduto;
import model.abstratos.Usuario;
import model.extensores.Empresa;
import model.extensores.Entregador;
import persistence.PedidoDAO;
import persistence.UsuarioDAO;

public class HelperPedido {
    private static final HelperPedido instance = new HelperPedido();
    public static HelperPedido getInstance(){
        return instance;
    }
    
    public List<Pedido> dividePedidoPorEmpresa(Pedido pedido) throws SQLException, ClassNotFoundException {
        
        HashMap<Long, Pedido> pedidosPorEmpresa = new HashMap<>();
        for(Iterator pedidosIterator = pedido.getProdutos().iterator();pedidosIterator.hasNext();){
            PedidoProduto pedidoProduto = (PedidoProduto)pedidosIterator.next();
            Pedido pedidoAtual;
            if (pedidosPorEmpresa.containsKey(pedidoProduto.getProduto().getId_empresa())){
                pedidoAtual = pedidosPorEmpresa.get(pedidoProduto.getProduto().getId_empresa());
            }else{
                pedidoAtual = new Pedido();
                Empresa empresa = (Empresa)UsuarioDAO.getInstance().getById(pedidoProduto.getProduto().getId_empresa());
                pedidoAtual.setEmpresa(empresa);
                pedidosPorEmpresa.put(empresa.getId(),pedidoAtual);
                pedidoAtual.setCliente(pedido.getCliente());
                pedidoAtual.setEstado(pedido.getEstado());
                pedidoAtual.setEndereco(pedido.getEndereco());
            }
            pedidoAtual.addProduto(pedidoProduto);
        }       
        List<Pedido> result = new ArrayList<>(pedidosPorEmpresa.values());
        
        return result;
    }    
    
    public void iniciarPreparoPedidos(List<Pedido> pedidos) throws SQLException, ClassNotFoundException {
        for(Iterator pedidosIterator = pedidos.iterator();pedidosIterator.hasNext();){
            Pedido pedido = (Pedido)pedidosIterator.next();
            pedido.getEstado().emPreparo(pedido);
        }
        PedidoDAO.getInstance().updateEstado(pedidos);
    }
    
    public void FinalizarPreparoPedidos(List<Pedido> pedidos) throws SQLException, ClassNotFoundException {
        for(Iterator pedidosIterator = pedidos.iterator();pedidosIterator.hasNext();){
            Pedido pedido = (Pedido)pedidosIterator.next();
            pedido.getEstado().aguardandoEntregador(pedido);
            pedido.getEstado().aCaminho(pedido);
            
            
        }
        PedidoDAO.getInstance().updateEstado(pedidos);
    }
    
    public void cancelarPedidos(List<Pedido> pedidos) throws SQLException, ClassNotFoundException {
        for(Iterator pedidosIterator = pedidos.iterator();pedidosIterator.hasNext();){
            Pedido pedido = (Pedido)pedidosIterator.next();
            pedido.getEstado().cancelado(pedido);
        }
        PedidoDAO.getInstance().updateEstado(pedidos);
    }
    
    public int getCountPedidosPendentesEmpresa(Long id_empresa) throws SQLException, ClassNotFoundException{
        return PedidoDAO.getInstance().getCountPedidosPendentes(id_empresa);
    }

    public void adicionarPedidoListaPendentesEntregador(Pedido pedido) {
        Usuario primeiroEntregador = EntregadorChainResponsibility.getInstance().getPrimeiroEntregador();
        if (primeiroEntregador!=null)
            primeiroEntregador.addPedido(pedido);
        else
            EntregadorChainResponsibility.getInstance().addPedidoListaPendente(pedido);
    }

    public int getCountPedidosPendentesEntregador(Long id_entregador) throws SQLException, ClassNotFoundException {
        Usuario entregador = EntregadorChainResponsibility.getInstance().isInChain(id_entregador);
        return entregador.getPedidos().size();
    }

    public void dispensarEntrega(List<Pedido> pedidos, Entregador entregador) {
        try {
            entregador.repassarPedidos(pedidos);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HelperPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void aceitarEntrega(List<Pedido> pedidos, Entregador entregador) {
        try {
            entregador.aceitarPedidos(pedidos);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HelperPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
