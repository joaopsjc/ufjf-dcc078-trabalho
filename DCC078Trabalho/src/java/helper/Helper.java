package helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Pedido;
import model.abstratos.Endereco;
import model.abstratos.Produto;
import model.abstratos.Usuario;
import persistence.EnderecoDAO;
import persistence.NotificacaoDAO;

public class Helper {
    
    
    private static final Helper instance = new Helper();
    public static Helper getInstance(){
        return instance;
    }
    
    public Usuario getLoggedUser(HttpServletRequest httpRequest){
        HttpSession sessaoAtual = httpRequest.getSession(true);

        Usuario usuatioLogado = (Usuario)sessaoAtual.getAttribute("loggedUser");
        return usuatioLogado;
    }
    
    public List<Produto> getListProdutos(HttpServletRequest httpRequest){
        return getLoggedUser(httpRequest).getProdutos();
    }
    
    public List<Produto> getListProdutosByIds(String selectedIds,HttpServletRequest request){
        List<String> listSelectedIds = stringTolist(selectedIds);
        List<Produto> result = new ArrayList<>();
        for(Iterator produtosIterator = getListProdutos(request).iterator();produtosIterator.hasNext();){
            Produto produtoAtual = (Produto)produtosIterator.next();
            if (listSelectedIds.contains(produtoAtual.getId().toString()))
                result.add(produtoAtual);
        }
        return result;
    }
    
    public void setListProdutos(List<Produto> produtos,HttpServletRequest request){
        List<Produto> listProdutos = getListProdutos(request);
        if (listProdutos != null){
            for(Iterator produtosIterator = produtos.iterator();produtosIterator.hasNext();){
                Produto produtoAtual = (Produto)produtosIterator.next();
                for(Iterator j = listProdutos.iterator();j.hasNext();){
                    Produto proximoProduto = (Produto)j.next();
                    if (Objects.equals(proximoProduto.getId(), produtoAtual.getId()))
                        produtoAtual.saveToMemento(proximoProduto.getEstadoSalvo());
                }   

            }
        }
        getLoggedUser(request).setProdutos(produtos);
    }
    
    public List<String> stringTolist(String selectedIds){
        return new ArrayList<>(Arrays.asList(selectedIds.split(",")));
    }

    public Pedido getCarrinhoByClienteId(HttpServletRequest httpRequest) {
        Pedido pedido = getLoggedUser(httpRequest).getCarrinho();
        return pedido;
    }

    public void addCarrinho(Pedido carrinho,HttpServletRequest httpRequest) {
        getLoggedUser(httpRequest).setCarrinho(carrinho);
    }

    public void zeraCarrinhoByClienteId(HttpServletRequest request) {
        getLoggedUser(request).setCarrinho(new Pedido());
    }

    
    
    public void setDynamicInfoLoggedUser(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession sessaoAtual = httpRequest.getSession(false);

        if (sessaoAtual == null)
            return;
        
        Usuario usuarioLogado = (Usuario)sessaoAtual.getAttribute("loggedUser");
        if (usuarioLogado == null)
            return;
        sessaoAtual.setAttribute("menuPageName", "menu"+usuarioLogado.getTipo()+".jsp");       
        
        switch(usuarioLogado.getTipo()){
            case "Entregador":
                setDynamicInfoEntregador(sessaoAtual,usuarioLogado);
                break;
            case "Empresa":
                setDynamicInfoEmpresa(sessaoAtual,usuarioLogado);
                break;
            case "Cliente":
                setDynamicInfoCliente(sessaoAtual,usuarioLogado);
                break;
        }
    }
    
    private void setDynamicInfoEntregador(HttpSession sessaoAtual, Usuario usuarioLogado) throws SQLException, ClassNotFoundException{
        sessaoAtual.setAttribute("countPedidosPendentesEntregador", HelperPedido.getInstance().getCountPedidosPendentesEntregador(usuarioLogado.getId()));
    }
    private void setDynamicInfoEmpresa(HttpSession sess, Usuario usuarioLogado) throws SQLException, ClassNotFoundException{
        sess.setAttribute("countPedidosPendentesEmpresa", HelperPedido.getInstance().getCountPedidosPendentesEmpresa(usuarioLogado.getId()));
    }
    private void setDynamicInfoCliente(HttpSession sessaonAtual, Usuario usuarioLogado) throws SQLException, ClassNotFoundException{
        Endereco endereco = EnderecoDAO.getInstance().getPrincipalByUserId(usuarioLogado.getId());
        usuarioLogado.setEnderecoPrincipal(endereco);
        sessaonAtual.setAttribute("countNotificacoesCliente", NotificacaoDAO.getInstance().getCountNotificacoesCliente(usuarioLogado.getId()));
    }
}