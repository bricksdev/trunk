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
    var isChecked = document.getElementsByName("selectInput");
    var len = isChecked.length;
    for(var i=len-1;i>=0;i--){
        if(isChecked[i].checked==true){
            //tableObject.lastChild.appendChild(isChecked[i].parentNode.parentNode);
            tableObject.lastChild.appendChild(tableObject.rows[isChecked[i].parentNode.parentNode.rowIndex].cloneNode(true));
            //如果是添加清空数据
            if(!isCopy){
                for(var j=1;j<tableObject.rows[tableObject.rows.length - 1].children.length;j++){
                    tableObject.rows[tableObject.rows.length - 1].children[j].childNodes[1].value="";
                }
            }
            //去除复制的checkbox选中
            tableObject.rows[tableObject.rows.length - 1].childNodes[1].childNodes[1].checked=false;
            //ID加1
            var idval = tableObject.rows[tableObject.rows.length-2].childNodes[1].childNodes[1].value;
            tableObject.rows[tableObject.rows.length - 1].childNodes[1].childNodes[1].value=parseInt(idval)+1;
        }
    }
    //修改样式
    //alert(tableObject.rows.length);
    for(var i=1;i<tableObject.rows.length;i++){
        if(i%2==1){
            tableObject.rows[i].className="newTr";
        }
    }
}

function deleteTableTr(tableId){
    var tableObject = $(tableId);
    var isChecked = document.getElementsByName("selectInput");
    var len = isChecked.length;
    for(var i=len-1;i>=0;i--){
        if(isChecked[i].checked==true){
            //删除前保证有一行数据
            if(tableObject.rows.length>1){
                tableObject.lastChild.removeChild(isChecked[i].parentNode.parentNode);
            }
            else{
                for(var j=1;j<tableObject.rows[tableObject.rows.length - 1].children.length;j++){
                    tableObject.rows[tableObject.rows.length - 1].children[j].childNodes[1].value="";
                }
            }
        }
    }
}


