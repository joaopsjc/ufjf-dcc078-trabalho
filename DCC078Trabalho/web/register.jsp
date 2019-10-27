<!DOCTYPE html>
<html>

<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>UFJF Food | Registro</title>

    <link href="../DCC078Trabalho/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/css/animate.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/css/style.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/css/style_custom.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="../DCC078Trabalho/assets/css/plugins/sweetalert/sweetalert2.css" rel="stylesheet">
    
    <%@page contentType="text/html" pageEncoding="windows-1252"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen   animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">UFJF</h1>

            </div>
            <h3>Registro no UFJF Food</h3>
            <p>Crie sua conta agora.</p>
            <form class="m-t" role="form" action="FrontController" method="post">
                <input type="hidden" value="DoRegister" name="action"/>
                <input type="hidden" value="" name="tipoUsuarioText"/>
                <div class="form-group">
                    <input type="text" name="nome" class="form-control" placeholder="Nome" required="">
                </div>
                <div class="form-group">
                    <input type="email" name="login" class="form-control" placeholder="Email" required="">
                </div>
                <div class="form-group">
                    <input type="password" name="senha" class="form-control" placeholder="Senha" required="">
                </div>
                <div class="form-group">
                    <input type="password" name="confirmSenha" class="form-control" placeholder="Confirme a senha" required="">
                </div>
                <div class="form-group">
                    <select name="tipoUsuario" class="form-control" placeholder="Tipo de usuário" required="">
                        <option value="">Tipo de usuário</option>
                        <c:forEach items="${listTipoUsuario}" var="tipoUsuario">
                            <option value="${tipoUsuario.id}">${tipoUsuario.nome}</option>  
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group color-red">
                    <c:out value="${messageInfo}" />
                </div>
                
                <div class="form-group">
                        <div class="checkbox i-checks"><label> <input required="" type="checkbox"><i></i> Concordo com os termos </label></div>
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">Criar conta</button>

                <p class="text-muted text-center"><small>Já possui uma conta?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="FrontController?action=Login">Login</a>
            </form>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="../DCC078Trabalho/assets/js/jquery-2.1.1.js"></script>
    <script src="../DCC078Trabalho/assets/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="../DCC078Trabalho/assets/js/plugins/iCheck/icheck.min.js"></script>
    <!-- Sweet alert -->
    <script src="../DCC078Trabalho/assets/js/plugins/sweetalert/sweetalert2.min.js"></script>
    
    <script src="../DCC078Trabalho/assets/js/script.js"></script>
    <script>
        $(document).ready(function(){
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
            $('[name=tipoUsuario]').on('change',function(ev){
                $('[name=tipoUsuarioText]').val($(ev.target).find('option:selected').text());
            });
            $('[name=tipoUsuarioText]').val($('[name=tipoUsuario]').find('option:selected').text());
            var msgSuccess = "<%=request.getAttribute("messageSuccess")%>";
            var msgError = "<%=request.getAttribute("messageError")%>";
            if (msgError.length>0)
                UF.Alert.Error({message:msgError});
            else if (msgSuccess.length>0)
                UF.Alert.Success({
                    message:msgSuccess,
                    onConfirm: function(){
                        window.location = "FrontController?action=Login";
                    }
                });
        });
    </script>
</body>

</html>
