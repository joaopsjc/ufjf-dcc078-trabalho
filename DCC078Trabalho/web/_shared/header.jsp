
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.abstratos.Usuario"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>UFJF Food</title>

    <link href="../DCC078Trabalho/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/font-awesome/pe-icon-set-food.min.css" rel="stylesheet">
    

    <!-- Sweet Alert -->
    <link href="../DCC078Trabalho/assets/css/plugins/sweetalert/sweetalert2.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/css/animate.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/css/style.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/css/style_custom.css" rel="stylesheet">

</head>

<body>

<div id="wrapper">
    
    <%
        String menuPageName = "/_shared/"+(String)request.getSession().getAttribute("menuPageName");
    %>
    <jsp:include page ='<%=menuPageName%>'/>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top white-bg" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                </div>
                <c:if test = "${loggedUser.getTipo() == 'Cliente'}">
                    <form role="search" class="navbar-form-custom" action="FrontController">
                        <input type="hidden" value="GetProdutosByGlobalSearch" name="action"/>
                        <div class="form-group">
                            <input type="text" placeholder="Pesquise por produtos ou categorias..." class="form-control" name="stringSearch" id="stringSearch">
                        </div>
                    </form>
                </c:if>
                <ul class="nav navbar-top-links navbar-right">


                    <c:if test = "${loggedUser.getTipo() == 'Cliente'}">
                        <li>
                            <a id="link-carrinho" class="count-info" href="FrontController?action=ResumoCarrinho">
                                <i class="fa fa-shopping-cart" title="Carrinho"></i>
                                <span class="label label-danger"><c:out value="${loggedUser.getQtdCarrinho()}" /></span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test = "${loggedUser.getTipo() == 'Entregador'}">
                        <li>
                            <a id="link-entregas" class="count-info" href="FrontController?action=Home">
                                <i class="fa fa-truck" title="Entregas"></i>
                                <span class="label label-danger"></span>
                            </a>
                        </li>
                    </c:if>
                    <li>
                        
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <c:out value="${loggedUser.getNickname()}" /> <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="FrontController?action=Profile"><i class="fa fa-user"></i> Perfil</a></li>
                            <li><a href="FrontController?action=FormAlterarSenha"><i class="fa fa-key"></i> Alterar senha</a></li>
                            <li class="divider"></li>
                            <li><a href="FrontController?action=DoLogout"><i class="fa fa-sign-out"></i> Logout</a></li>
                        </ul>
                    </li>
                </ul>
                

            </nav>
        </div>
        