/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import controller.ActionFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TipoUsuario;
import model.abstratos.Usuario;
import controller.UsuarioFactory;
import model.extensores.*;
import persistence.TipoUsuarioDAO;
import persistence.UsuarioDAO;

/**
 *
 * @author jjsfa
 */
public class DoRegisterAction implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String confirmSenha = request.getParameter("confirmSenha");
        Long tipoUsuarioId = Long.parseLong(request.getParameter("tipoUsuario"));
        String tipoUsuarioText = request.getParameter("tipoUsuarioText");
        
        
        try {
            if (!senha.equals(confirmSenha)){
                request.setAttribute("messageError", "As senhas informadas não são idênticas");
                request.setAttribute("messageSuccess", "");
                List<TipoUsuario> listTipoUsuario = TipoUsuarioDAO.getInstance().getTiposUsuario();
                request.setAttribute("listTipoUsuario", listTipoUsuario);
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            
            Usuario usuario = UsuarioFactory.create(tipoUsuarioText);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setNome(nome);
            UsuarioDAO.getInstance().insert(usuario);
            request.setAttribute("messageError", "");
            request.setAttribute("messageSuccess", "Registro realizado com sucesso!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            
        } catch (ServletException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoRegisterAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
