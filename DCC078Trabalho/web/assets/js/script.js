﻿UFJFFood = new Object();
UF = UFJFFood;

UF.RegisterNamespace = function (className,parentClass) {
    var classNameSplit = className.split('.');
    var currentObject = parentClass || UF;
    classNameSplit.forEach(function (clsName, clsIndex) {
        if (currentObject[clsName] == null)
             currentObject[clsName] = new Object();
        currentObject = currentObject[clsName];
    });
}


UF.RegisterNamespace('Helpers');

UF.Helpers.ObjectArrayIndexOf = function (arr, value, properties) {
    if (typeof properties == "string")
        properties = [properties];

    for (var i = 0, len = arr.length; i < len; i++) {
        var currentItem = arr[i];
        properties.forEach(function (property) {
            if (currentItem != null)
                currentItem = currentItem[property];
            if (currentItem == null)
                return false;
        });
        if (currentItem === value)
            return i;
    }
    return -1;
}

UF.Helpers.CloneObject = function (objectA, cloneChilds) {
    cloneChilds = cloneChilds == false ? false : true;
    var result = {};
    for (var att in objectA) {
        var item = objectA[att];
        if (item != null && cloneChilds && Array.isArray(item))
            result[att] = UF.Helpers.CloneArray(item)
        else if (item != null && cloneChilds && typeof item == "object")
            result[att] = UF.Helpers.CloneObject(item)
        else
            result[att] = item;
    }
    return result;
}

UF.Helpers.CloneArray = function (arrayA, cloneChilds) {
    cloneChilds = cloneChilds == false ? false : true;
    var result = [];
    arrayA.forEach(function (item, itemIndex) {
        if (item != null && cloneChilds && Array.isArray(item))
            result[itemIndex] = UF.Helpers.CloneArray(item)
        else if (item != null && cloneChilds && typeof item == "object")
            result[itemIndex] = UF.Helpers.CloneObject(item)
        else
            result[itemIndex] = item;
    });
    return result;
}

UF.Helpers.MergeObject = function (objectA, objectB) {
    var result = UF.Helpers.CloneObject(objectA);
    for (var att in objectB) {
        if (objectA[att] != null && typeof objectA[att] == 'object' && !Array.isArray(objectA[att])) {
            result[att] = UF.Helpers.MergeObject(result[att], objectB[att]);
        }
        else
            result[att] = objectB[att];
    }
    return result;
}

UF.Helpers.ReplaceAll = function (target, search, replacement) {
    if (target.indexOf(search) >= 0)
        return target.replace(new RegExp(search, 'g'), replacement);
    else
        return target;
}

UF.Helpers.GetSelectedIdsFromGrid = function(grid){
    if (typeof(grid)=="string")
        grid = $("#"+grid);
    var result = [];
    grid.find('input[type=checkbox][data-id]:checked').each(function(i,el){
        el = $(el);
        result.push(el.attr('data-id'))
    });
    return result;
}

UF.Helpers.SetCheckAllGrid = function(grid){
    if (typeof(grid)=="string")
        grid = $("#"+grid);
    var gridId = '#'+grid.attr('id');
    var check = $(gridId+'-check-all');
    check.on('ifUnchecked', function(event){
        $(gridId).find('td .i-checks').iCheck('uncheck');
    });
    check.on('ifChecked', function(event){
        $(gridId).find('td .i-checks').iCheck('check');
    });
}

UF.Helpers.TryParseJson = function(str){
    try {
        return JSON.parse(str);
    } catch (e) {
        return false;
    }
}

UF.RegisterNamespace('Alert');

UF.Alert.SweetAlertConfigDefault = {
    confirmButtonText: "Sim",
    cancelButtonText: "Não",
    confirmButtonColor: '#16987e',
    cancelButtonColor: '#aaa'
}

UF.Alert.ShowMessage = function(params){
    var config = {
        html: params.message,
        allowOutsideClick: false,
        showConfirmButton: true,
        type:params.type,
        showCancelButton: params.showCancelButton,
        onConfirm: params.onConfirm || function(){},
        onClose: params.onClose || function(){}
    };
    if (params.confirmButtonText)
        config.confirmButtonText = params.confirmButtonText;
    UF.Alert.SweetAlert(config);
}


UF.Alert.Success = function(params){
    params.type="success";
    params.showCancelButton = false;
    params.confirmButtonText =  "Ok";
    UF.Alert.ShowMessage(params)
}

UF.Alert.Error = function(params){
    params.type="error";
    params.showCancelButton = false;
    params.confirmButtonText =  "Ok";
    UF.Alert.ShowMessage(params)
}

