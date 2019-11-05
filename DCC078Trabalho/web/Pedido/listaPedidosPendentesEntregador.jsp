<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../_shared/header.jsp" %>
<div class="wrapper wrapper-content animated fadeIn">
    <div class="row">
        <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Pedidos pendentes</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row toolbar-crud-grid">
                            <button onclick="UF.Entregador.AceitarPedido(this)" class="btn btn-success" type="button"><i class="fa fa-check"></i> Aceitar</button>
                            <button onclick="UF.Entregador.RejeitarPedido(this)" class="btn btn-danger" type="button"><i class="fa fa-times"></i> Rejeitar</button>
                        </div>
                        <table id="pedidos-pendentes-grid-resumo"class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="table-th-check"><input type="checkbox"  class="i-checks" id="pedidos-pendentes-grid-resumo-check-all" name="check-grid"></th>
                                <th>Restaurante</th>
                                <th>Cliente</th>
                                <th>Entregador</th>
                                <th>Frete</th>
                                <th>Valor Total</th>
                                <th>Estado</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listPedidos}" var="pedido">
                                <tr>
                                    <td><input type="checkbox"  class="i-checks" data-id="<c:out value="${pedido.getId()}" />" name="check-grid"></td>
                                    <td><c:out value="${pedido.getEmpresa().getNome()}" /></td>
                                    <td><c:out value="${pedido.getCliente().getNome()}" /></td>
                                    <td><c:out value="${pedido.getEntregador().getNome()}" /></td>
                                    <td><c:out value="${pedido.getFrete()}" /></td>
                                    <td><c:out value="${pedido.getPrecoProdutos()}" /></td>
                                    <td><c:out value="${pedido.getEstado().getNome()}" /></td>
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
            
            UF.Helpers.SetCheckAllGrid('pedidos-pendentes-grid-resumo');
        });
</script>