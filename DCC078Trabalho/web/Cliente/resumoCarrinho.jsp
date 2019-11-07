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
                        <button onclick="UF.Carrinho.RemoverDoCarrinho(this)" class="btn btn-danger" type="button"><i class="fa fa-trash"></i> Remover do carrinho</button>
                        <button onclick="UF.Carrinho.RealizarPedido(this)" class="btn btn-primary" type="button"><i class="fa fa-check"></i> Realizar pedido</button>
                    </div>
                    <table id="carrinho-grid-resumo"class="table table-bordered">
                        <thead>
                            <tr>
                                <th class="table-th-check"><input type="checkbox"  class="i-checks" id="carrinho-grid-resumo-check-all" name="check-grid"></th>
                                <th>Nome</th>
                                <th>Categoria</th>
                                <th>Restaurante</th>
                                <th>Preço</th>
                                <th>Quantidade</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${carrinho.produtos}" var="produto">
                                <tr>
                                    <td><input type="checkbox"  class="i-checks" data-id="<c:out value="${produto.getProduto().getId()}" />" name="check-grid"></td>
                                    <td><c:out value="${produto.getProduto().getNome()}" /></td>
                                    <td><c:out value="${produto.getProduto().getCategoria()}" /></td>
                                    <td><c:out value="${produto.getProduto().getNomeEmpresa()}" /></td>
                                    <td><c:out value="${produto.getProduto().getPreco()}" /></td>
                                    <td><input type="number" min="1" value="<c:out value="${produto.getQuantidade()}" />" name="quantidade-produto-<c:out value="${produto.getProduto().getId()}" />"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="form-group">
                        <label class="font-noraml">Multi select</label>
                        <div class="input-group">
                            <select data-placeholder="Escolha uma Promoção..." class="chosen-select" multiple="" style="width: 350px; display: none;" tabindex="-1">
                                <option value="">Select</option>
                                <option value="Natal">Promoção de Natal</option>
                                <option value="DiaMaes">Promoção de Dia das Mães</option>
                            </select><div class="chosen-container chosen-container-multi" style="width: 350px;" title=""><ul class="chosen-choices"><li class="search-field"><input type="text" value="Choose a Country..." class="default" autocomplete="off" style="width: 140px;" tabindex="4"></li></ul><div class="chosen-drop"><ul class="chosen-results"><li class="active-result" data-option-array-index="0" style="">Select</li><li class="active-result" data-option-array-index="251" style="">Promoção de Natal</li><li class="active-result" data-option-array-index="251" style="">Promoção de Dia das Mães</li></ul></div></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../_shared/footer.jsp" %>

<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });

        UF.Helpers.SetCheckAllGrid('carrinho-grid-resumo');
    });
</script>