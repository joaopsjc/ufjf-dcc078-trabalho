<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>UFJF Food | Login</title>

    <link href="../DCC078Trabalho/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Sweet Alert -->
    <link href="../DCC078Trabalho/assets/css/plugins/sweetalert/sweetalert2.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/css/animate.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/css/style.css" rel="stylesheet">
    <link href="../DCC078Trabalho/assets/css/style_custom.css" rel="stylesheet">

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">UFJF</h1>

            </div>
            <h3>Bem vindo ao UFJF Food</h3>
            <form class="m-t" role="form" method="post" action="FrontController">
                <input type="hidden" name="action" value="DoLogin" />
                <div class="form-group">
                    <input type="email" name="login" class="form-control" placeholder="Usuario" required="">
                </div>
                <div class="form-group">
                    <input type="password" name="senha" class="form-control" placeholder="Senha" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">Login</button>

                <p class="text-muted text-center"><small>N�o possui uma conta?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="FrontController?action=Register">Cadastre-se</a>
            </form>
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="../DCC078Trabalho/assets/js/jquery-2.1.1.js"></script>
    <script src="../DCC078Trabalho/assets/js/bootstrap.min.js"></script>

    <!-- Sweet alert -->
    <script src="../DCC078Trabalho/assets/js/plugins/sweetalert/sweetalert2.min.js"></script>
    <script src="../DCC078Trabalho/assets/js/script.js"></script>
    
    <script>
        $(document).ready(function(){
            var msgError = "<%=request.getAttribute("messageError")%>";
            if (msgError.length>0 && msgError != 'null')
                UF.Alert.Error({message:msgError});
        });
    </script>

</body>

</html>