UF.Alert.ShowLoading = function(){
    var config = {
        title: "Carregando...",
        html:   '<div class="clice-loader-contents"><svg class="circle-loader progress"><circle cx="20" cy="20" r="15"></svg></div>',
        allowOutsideClick: false,
        showConfirmButton: false,
        showCancelButton: false
    };
    UF.Alert.SweetAlert(config);
}

UF.Alert.CloseLoading = function(){
    window.swal.close();
}

UF.Alert.SweetAlert = function(params){
    var sweetAlertConfig = UF.Helpers.MergeObject(UF.Alert.SweetAlertConfigDefault,params);
    
    var onConfirm = function(){
        if (typeof(params.onConfirm)=="function")
            params.onConfirm();
    }
    var onClose = function(){
        if (typeof(params.onClose)=="function")
            params.onClose();
    }
    
    return window.swal(sweetAlertConfig).then(onConfirm, onClose);
}

UF.ChangeMaskCPFCPNPJ = function(element, isCpnj){
    if(isCpnj){
        var mask = '99.999.999/9999-99';
        var placeholder = '__.___.___/____-__';
    }
    else{
        var mask = '999.999.999-99';
        var placeholder = '___.___.___-__';
    }
    element.val('');
    element.unmask();
    element.mask(mask,{placeholder:placeholder});
}

UF.SetMaskToInputs = function(){
    $('[data-mask]').each(function(i,el){
        el = $(el);
        var mask = el.attr('data-mask');
        var placeholder = UF.Helpers.ReplaceAll(mask,'9','_');
        el.mask(mask,{placeholder:placeholder});
    })
}

UF.SetMaskToInputs();

UF.RegisterNamespace("Produto");

UF.Produto.EditarProduto = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("produto-grid-resumo");
    if (selectedIds.length!=1){
        UF.Alert.Error({message:"É necessário selecionar um produto!"});
        return;
    }
    var form = $('#form-detalhe-produto');
    form.find('[name=id]').val(selectedIds[0]);
    form.submit();
    
}

UF.Produto.ExcluirProduto = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("produto-grid-resumo");
    if (selectedIds.length==0){
        UF.Alert.Error({message:"É necessário selecionar ao menos um produto!"});
        return;
    }
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=ExcluirProduto",	
	type : 'post',	
	data : {
            selectedIds: selectedIds.join(',')
	},	
	complete  : function(response){	
            UF.Alert.CloseLoading();
            if (response.responseText=="")
                UF.Alert.Success({message:"Produto(s) excluído(s) com sucesso",
                    onConfirm: function(){
                        window.location = "FrontController?action=ResumoProdutos";
                    }});
            else
                UF.Alert.Error({message:response.responseText});
            
	}	
    });   
}

UF.Produto.BloquearDesbloquearProduto = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("produto-grid-resumo");
    if (selectedIds.length==0){
        UF.Alert.Error({message:"É necessário selecionar ao menos um produto!"});
        return;
    }
    var isBloquear = element.getAttribute('data-type')=="Bloquear";
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=BloquearDesbloquearProduto",	
	type : 'post',	
	data : {
            selectedIds: selectedIds.join(','),
            isBloquear: isBloquear
	},	
	complete : function(response){	
            UF.Alert.CloseLoading();
            if (response.responseText=="")
                UF.Alert.Success({message:"Produto(s) "+(isBloquear? "bloqueado(s)" : "desbloqueado(s)") + " com sucesso",
                    onConfirm: function(){
                        window.location = "FrontController?action=ResumoProdutos";
                    }});
            else
                UF.Alert.Error({message:response.responseText});
            
	}	
    }); 
    
}

UF.RegisterNamespace("Cliente");

UF.Cliente.AdicionarProdutosCarrinho = function(){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("produto-grid-resumo");
    if (selectedIds.length==0){
        UF.Alert.Error({message:"É necessário selecionar ao menos um produto!"});
        return;
    }
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=AdicionarProdutosCarrinho",	
	type : 'post',	
	data : {
            selectedIds: selectedIds.join(',')
	},	
	complete  : function(response){	
            UF.Alert.CloseLoading();
            var responseJson = UF.Helpers.TryParseJson(response.responseText);
            if (responseJson){
                UF.Alert.Success({message:"Produto(s) adicionados ao carrinho com sucesso"});
                $('#link-carrinho span').html(responseJson.qtdItensCarrinho);                
            }else{
                UF.Alert.Error({message:response.responseText}); 
            }
	}	
    });
}

UF.RegisterNamespace("Carrinho");

