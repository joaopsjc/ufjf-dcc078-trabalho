/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.Helper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
  
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.abstratos.Usuario;

/**
 *
 * @author jjsfa
 */

public class SessionControl implements javax.servlet.Filter {

    private static final List<String> allowedPages = new ArrayList<String>() {{
        add("DoLogin");
        add("Login");
        add("/login.jsp");
        add("/assets");
        add("/register.jsp");
        add("Register");
        add("DoRegister");
        add("/erro.jsp");
    }};
    
    @Override
    public void destroy() {
       // TODO Auto-generated method stub
  
    }
  
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
           FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String action = (String)httpRequest.getParameter("action"); 
        action = action == null ? "" : action;
        
        String servletPath = httpRequest.getServletPath();
        
        boolean allow = allowedPages.contains(servletPath) || allowedPages.contains(action);
        if ( allow || servletPath.startsWith("/assets")){
            chain.doFilter(request, response);
            return;
        }
        Usuario user = Helper.getInstance().getLoggedUser(httpRequest);

        if (user==null){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("FrontController?action=Login");
        }else{
            chain.doFilter(request, response);
        }   
    }
  
    @Override
    public void init(FilterConfig arg0) throws ServletException {
       // TODO Auto-generated method stub
  
    }
}
