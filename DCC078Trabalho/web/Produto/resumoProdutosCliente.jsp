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
                            <button onclick="UF.Cliente.AdicionarProdutosCarrinho(this)" class="btn btn-success" type="button"><i class="fa fa-plus"></i> Adicionar ao carrinho</button>
                        </div>
                        <table id="produto-grid-resumo"class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="table-th-check"><input type="checkbox"  class="i-checks" id="produto-grid-resumo-check-all" name="check-grid"></th>
                                <th>Nome</th>
                                <th>Categoria</th>
                                <th>Restaurante</th>
                                <th>Preço</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listProdutos}" var="produto">
                                <tr>
                                    <td><input type="checkbox"  class="i-checks" data-id="<c:out value="${produto.getId()}" />" name="check-grid"></td>
                                    <td><c:out value="${produto.getNome()}" /></td>
                                    <td><c:out value="${produto.getCategoria()}" /></td>
                                    <td><c:out value="${produto.getNomeEmpresa()}" /></td>
                                    <td><c:out value="${produto.getPreco()}" /></td>
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