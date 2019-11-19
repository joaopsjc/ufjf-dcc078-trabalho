/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Notificacao;

/**
 *
 * @author ice
 */
public class NotificacaoDAO  extends DAO{
    private static NotificacaoDAO instance = new NotificacaoDAO();
    public static NotificacaoDAO getInstance(){
        return instance;
    }
    
    public void insert(Notificacao notificacao) throws SQLException, ClassNotFoundException{
        Connection conn = DatabaseLocator.getInstance().getConection();
        PreparedStatement st = conn.prepareStatement("insert into notificacao(id_cliente,mensagem,dataHoraNotificacao) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
        st.setLong(1,notificacao.getId_cliente());
        st.setString(2,notificacao.getMensagem());
        st.setString(3,notificacao.getDataHoraNotificacao());
        executeQuery(st);
    }
    
    public void marcarComoLida(Long id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseLocator.getInstance().getConection();
        PreparedStatement st = conn.prepareStatement("update notificacao set lida=1 where id=?",Statement.RETURN_GENERATED_KEYS);
        st.setLong(1,id);
        executeQuery(st);
    }
    
    public void marcarComoLida(List<String> idsList) throws SQLException,  ClassNotFoundException {
        for(Iterator i = idsList.iterator();i.hasNext();)
            marcarComoLida(Long.parseLong((String)i.next()));        
    }
    
    public List<Notificacao> getNotificacoesNaoLidasByUserId(Long id_cliente) throws SQLException, ClassNotFoundException{
        
        String query = "select * from notificacao where lida=0 and id_cliente="+id_cliente;
        return getListNotificacoes(query);
    }

    public int getCountNotificacoesCliente(Long id) throws SQLException, ClassNotFoundException {
        return getNotificacoesNaoLidasByUserId(id).size();
    }
    
    
    public Notificacao populateNotificacaoObjectFromDataset(ResultSet rs) throws SQLException{
        Long id = rs.getLong("id");
        String mensagem = rs.getString("mensagem");
        String dataHoraNotificacao = rs.getString("dataHoraNotificacao");
        boolean lida = rs.getInt("lida")==1;
        Long id_cliente = rs.getLong("id_cliente");

        Notificacao notificacao = new Notificacao(id,id_cliente, mensagem, dataHoraNotificacao,lida);
        return notificacao;
    }
    

    private List<Notificacao> getListNotificacoes(String query) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;
        List<Notificacao> notificacoes = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                
                Notificacao notificacao = populateNotificacaoObjectFromDataset(rs);
                notificacoes.add(notificacao);
                
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return notificacoes;
    }
    
}
