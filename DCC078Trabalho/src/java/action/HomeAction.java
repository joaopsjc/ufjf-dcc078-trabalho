/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.extensores.Empresa;
import persistence.EmpresaDAO;

/**
 *
 * @author jjsfa
 */
public class HomeAction implements Action{
    public void execute(HttpServletRequest request,HttpServletResponse response)
            throws IOException{ 
        response.sendRedirect("index.jsp");
    }
}
