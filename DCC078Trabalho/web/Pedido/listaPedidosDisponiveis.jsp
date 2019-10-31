<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../_shared/header.jsp" %>
<div class="wrapper wrapper-content animated fadeIn">
    <div class="row">
        <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Produtos </h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                    <div class="row toolbar-crud-grid">
                        <button onclick="" class="btn btn-success" type="button"><i class="fa fa-plus"></i> Aceitar</button>
                    </div>
                    <div class="row toolbar-crud-grid">
                        <button onclick="" class="btn btn-success" type="button"><i class="fa fa-plus"></i> Rejeitar</button>
                    </div>
                        <table id="produto-grid-resumo"class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Restaurante</th>
                                <th>Cliente</th>
                                <th>Frete</th>
                                <th>Valor Total</th>
                                <th>Estado</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listPedidos}" var="pedido">
                                <tr>
                                    <td><input type="checkbox"  class="i-checks" data-id="<c:out value="${produto.getId()}" />" name="check-grid"></td>
                                    <td><c:out value="${pedido.getEmpresa().getNome()}" /></td>
                                    <td><c:out value="${pedido.getCliente().getNome()}" /></td>
                                    <td><c:out value="${pedido.getFrete()}" /></td>
                                    <td><c:out value="${pedido.getPrecoProdutos()}" /></td>
                                    <td><c:out value="${pedido.getEstado().getEstado()}" /></td>
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
            
            UF.Helpers.SetCheckAllGrid('produto-grid-resumo');
        });
</script>