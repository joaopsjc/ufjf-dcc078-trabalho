/* Padrão de projeto Singleton.
 * DAO - Objeto de acesso a dados.
 * 
 * 
 */
package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Empresa;

/**
 *
 * @author Anderson
 */
public class EmpresaDAO {
    private static EmpresaDAO instance = new EmpresaDAO();
    public static EmpresaDAO getInstance(){
        return instance;
    }
    
    private void closeResources(Connection conn, Statement st) {
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        } catch(SQLException e) {

        }
    }  
    
    
    public List<Empresa> getEmpresas()  throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
        List<Empresa> listaEmpresas = new ArrayList();
        try {
                conn = DatabaseLocator.getInstance().getConection();
                st = conn.createStatement();
                
                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery("select id,nome from empresa");
                
                // iterate through the java resultset
                while (rs.next())
                {
                    Long id = rs.getLong("id");
                    String nome = rs.getString("nome");
                    
                    //listaEmpresas.add(new Empresa(id,nome));
                }
            } catch(SQLException e) {
                throw e;
            } finally {
                closeResources(conn, st);
            }
        return listaEmpresas;
    }

}
