/*
 * Padrão de projeto Singleton.
 * Este padrão garante a existência de apenas uma instância de uma classe, 
 * mantendo um ponto global de acesso ao seu objeto.
 * exemplo: priavate static, public static getInstance
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Anderson
 */
public class DatabaseLocator {
    private static DatabaseLocator instance = new DatabaseLocator();
    public static DatabaseLocator getInstance(){
        return instance;
    }
    public Connection getConection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn =
                DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/KQ661Av6Jf", "KQ661Av6Jf", "Pwb3weObef");
        Statement st = conn.createStatement();
        return conn;
    }
    
}