UF.Carrinho.RealizarPedido = function(element){
    
    var qtdItens = [];
    $('#carrinho-grid-resumo input[type=number]').each(function(i,el){
        qtdItens.push($(el).val());
    });
    qtdItens.join();   
    
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=RealizarPedidoCarrinho",	
	type : 'post',
	data : {
            qtdItens: qtdItens.join(',')
	},	
	complete  : function(response){	
            UF.Alert.CloseLoading();
            if (response.responseText.length==0){
                UF.Alert.Success({message:"Pedido realizado",
                    onConfirm: function(){
                        window.location = "FrontController?action=Home";
                    }});   
            }else{
                UF.Alert.Error({message:response.responseText}); 
            }
	}	
    });    
}

UF.Carrinho.RemoverDoCarrinho = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("carrinho-grid-resumo");
    if (selectedIds.length==0){
        UF.Alert.Error({message:"É necessário selecionar ao menos um produto!"});
        return;
    }
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=ExcluirProdutosCarrinho",	
	type : 'post',	
	data : {
            selectedIds: selectedIds.join(',')
	},	
	complete  : function(response){	
            UF.Alert.CloseLoading();
            var responseJson = UF.Helpers.TryParseJson(response.responseText);
            if (responseJson){
                UF.Alert.Success({message:"Produto(s) removido(s) do carrinho com sucesso",
                    onConfirm: function(){
                        window.location = "FrontController?action=ResumoCarrinho";
                    }});
                $('#link-carrinho span').html(responseJson.qtdItensCarrinho);                
            }else{
                UF.Alert.Error({message:response.responseText}); 
            }            
	}	
    });   
}

UF.RegisterNamespace("Empresa");

UF.Empresa.VisualizarProdutos = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("empresa-grid-resumo");
    if (selectedIds.length!=1){
        UF.Alert.Error({message:"É necessário selecionar uma empresa!"});
        return;
    }
    var form = $('#form-visualizar-produtos');
    form.find('[name=empresaId]').val(selectedIds[0]);
    form.submit();
}

UF.Empresa.AceitarPedido = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("pedidos-pendentes-grid-resumo");
    if (selectedIds.length==0){
        UF.Alert.Error({message:"É necessário selecionar ao menos um pedido!"});
        return;
    }
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=IniciarPreparoPedidos",	
	type : 'post',	
	data : {
            selectedIds: selectedIds.join(',')
	},	
	complete  : function(response){	
            UF.Alert.CloseLoading();
            if (response.responseText=="")
                UF.Alert.Success({message:"Preparo do(s) pedido(s) iniciado(s)",
                    onConfirm: function(){
                        window.location = "FrontController?action=Home";
                    }});
            else
                UF.Alert.Error({message:response.responseText});
            
	}	
    });   
}

UF.Empresa.FinalizarPedido = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("pedidos-finalizados-grid-resumo");
    if (selectedIds.length==0){
        UF.Alert.Error({message:"É necessário selecionar ao menos um pedido!"});
        return;
    }
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=FinalizarPedidosEmpresaAction",	
	type : 'post',	
	data : {
            selectedIds: selectedIds.join(',')
	},	
	complete  : function(response){	
            UF.Alert.CloseLoading();
            if (response.responseText=="")
                UF.Alert.Success({message:"Pedido(s) marcados(s) como finalizado(s)",
                    onConfirm: function(){
                        window.location = "FrontController?action=Home";
                    }});
            else
                UF.Alert.Error({message:response.responseText});
            
	}	
    });   
}


UF.Empresa.RejeitarPedido = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("pedidos-pendentes-grid-resumo");
    if (selectedIds.length==0){
        UF.Alert.Error({message:"É necessário selecionar ao menos um pedido!"});
        return;
    }
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=CancelarPedidos",
	type : 'post',	
	data : {
            selectedIds: selectedIds.join(',')
	},	
	complete  : function(response){	
            UF.Alert.CloseLoading();
            if (response.responseText=="")
                UF.Alert.Success({message:"Pedido(s) cancelado(s)!",
                    onConfirm: function(){
                        window.location = "FrontController?action=Home";
                    }});
            else
                UF.Alert.Error({message:response.responseText});
            
	}	
    });   
}

