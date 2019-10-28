<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../_shared/header.jsp" %>
<div class="wrapper wrapper-content animated fadeIn">
    <div class="row">
        <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Alterar senha </h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form method="post" class="form-horizontal" action="FrontController">
                            <input type="hidden" name="action" value="AlterarSenha"/>
                                
                            <div class="form-group group-label">
                                <div class="col-sm-12">
                                    <label for="senha" class="col-sm-4 control-label">Senha atual</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="password" name="senha" class="form-control" required="">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group group-label">
                                <div class="col-sm-12">
                                    <label for="novaSenha" class="col-sm-4 control-label">Nova senha</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="password" name="novaSenha" class="form-control" required="">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group group-label">
                                <div class="col-sm-12">
                                    <label for="confirmSenha" class="col-sm-4 control-label">Confirme a senha</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="password" name="confirmSenha" class="form-control" required="">
                                    </div>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <button class="btn btn-primary" type="submit"><i class="fa fa-check"></i> Salvar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
    </div>
</div>
<%@ include file="../_shared/footer.jsp" %>

<script>
    $(document).ready(function(){
        var msgSuccess = "<%=request.getAttribute("messageSuccess")%>";
        var msgError = "<%=request.getAttribute("messageError")%>";
        if (msgError.length>0)
            UF.Alert.Error({message:msgError});
        else if (msgSuccess.length>0)
            UF.Alert.Success({
                message:msgSuccess
            });

    });
</script>