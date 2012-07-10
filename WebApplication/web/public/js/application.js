/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*全选列表*/
function selectAllBox(name,checkHead){
    var objs = document.getElementsByTagName("input");
    for (var i = 0; i < objs.length; i ++){
        if(objs[i].type == "checkbox" && objs[i].name==name)
            objs[i].checked =  checkHead.checked;
    }
}

/*自己以外的checkbox全取消选择*/
function cancelRestBox(name,checkHead){
    var objs = document.getElementsByTagName("input");
    var checked = checkHead.checked;
    for (var i = 0; i < objs.length; i ++){        
        if(objs[i].type == "checkbox" && objs[i].name==name)
            objs[i].checked =  false;
    }    
    checkHead.checked = checked;
}

function showErrList(errList){
    var msgList = errList.split(",");
    var msg = "详细：\n";
    for(var i = 0;i < msgList.length ; i++){
        msg = msg + "    "+ msgList[i]+"\n";
    }
    alert(msg);
}

//切换标签
function changeTab(allId,currentId,paramid){
    ids = allId.split(",");    
    for(var i=0;i<ids.length;i++){
        document.getElementById("div" + ids[i]).style.display="none";
        document.getElementById("tab" + ids[i]).className="";
    }
    document.getElementById("div" + currentId).style.display="block";
    document.getElementById("tab" + currentId).className="current";
    if(paramid != undefined){
        document.getElementById(paramid+"tab").value = currentId;
    }
    
    //$1("divSysError").style.display = "none";
    //$1("divSysInfo").style.display = "none";

}
function scanOpen(url){
    window.showModalDialog(url+"&"+Math.random(), window, "dialogTop:10px;dialogWidth:220px;dialogHeight:220px;status:no;");
    //window.showModalDialog(url, window);
}

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
function setFocus(){
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
}
