package model.abstratos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.Pedido;

public abstract class Usuario {
    private Long id;
    private String nome,
            login,
            senha,
            documento;
    private final List<Endereco> enderecos;
    private final List<Pedido> pedidos;
    private List<Produto> produtos;
    private Pedido carrinho;
    
    private Endereco enderecoPrincipal;

    public Usuario(){
        enderecos = new ArrayList<>();
        pedidos = new ArrayList<>();
        produtos = new ArrayList<>();
    }
    
    public String getTipo()
    {
        return "Usuario";
    }
    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public String getNickname(){
        String[] names = this.nome.split(" ");
        String firstName = names[0];
        if (names.length==1)
            return firstName;
        String lastName = names[names.length-1];
        
        
        return firstName+" "+lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getDocumento() {
        return documento;
    }


    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    
    public void addEndereco(Endereco novoEndereco)
    {
        enderecos.add(novoEndereco);
    }
    public void addPedido(Pedido novoPedido)
    {
        pedidos.add(novoPedido);
    }

    public void addPedido(List<Pedido> listaPedidosPendentes) {
        pedidos.addAll(listaPedidosPendentes);
    }
    

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setCarrinho(Pedido carrinho) {
        this.carrinho = carrinho;
    }

    public Pedido getCarrinho() {
        if (this.carrinho == null)
            this.carrinho = new Pedido();
        return this.carrinho;
    }

    public abstract String getQtdCarrinho();

    public Endereco getEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    public void setEnderecoPrincipal(Endereco enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }

    public List<Pedido> getPedidos(List<String> idsList) {
        List<Pedido> result = new ArrayList<>();
        for(Iterator pedidosIteraor = getPedidos().iterator(); pedidosIteraor.hasNext();){
            Pedido pedidoAtual = (Pedido) pedidosIteraor.next();
            if (idsList.contains(pedidoAtual.getId().toString()))
                result.add(pedidoAtual);                   
        }
        return result;
    }
    
    public abstract void setDynamicInfo(HttpSession sessaonAtual);
}