UF.Empresa.AdicionarProdutosCombo = function(){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("produto-grid-resumo");
    if (selectedIds.length==0){
        UF.Alert.Error({message:"É necessário selecionar ao menos um produto!"});
        return;
    }
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=AdicionarProdutosCombo",	
	type : 'post',	
	data : {
            selectedIds: selectedIds.join(','),
            id_combo: $('[name=id]').val()
	},	
	complete  : function(response){	
            UF.Alert.CloseLoading();
            var responseJson = UF.Helpers.TryParseJson(response.responseText);
            if (responseJson){
                UF.Alert.Success({message:"Produto(s) adicionados ao combo com sucesso"});
                $('#link-carrinho span').html(responseJson.qtdItensCarrinho);                
            }else{
                UF.Alert.Error({message:response.responseText}); 
            }
	}	
    });
}

UF.RegisterNamespace("Pedido");

UF.Pedido.AceitarPedido = function()
{
    window.location = "FrontController?action=AceitarPedido";
}
UF.Pedido.RejeitarPedido = function()
{
    window.location = "FrontController?action=RejeitarPedido";
}

UF.RegisterNamespace("Endereco");

UF.Endereco.EditarEndereco = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("endereco-grid-resumo");
    if (selectedIds.length!=1){
        UF.Alert.Error({message:"É necessário selecionar um endereco!"});
        return;
    }
    var form = $('#form-detalhe-endereco');
    form.find('[name=id]').val(selectedIds[0]);
    form.submit();    
}

UF.Endereco.ExcluirEndereco = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("endereco-grid-resumo");
    if (selectedIds.length==0){
        UF.Alert.Error({message:"É necessário selecionar ao menos um endereco!"});
        return;
    }
    var principalSelected = $('#endereco-grid-resumo').find('input[type=checkbox][data-id]:checked').parents('tr').find('i[class="color-green fa fa-check"]').length ==1;
    
    if (principalSelected){
        UF.Alert.Error({message:"Não é possível excluir um endereço principal. Defina um novo principal e tente novamente!"});
        return;
    }
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=ExcluirEndereco",	
	type : 'post',	
	data : {
            selectedIds: selectedIds.join(',')
	},	
	complete:function(response){	
            UF.Alert.CloseLoading();
            if (response.responseText=="")
                UF.Alert.Success({message:"Endereco(s) excluído(s) com sucesso",
                    onConfirm: function(){
                        window.location = "FrontController?action=ResumoEnderecos";
                    }});
            else
                UF.Alert.Error({message:response.responseText});            
	}	
    });   
}


UF.Endereco.TornarPrincipal = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("endereco-grid-resumo");
    if (selectedIds.length!=1){
        UF.Alert.Error({message:"É necessário selecionar um endereco!"});
        return;
    }
    
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=TornarEnderecoPrincipal",	
	type : 'post',	
	data : {
            selectedId: selectedIds[0]
	},	
	complete  : function(response){	
            UF.Alert.CloseLoading();
            if (response.responseText=="")
                UF.Alert.Success({message:"Endereco principal selecionado",
                    onConfirm: function(){
                        window.location = "FrontController?action=ResumoEnderecos";
                    }});
            else
                UF.Alert.Error({message:response.responseText});
            
	}	
    });   
}


UF.RegisterNamespace("Entregador")

UF.Entregador.AceitarPedido = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("pedidos-pendentes-grid-resumo");
    if (selectedIds.length==0){
        UF.Alert.Error({message:"É necessário selecionar ao menos um pedido!"});
        return;
    }
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=AceitarEntregaPedido",	
	type : 'post',	
	data : {
            selectedIds: selectedIds.join(',')
	},	
	complete  : function(response){	
            UF.Alert.CloseLoading();
            if (response.responseText=="")
                UF.Alert.Success({message:"Pedido(s) aceito(s) com sucesso",
                    onConfirm: function(){
                        window.location = "FrontController?action=ListaPedidosPendentesEntregador";
                    }});
            else
                UF.Alert.Error({message:response.responseText});
            
	}	
    });   
}

UF.Entregador.RejeitarPedido = function(element){
    var selectedIds = UF.Helpers.GetSelectedIdsFromGrid("pedidos-pendentes-grid-resumo");
    if (selectedIds.length==0){
        UF.Alert.Error({message:"É necessário selecionar ao menos um pedido!"});
        return;
    }
    UF.Alert.ShowLoading();
    $.ajax({
	url : "FrontController?action=DispensarEntregaPedido",
	type : 'post',	
	data : {
            selectedIds: selectedIds.join(',')
	},	
	complete  : function(response){	
            UF.Alert.CloseLoading();
            if (response.responseText=="")
                UF.Alert.Success({message:"Pedido(s) rejeitado(s) com sucesso!",
                    onConfirm: function(){
                        window.location = "FrontController?action=ListaPedidosPendentesEntregador";
                    }});
            else
                UF.Alert.Error({message:response.responseText});
            
	}	
    });   
}