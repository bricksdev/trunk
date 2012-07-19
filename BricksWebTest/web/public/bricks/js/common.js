/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function __selectAll(selectID, checked){
    var selectInputs = document.getElementsByName(selectID);

    for(var i = 0; i < selectInputs.length; i++){
        selectInputs[i].checked = checked;
    }
}

function __showCalendar(elementObj){

    displayCalendar(elementObj,"yyyy-mm-dd",elementObj,false);
}

function __showTime(elementObj){

    displayCalendar(elementObj,"yyyy-mm-dd hh:ii",elementObj,true);
}

function selectedElement(curObj){
    var options = curObj.options;
    for(var i = 0; i< options.length; i++){
        options[i].selected = true;
    }
}

function checkInputRequired(){
    var bool = true;
    var inputs = document.getElementsByTagName("input");
    for(var idx = 0; idx < inputs.length; idx++){
        if(inputs[idx].type.toLowerCase() == "text" && inputs[idx].getAttribute("ifrequired") == "true" && inputs[idx].value == "" ){
            inputs[idx].style.backgroundColor = "red";
            document.getElementById("errorDiv").innerHTML = inputs[idx].title + " is required.";
            bool = false;
        }else {
            if( inputs[idx].style.backgroundColor ==  "red"){
                inputs[idx].style.backgroundColor = "";
            }
        }
    }

    return bool;
}

function addedTableTr(tableId, isCopy){
    var tableObject = $(tableId);
    var oTr = tableObject.rows[tableObject.rows.length - 1];
    var checkedTr = $("selectAll")?true:false
    var newTr = oTr.cloneNode(true);
    tableObject.getElementsByTagName("tbody")[0].appendChild(newTr);
    // 设定当前是否清空复制的内容
    if(!isCopy){
        var childrens = newTr.children;
        for(var idx1 = 0; idx1 < childrens.length; idx1++){
            if(childrens[idx1].children[0].tagName.toLowerCase() == "input" && !(childrens[idx1].children[0].type.toLowerCase() == "checkbox" || childrens[idx1].children[0].type.toLowerCase() == "radio")){
                childrens[idx1].children[0].value = "";
            }
        }

    }
    // 如果存在checked项即设定checked的value
    if(checkedTr){
        newTr.cells[0].firstChild.value = newTr.rowIndex;
    }
}

function deleteTableTr(tableId){
    var tableObject = $(tableId);
    var checkedTr = $("selectAll")?true:false
    var childrens = null;
    for(var idx = 0; idx < tableObject.rows.length; idx++){
        if(idx == 0){
            childrens = tableObject.rows[idx].children;
            for(var idx1 = 0; idx1 < childrens.length; idx1++){
                if(childrens[idx1].children[0].tagName.toLowerCase() == "input" && !(childrens[idx1].children[0].type.toLowerCase() == "checkbox" || childrens[idx1].children[0].type.toLowerCase() == "radio")){
                    childrens[idx1].children[0].value = "";
                }
            }
        }else{
            tableObject.deleteRow(idx);
        }
    }
}


function currentSubmit(btn){
    if(checkInputRequired()){
        $("__METHOD").value = btn.id;
        btn.form.submit();
    }
}

function selectedStyle(value){
    var options = $("__style").options;
    for(var idx = 0; idx < options.length; idx++){
        if(options[idx].value == value){
            options[idx].selected = true;
        }
    }
}