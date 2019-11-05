<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../_shared/header.jsp" %>
<div class="wrapper wrapper-content animated fadeIn">
    <div class="row">
        <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Endereços </h5>
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
                            
                            <a href="FrontController?action=FormNovoEndereco"> <button type="button" class="btn btn-success "><i class="fa fa-plus"></i>Adicionar</button></a>
                            
                            <button onclick="UF.Endereco.EditarEndereco(this)" class="btn btn-primary" type="button"><i class="fa fa-edit"></i> Editar</button>
                            <button onclick="UF.Endereco.ExcluirEndereco(this)" class="btn btn-danger" type="button"><i class="fa fa-trash"></i> Excluir</button> 
                            <button onclick="UF.Endereco.TornarPrincipal(this)" class="btn btn-warning" type="button"><i class="fa fa-star"></i> Tornar principal</button> 
                        </div>
                        <table id="endereco-grid-resumo"class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="table-th-check"><input type="checkbox"  class="i-checks" id="endereco-grid-resumo-check-all" name="check-grid"></th>
                                <th>Logradouro</th>
                                <th>Nº</th>
                                <th>Bairro</th>
                                <th>Principal</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listEnderecos}" var="endereco">
                                <tr>
                                    <td><input type="checkbox"  class="i-checks" data-id="<c:out value="${endereco.getId()}" />" name="check-grid"></td>
                                    <td><c:out value="${endereco.getLogradouroExibicao()}" /></td>
                                    <td><c:out value="${endereco.getNumero()}" /></td>
                                    <td><c:out value="${endereco.getBairro()}" /></td>                                    
                                    <c:choose>
                                        <c:when test="${endereco.isPrincipal()}">
                                            <td><i class="color-green fa fa-check"></i></td>
                                        </c:when>    
                                        <c:otherwise>
                                            <td><i class="color-red fa fa-times"></i></td>
                                        </c:otherwise>
                                    </c:choose>
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
            
            UF.Helpers.SetCheckAllGrid('endereco-grid-resumo');
        });
    </script>