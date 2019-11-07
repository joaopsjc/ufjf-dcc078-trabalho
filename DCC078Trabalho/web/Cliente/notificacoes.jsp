<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../_shared/header.jsp" %>
<div class="wrapper wrapper-content animated fadeIn">
    <div class="row">
        <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Notificações </h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form method="GET" action="FrontController" id="form-detalhe-endereco">
                            <input type="hidden" name="id" value="" />
                            <input type="hidden" name="action" value="FormDetalheEndereco" />
                        </form>
                        <div class="row toolbar-crud-grid">
                            <button onclick="UF.Cliente.MarcarComoLida(this)" class="btn btn-primary" type="button"><i class="fa fa-check-square-o"></i> Marcar como lida</button>
                        </div>
                        <table id="notificacoes-grid-resumo"class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="table-th-check"><input type="checkbox"  class="i-checks" id="notificacoes-grid-resumo-check-all" name="check-grid"></th>
                                <th>Mensagem</th>
                                <th>Data/Hora</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listNotificacoes}" var="notificacao">
                                <tr>
                                    <td><input type="checkbox"  class="i-checks" data-id="<c:out value="${notificacao.getId()}" />" name="check-grid"></td>
                                    <td><c:out value="${notificacao.getMensagem()}" /></td>
                                    <td><c:out value="${notificacao.getDataHoraNotificacao()}" /></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
    </div>
</div>
<%@ include file="../_shared/footer.jsp" %>

<script>
        $(document).ready(function(){
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            }); 
            
            UF.Helpers.SetCheckAllGrid('notificacoes-grid-resumo');
        });
    </script>