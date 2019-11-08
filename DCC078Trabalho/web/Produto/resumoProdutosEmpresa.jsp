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
                        <form method="GET" action="FrontController" id="form-detalhe-produto">
                            <input type="hidden" name="id" value="" />
                            <input type="hidden" name="action" value="FormDetalheProduto" />
                        </form>
                        <div class="row toolbar-crud-grid">
                            <div class="btn-group">
                                <button data-toggle="dropdown" class="btn btn-success "><i class="fa fa-plus"></i>Adicionar</button>
                                <ul class="dropdown-menu">
                                    <li><a href="FrontController?action=FormNovoProduto&categoria=Sanduiche">Sanduíche</a></li>
                                    <li><a href="FrontController?action=FormNovoProduto&categoria=Pizza">Pizza</a></li>
                                    <li><a href="FrontController?action=FormNovoProdutoCombo">Combo</a></li>
                                </ul>
                            </div>
                            <button onclick="UF.Produto.EditarProduto(this)" class="btn btn-primary" type="button"><i class="fa fa-edit"></i> Editar</button>
                            <button onclick="UF.Produto.ExcluirProduto(this)" class="btn btn-danger" type="button"><i class="fa fa-trash"></i> Excluir</button> 
                            <button onclick="UF.Produto.BloquearDesbloquearProduto(this)" data-type="Bloquear" class="btn btn-warning" type="button"><i class="fa fa-lock"></i> Bloquear</button> 
                            <button onclick="UF.Produto.BloquearDesbloquearProduto(this)" data-type="Desbloquear" class="btn btn-primary" type="button"><i class="fa fa-unlock"></i> Desbloquear</button> 
                            <button onclick="UF.Produto.DisponibilizarIndisponibilizarProduto(this)" data-type="Disponivel" class="btn btn-primary" type="button"><i class="fa fa-check"></i> Disponível</button> 
                            <button onclick="UF.Produto.DisponibilizarIndisponibilizarProduto(this)" data-type="Indisponivel" class="btn btn-warning" type="button"><i class="fa fa-times"></i> Indisponível</button> 
                        </div>
                        <table id="produto-grid-resumo"class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="table-th-check"><input type="checkbox"  class="i-checks" id="produto-grid-resumo-check-all" " name="check-grid"></th>
                                <th>Nome</th>
                                <th>Categoria</th>
                                <th>Preço</th>
                                <th>Estado</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listProdutos}" var="produto">
                                <tr>
                                    <td><input type="checkbox"  class="i-checks" data-id="<c:out value="${produto.getId()}" />" name="check-grid"></td>
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
            
            UF.Helpers.SetCheckAllGrid('produto-grid-resumo');
        });
    </script>