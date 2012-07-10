/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function $1(id){
    return document.getElementById(id);
}

/*全选列表*/
function selectAllBox(name,checkHead){
    var objs = document.getElementsByTagName("input");
    for (var i = 0; i < objs.length; i ++){
        if(objs[i].type == "checkbox" && objs[i].name==name)
            objs[i].checked =  checkHead.checked;
    }
}
function getSelectedBox(name){
    var boxs = new Array();
    var objs = document.getElementsByTagName("input");
    for (var i = 0; i < objs.length; i ++){
        if(objs[i].name==name && objs[i].type == "checkbox" && objs[i].checked)
            boxs.push(objs[i]);
    }
    return boxs;
}
/*改变标签页*/
function changeTab(allId,currentId,key){
    ids = allId.split(",");
    for(var i=0;i<ids.length;i++){
        $1("div" + ids[i]).style.display="none";
        $1("tab" + ids[i]).className="";
    }
    $1("div" + currentId).style.display="block";
    $1("tab" + currentId).className="current";
    //$1("divSysError").style.display = "none";
    //$1("divSysInfo").style.display = "none";
    $1(key+"tab").value = currentId;
    inputs = $1("div" + currentId).getElementsByTagName("input");
    for(var i=0;i<inputs.length;i++){
        if(inputs[i].type =="text"){
            inputs[i].focus();
            return;
        }
    }
}
function scanOpen(url){
    window.showModalDialog(url+"&"+Math.random(), window, "dialogTop:10px;dialogWidth:220px;dialogHeight:220px;status:no;");
//window.showModalDialog(url, window);
}
/*取URL参数*/
function getUrlParam(name){
    url = location.href;
    index1 = url.indexOf(name + "=");
    index2 = -1;
    if(index1 != -1){
        index2 = url.indexOf("&",index1+1);
        if(index2 == -1)
            index2 = url.indexOf("#",index1+1);
    }else{
        return "";
    }
    index1 = index1 + name.length + 1;
    if(index2 == -1)
        return url.substr(index1);
    else
        return url.substr(index1,index2-index1);
}
/*function setFocus(){
  inputs = document.getElementsByTagName("input");
  for(var i=0;i<inputs.length;i++){
      if(inputs[i].type =="text"){
          inputs[i].focus();
          return;
      }
  }
}
if(document.addEventListener){
   window.addEventListener('load',setFocus,false);
}else{
   window.attachEvent("onload",setFocus);
}*/