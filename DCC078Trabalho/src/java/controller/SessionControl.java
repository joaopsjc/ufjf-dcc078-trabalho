/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
  
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jjsfa
 */

public class SessionControl implements javax.servlet.Filter {

    public void destroy() {
       // TODO Auto-generated method stub
  
    }
  
    public void doFilter(ServletRequest request, ServletResponse response,
           FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession sess = httpRequest.getSession(true);

        String action = (String)httpRequest.getParameter("action"); 
        action = action == null ? "" : action;
        
        String servletPath = httpRequest.getServletPath();
        
        if ( action.equals("DoLogin") || action.equals("Login") || servletPath.equals("/login.jsp") || servletPath.startsWith("/assets")){
            chain.doFilter(request, response);
            return;
        }
        Integer userId = (Integer)sess.getAttribute("loggedUser");

        if (userId==null){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("FrontController?action=Login");
            return;
        }else{
            chain.doFilter(request, response);
        } 
  
    }
  
    public void init(FilterConfig arg0) throws ServletException {
       // TODO Auto-generated method stub
  
    }
}
