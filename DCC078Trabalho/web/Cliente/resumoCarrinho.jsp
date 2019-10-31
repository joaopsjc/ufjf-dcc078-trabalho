<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../_shared/header.jsp" %>
<div class="wrapper wrapper-content animated fadeIn">
    <div class="row">
        <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Carrinho </h5>
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
                                <th>Pre�o</th>
                                <th>Quantidade</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${carrinho.produtos}" var="produto">
                                <tr>
                                    <td><input type="checkbox"  class="i-checks" data-id="<c:out value="${produto.getId_produto()}" />" name="check-grid"></td>
                                    <td><c:out value="${produto.getProduto().getNome()}" /></td>
                                    <td><c:out value="${produto.getProduto().getCategoria()}" /></td>
                                    <td><c:out value="${produto.getProduto().getNomeEmpresa()}" /></td>
                                    <td><c:out value="${produto.getProduto().getPreco()}" /></td>
                                    <td><input type="number" min="1" value="<c:out value="${produto.getQuantidade()}" />" name="quantidade-produto-<c:out value="${produto.getId_produto()}" />"/></td>
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