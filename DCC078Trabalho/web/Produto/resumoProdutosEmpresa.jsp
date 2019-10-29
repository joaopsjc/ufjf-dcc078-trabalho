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
                            <button class="btn btn-success" type="submit"><i class="fa fa-plus"></i> Adicionar</button>
                            <button class="btn btn-warning" type="submit"><i class="fa fa-edit"></i> Editar</button>
                            <button class="btn btn-danger" type="submit"><i class="fa fa-trash"></i> Excluir</button> 
                        </div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Nome</th>
                                <th>Categoria</th>
                                <th>Preço</th>
                                <th>Estado</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listProdutos}" var="produto">
                                <tr>
                                    <td><td><input type="checkbox"  checked class="i-checks" data-id="<c:out value="${produto.getId()}" />" name="check-grid"></td></td>
                                    <td><c:out value="${produto.getNome()}" /></td>
                                    <td><c:out value="${produto.getCategoria()}" /></td>
                                    <td><c:out value="${produto.getPreco()}" /></td>
                                    <td><c:out value="${produto.getNomeEstado()}" /></td>
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
        });
    </script>