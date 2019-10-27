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