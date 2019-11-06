<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../_shared/header.jsp" %>
<div class="wrapper wrapper-content animated fadeIn">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>Produto <c:out value="${currentProduto.getId()}"/> </h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <form method="post" class="form-horizontal" action="FrontController">
                        <input type="hidden" name="action" value="SaveProduto"/>
                        <input type="hidden" name="id" value="<c:out value="${currentProduto.getId()}"/>"/>
                        <input type="hidden" name="estado" value="<c:out value="${currentProduto.getNomeEstado()}"/>"/>

                        <div class="form-group group-label">
                            <div class="col-sm-12">
                                <label for="nome" class="col-sm-6 control-label">Nome</label>
                                <label for="login" class="col-sm-6 control-label">Categoria</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="text" name="nome" class="form-control" value="<c:out value="${currentProduto.getNome()}"> </c:out>" required="">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="text" readonly="readonly" name="categoria" class="form-control" value="<c:out value="${currentProduto.getCategoria()}"> </c:out>" required="">
                                </div>
                            </div>
                        </div>

                        <div class="form-group group-label">
                            <div class="col-sm-12">
                                <label for="descricao" class="col-sm-12 control-label">Descrição</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <textarea class="form-control" name="descricao"><c:out value="${currentProduto.getDescricao()}"></c:out></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group group-label">
                            <div class="col-sm-12">
                                <label for="quantidade" class="col-sm-3 control-label">Quantidade disponível</label>
                                <label for="preco" class="col-sm-3 control-label">Preço</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="number" name="quantidade" class="form-control" value="<c:out value="${currentProduto.getQuantidade()}"> </c:out>" required="">
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="input-group">
                                    <input type="number" step="any" name="preco" class="form-control" value="<c:out value="${currentProduto.getPreco()}"> </c:out>" required="">
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
                            <button onclick="UF.Empresa.AdicionarProdutosCombo(this)" class="btn btn-success" type="button"><i class="fa fa-plus"></i> Adicionar ao Combo</button>
                        </div>
                        <table id="produto-grid-adicionar"class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="table-th-check"><input type="checkbox"  class="i-checks" id="produto-grid-adicionar-check-all" name="check-grid"></th>
                                        <th>Nome</th>
                                        <th>Categoria</th>
                                        <th>Restaurante</th>
                                        <th>Preço</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listProdutosNotCombo}" var="produto">
                                    <tr>
                                        <td><input type="checkbox"  class="i-checks" data-id="<c:out value="${produto.getId()}" />" name="check-grid"></td>
                                        <td><c:out value="${produto.getNome()}" /></td>
                                        <td><c:out value="${produto.getCategoria()}" /></td>
                                        <td><c:out value="${produto.getQuantidade()}" /></td>
                                        <td><c:out value="${produto.getPreco()}" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="ibox-content">
                        <div class="row toolbar-crud-grid">
                            <button onclick="UF.Empresa.RemoverProdutosCombo(this)" class="btn btn-success" type="button"><i class="fa fa-plus"></i> Remover do Combo</button>
                        </div>
                        <table id="produto-grid-remover"class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th class="table-th-check"><input type="checkbox"  class="i-checks" id="produto-grid-remover-check-all" name="check-grid2"></th>
                                        <th>Nome</th>
                                        <th>Categoria</th>
                                        <th>Restaurante</th>
                                        <th>Preço</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listProdutosCombo}" var="produto">
                                    <tr>
                                        <td><input type="checkbox"  class="i-checks" data-id="<c:out value="${produto.getId()}" />" name="check-grid2"></td>
                                        <td><c:out value="${produto.getNome()}" /></td>
                                        <td><c:out value="${produto.getCategoria()}" /></td>
                                        <td><c:out value="${produto.getQuantidade()}" /></td>
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
</div>
<%@ include file="../_shared/footer.jsp" %>
