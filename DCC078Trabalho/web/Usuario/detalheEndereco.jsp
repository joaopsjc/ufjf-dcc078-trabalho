<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../_shared/header.jsp" %>
<div class="wrapper wrapper-content animated fadeIn">
    <div class="row">
        <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Endereço <c:out value="${currentEndereco.getId()}"/> </h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form method="post" class="form-horizontal" action="FrontController">
                            <input type="hidden" name="action" value="SaveEndereco"/>
                            <input type="hidden" name="id" value="<c:out value="${currentEndereco.getId()}"/>"/>
                                
                            <div class="form-group group-label">
                                <div class="col-sm-12">
                                    <label for="logradouro" class="col-sm-offset-4 col-sm-6 control-label">Logradouro</label>
                                    <label for="numero" class="col-sm-2 control-label">Número</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <select name="tipoEndereco" class="form-control">
                                            <option 
                                                <c:if test = "${currentEndereco.getTipo().equals('Rua')}">
                                                    selected
                                                </c:if> value="Rua">
                                            Rua
                                            </option>
                                            <option 
                                                <c:if test = "${currentEndereco.getTipo().equals('Avenida')}">
                                                    selected
                                                </c:if> value="Avenida">
                                            Avenida
                                            </option>
                                            <option 
                                                <c:if test = "${currentEndereco.getTipo().equals('Fazenda')}">
                                                    selected
                                                </c:if> value="Fazenda">
                                            Fazenda
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="input-group">
                                        <input type="text" name="logradouro" class="form-control" value="<c:out value="${currentEndereco.getLogradouro()}"> </c:out>" required="">
                                    </div>
                                </div>
                                <div class="col-sm-2">
                                    <div class="input-group">
                                        <input type="text" name="numero" class="form-control" value="<c:out value="${currentEndereco.getNumero()}"> </c:out>" required="">
                                    </div>
                                </div>
                            </div>
                                    
                            <div class="form-group group-label">
                                <div class="col-sm-12">
                                    <label for="complemento" class=" col-sm-4 control-label">Complemento</label>
                                    <label for="bairro" class="col-sm-4 control-label">Bairro</label>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" name="complemento" class="form-control" value="<c:out value="${currentEndereco.getComplemento()}"> </c:out>" >
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" name="bairro" class="form-control" value="<c:out value="${currentEndereco.getBairro()}"> </c:out>" required="">
                                    </div>
                                </div>
                            </div> 
                            <div class="form-group group-label">
                                <div class="col-sm-12">
                                    <label for="cidade" class=" col-sm-4 control-label">Cidade</label>
                                    <label for="estado" class="col-sm-4 control-label">Estado</label>
                                    <label for="cep" class="col-sm-4 control-label">CEP</label>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" name="cidade" class="form-control" value="<c:out value="${currentEndereco.getCidade()}" > </c:out>" required="">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" name="estado" class="form-control" value="<c:out value="${currentEndereco.getEstado()}"> </c:out>" required="">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input type="text" name="cep" class="form-control" value="<c:out value="${currentEndereco.getCep()}"> </c:out>" required="">
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
