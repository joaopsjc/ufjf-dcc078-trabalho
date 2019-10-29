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
                            <input type="hidden" name="action" value="NovoProduto"/>
                                
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
                                        <input type="text" name="categoria" class="form-control" value="<c:out value="${currentProduto.getCategoria()}"> </c:out>" required="">
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
                                        <textarea class="form-control" name="descricao">
                                            <c:out value="${currentProduto.getDescricao()}"/>
                                        </textarea>
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
                </div>
            </div>
    </div>
</div>
<%@ include file="../_shared/footer.jsp" %>
