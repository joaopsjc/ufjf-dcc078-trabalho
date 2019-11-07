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
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("insert into notificacao(id_cliente,mensagem,dataHoraNotificacao) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,notificacao.getId_cliente());
            st.setString(2,notificacao.getMensagem());
            st.setString(3,notificacao.getDataHoraNotificacao());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating endereco failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void marcarComoLida(Long id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.prepareStatement("update notificacao set lida=1 where id=?",Statement.RETURN_GENERATED_KEYS);
            st.setLong(1,id);
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update notificacao failed, no rows affected.");
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public void marcarComoLida(List<String> idsList) throws SQLException,  ClassNotFoundException {
        for(Iterator i = idsList.iterator();i.hasNext();)
            marcarComoLida(Long.parseLong((String)i.next()));        
    }
    
    public List<Notificacao> getNotificacoesNaoLidasByUserId(Long id_cliente) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Notificacao> notificacoes = new ArrayList<>();
        try {
            conn = DatabaseLocator.getInstance().getConection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("select * from notificacao where lida=0 and id_cliente="+id_cliente);

            while (rs.next())
            {
                Long id = rs.getLong("id");
                String mensagem = rs.getString("mensagem");
                String dataHoraNotificacao = rs.getString("dataHoraNotificacao");
                boolean lida = rs.getInt("lida")==1;
                
                Notificacao n = new Notificacao(id,id_cliente, mensagem, dataHoraNotificacao,lida);
                notificacoes.add(n);
                
            }
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        return notificacoes;
    }

    public int getCountNotificacoesCliente(Long id) throws SQLException, ClassNotFoundException {
        return getNotificacoesNaoLidasByUserId(id).size();
    }
    
}
