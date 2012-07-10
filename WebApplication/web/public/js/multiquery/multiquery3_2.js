/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var Multi_Key=null;
//  var MaxRowCount = 10000;
var MaxRowCount = 3000;
var firstInit = false;
//    var rowcountInfo = {
//        single:200,
//        notsingle:200,
//        range:200,
//        exrange:200
//    }
  
var rowcountInfo = {
    single:10,
    baksingle:10,
    notsingle:10,
    baknotsingle:10,
    range:10,
    bakrange:10,
    exrange:10,
    bakexrange:10
}
var cursorInfo = {
    single:1,
    baksingle:1,
    notsingle:1,
    baknotsingle:1,
    bakrange:1,
    bakexrange:1,
    range:1,
    exrange:1
}
function initRecordInfo(){
    rowcountInfo = {
        single:10,
        baksingle:10,
        notsingle:10,
        baknotsingle:10,
        range:10,
        bakrange:10,
        exrange:10,
        bakexrange:10
    }
    cursorInfo = {
        single:1,
        baksingle:1,
        notsingle:1,
        baknotsingle:1,
        bakrange:1,
        bakexrange:1,
        range:1,
        exrange:1
    }
}
function initRecordInfoByKey(key){

    var bakkey ="bak"+key;
    rowcountInfo[key] =10;
    rowcountInfo[bakkey] =10;
    cursorInfo[key] =1;
    cursorInfo[bakkey] =1;
   
}
function  cleanMultiDialog(){
    var multiDialog="multiDialog";
    var id1=multiDialog+"_c";
    var q=   YAHOO.util.Selector.query('#'+id1+' #'+multiDialog+'')

    if(q!=null && q!=undefined && q!="undefined" && q!=""){
        var tdom=document.getElementById(id1);
        tdom.parentNode.removeChild(tdom);
    }
}
var divMultiDialog = null;
function initDivMulti() {
    //590px

    cleanMultiDialog();

    divMultiDialog = new YAHOO.widget.Dialog("divMultiDialog",
    {
        width : "500px",
        fixedcenter : true,
        visible : false,
        modal:false,
        iframe:false,
        zindex:59,
        underlay:"shadow",
        close:false,
        constraintoviewport : true
    });
    divMultiDialog.render(document.body);
}

YAHOO.util.Event.onDOMReady(initDivMulti);
//显示YUI对话框

var multibd_tmp=null;
function setCursorInfo(type,num){
    if(type=="range" || type=="exrange"){
        if(cursorInfo[type]<num&& document.getElementById(type+"from"+num).value!=""&&num<MaxRowCount){
            var tempfrom =  document.getElementById(type+"from"+num).value;
            var tempto =  document.getElementById(type+"to"+num).value;
            document.getElementById(type+"from"+num).value="";
            document.getElementById(type+"to"+num).value="";
            document.getElementById(type+"from"+cursorInfo[type]).value=tempfrom;
            document.getElementById(type+"to"+cursorInfo[type]).value=tempto;
            cursorInfo[type]=cursorInfo[type]+1;
            setFocus(Multi_Key)
        }else if(cursorInfo[type]==num && document.getElementById(type+"from"+num).value!="" &&num<MaxRowCount){
            cursorInfo[type]=cursorInfo[type]+1;
        //    var tabvalue = document.getElementById(Multi_Key+"tab").value;
        //    addTableSingleRow(tabvalue, rowcountInfo[type]+1)
        }
    } else{
        if(cursorInfo[type]<num&& document.getElementById(type+num).value!=""&&num<MaxRowCount){
     
            var temp =  document.getElementById(type+num).value;
            document.getElementById(type+num).value="";
            document.getElementById(type+cursorInfo[type]).value=temp;
            cursorInfo[type]=cursorInfo[type]+1;
            setFocus(Multi_Key)
       
        
        }else if(cursorInfo[type]==num && document.getElementById(type+num).value!=""&&num<MaxRowCount){
            cursorInfo[type]=cursorInfo[type]+1;
          //  var tabvalue = document.getElementById(Multi_Key+"tab").value;
         //   addTableSingleRow(tabvalue, rowcountInfo[type]+1)
        }
    }
    if(((cursorInfo[type])==rowcountInfo[type] &&(cursorInfo[type]+1<MaxRowCount))){//||(cursorInfo.single+1==rowcountInfo.single)
        var tabvalue = document.getElementById(Multi_Key+"tab").value;
        addTableSingleRow(tabvalue, rowcountInfo[type]+1)
    }
}


function setTable1(){
    var theTable=document.getElementById('singletable');
    var rows=[];
    for(var i=1;i<=rowcountInfo.single;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" onchange=\"setCursorInfo('single','"+i+"');\" id=\"single"+i+"\" name=\"single\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div1').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='singletable'>"+theTable.innerHTML+rows.join("")+"</table>";
    var theTable=document.getElementById('notsingletable');
    var rows=[];
    for(var i=1;i<=rowcountInfo.notsingle;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" id=\"notsingle"+i+"\" onchange=\"setCursorInfo('notsingle','"+i+"');\" name=\"notsingle\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div3').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='notsingletable'>"+theTable.innerHTML+rows.join("")+"</table>";
    var theTable=document.getElementById('varrangetable');
    var rows=[];
    for(var i=1;i<=rowcountInfo.range;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" id=\"rangefrom"+i+"\" name=\"rangefrom\" style=\"width: 160px;\"/></td>");
        rows.push("<td><input type=\"text\" id=\"rangeto"+i+"\" onchange=\"setCursorInfo('range','"+i+"');\" name=\"rangeto\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div2').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='varrangetable'>"+theTable.innerHTML+rows.join("")+"</table>";
    var theTable=document.getElementById('exrangetable');
    var rows=[];
    for(var i=1;i<=rowcountInfo.exrange;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" id=\"exrangefrom"+i+"\" name=\"exrangefrom\" style=\"width: 160px;\"/></td>");
        rows.push("<td><input type=\"text\" id=\"exrangeto"+i+"\" onchange=\"setCursorInfo('exrange','"+i+"');\" name=\"exrangeto\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div4').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='exrangetable'>"+theTable.innerHTML+rows.join("")+"</table>";
}

function configClick(key){
    Multi_Key=key;
}

function showDivDialog(url,title,key){
    var handleSuccess=function(o){
        document.getElementById("divMultibd").innerHTML=o.responseText;
        document.getElementById("divMultiDialog").style.display="";
        setTable1();
        multibd_tmp= document.getElementById("divMultibd").innerHTML;
        init(key);
        getAllCount();
        configClick(key);
        divMultiDialog.show();
        setFocus(key);
        document.getElementById("divDialogTitle").innerHTML=title;
    }
    var handleFailure=function(o){
        var err = "Transaction id: " + o.tId + " ";
        err += "HTTP status: " + o.status + " ";
        err += "Status code message: " + o.statusText + " ";
        document.getElementById("multibd").innerHTML=err;
        document.getElementById("multiDialog").style.display="";
        divMultiDialog.show();
    }

    if(multibd_tmp==null){
        var sUrl=url;//请求得到标题头
        var from=document.getElementById('queryform');
        req(handleSuccess,handleFailure,sUrl,from);
    }else{
        document.getElementById("divDialogTitle").innerHTML=title;
        //   resetValue();
        initTable();
        init(key);
        getAllCount();
        configClick(key);
        divMultiDialog.show();
        setFocus(key);
    }
}
function setFocus(key){
    var tabvalue = document.getElementById(key+"tab").value;
    if(tabvalue==""||tabvalue=="1"){
        //删除单一值
        var singles = document.getElementsByName("single");
        //        for(i = 0;i < singles.length;i++){
        //            singles[i].focus();
        //            return;
        //        }
        singles[cursorInfo.single-1].focus();
        return;
    }else if(tabvalue=="2"){
        //name="range" 删除值
        var rangefroms = document.getElementsByName("rangefrom");
        //        for(i = 0;i < rangefroms.length;i++){
        //            rangefroms[i].focus();
        //            return;
        //        }
        rangefroms[cursorInfo.range-1].focus();      
    //        var rangetos = document.getElementsByName("rangeto");
    //        for(i = 0;i < rangetos.length;i++){
    //            rangetos[i].value = "";
    //        }

    }else if(tabvalue=="3"){
        //删除排除单一值
        var notsingles = document.getElementsByName("notsingle");
        notsingles[cursorInfo.single-1].focus();    
    //        for(i = 0;i < notsingles.length;i++){
    //            notsingles[i].focus();
    //            return;
    //        }
    }else if(tabvalue=="4"){
        var exrangefroms = document.getElementsByName("exrangefrom");
        exrangefroms[cursorInfo.exrange-1].focus();    
    //        for(i = 0;i < exrangefroms.length;i++){
    //            exrangefroms[i].focus();
    //            return;
    //        }

    //        var exrangetos = document.getElementsByName("exrangeto");
    //        for(i = 0;i < exrangetos.length;i++){
    //            exrangetos[i].value = "";
    //        }
    }
}
function hideDivDialog(){
    cursorInfo.single=cursorInfo.baksingle;
    cursorInfo.range=cursorInfo.bakrange;
    cursorInfo.exrange=cursorInfo.bakexrange;
    cursorInfo.notsingle=cursorInfo.baknotsingle;
    rowcountInfo.single=rowcountInfo.baksingle;
    rowcountInfo.notsingle=rowcountInfo.baknotsingle;
    rowcountInfo.range=rowcountInfo.bakrange;
    rowcountInfo.exrange=rowcountInfo.bakexrange;
    divMultiDialog.hide();
}

function setBodyCss()
{
    YAHOO.util.Dom.addClass(document.getElementsByTagName("body")[0],"yui-skin-sam");
}
YAHOO.util.Event.onDOMReady(setBodyCss);

function req(handleSuccess,handleFailure,sUrl,form){

    YAHOO.example.container.wait =
    new YAHOO.widget.Panel("wait",
    {
        width: "240px",
        fixedcenter: true,
        close: false,
        draggable: false,
        zindex:4,
        modal: false,
        visible: false
    }
    );
    YAHOO.example.container.wait.setHeader("Loading, please wait...");
    YAHOO.example.container.wait.setBody("<img src=\""+webserver+"/public/images/rel_interstitial_loading.gif\"/>");
    YAHOO.example.container.wait.render(document.body);
    YAHOO.example.container.wait.show();

    var handleSuccess_tp=function(o){
        try{

            handleSuccess(o);
        }catch(e){
            alert(e);
        }
        YAHOO.example.container.wait.hide();
    }
    var handleFailure_tp=function(o){
        try{
            handleFailure(o);
        }catch(e){
            alert(e);
        }
        YAHOO.example.container.wait.hide();
    }
    var callback =
    {
        success:handleSuccess_tp,
        failure:handleFailure_tp,
        argument:['foo','bar']
    };

    if(js$util.lang.isNotEmpty(form)){

        YAHOO.util.Connect.setForm(form);
    }
    YAHOO.util.Connect.asyncRequest('POST', sUrl, callback);
}

function copyValue(key){
    cursorInfo.baksingle=cursorInfo.single;
    cursorInfo.bakrange=cursorInfo.range;
    cursorInfo.bakexrange=cursorInfo.exrange;
    cursorInfo.baknotsingle=cursorInfo.notsingle;
    rowcountInfo.baksingle=rowcountInfo.single;
    rowcountInfo.baknotsingle=rowcountInfo.notsingle;
    rowcountInfo.bakrange=rowcountInfo.range;
    rowcountInfo.bakexrange=rowcountInfo.exrange;
    //单一值
    var singles = document.getElementsByName("single");
    var a = "";
    for(i = 0;i < singles.length;i++){
        if(a == ""){
            if(singles[i].value.match(/[\S]+/)){
                a = singles[i].value;
            }
        }else if(singles[i].value.match(/[\S]+/)){
            a = a+ ";"+ singles[i].value;
        }
    }

    //排除单一值
    var notsingles = document.getElementsByName("notsingle");
    var b = "";
    for(i = 0;i < notsingles.length;i++){
        if(b == ""){
            if(notsingles[i].value.match(/[\S]+/)){
                b = notsingles[i].value;
            }
        }else if(notsingles[i].value.match(/[\S]+/)){
            b = b+ ";"+ notsingles[i].value;
        }
    }


    //范围值
    var c = new Array();
    for(i =1 ; i <= document.getElementsByName("rangefrom").length ;i++){
        var from = document.getElementById("rangefrom"+i).value;
        var to = document.getElementById("rangeto"+i).value;
        if(from.match(/[\S]+/) && to.match(/[\S]+/)){
            c.push({
                from:from,
                to:to
            });
        }
    }
    var d = new Array();
    for(i =1 ; i <= document.getElementsByName("exrangefrom").length ;i++){
        var from = document.getElementById("exrangefrom"+i).value;
        var to = document.getElementById("exrangeto"+i).value;
        if(from.match(/[\S]+/) && to.match(/[\S]+/)){
            d.push({
                from:from,
                to:to
            });
        }
    }
    //生成json
    var tmpjson = new Object();
    var exsinglekey = key +"_exsingle";
    var rangekey = key+"_range";
    var exrangekey = key+"_exrange";
    var singlekey = key+"_single";
    tmpjson[exsinglekey]=b;
    tmpjson[singlekey]=a;
    tmpjson[rangekey]=c;
    tmpjson[exrangekey]=d;
    //window.returnValue = tmpjson.toJSONString();
    //window.close();
    document.getElementById("returnValue").value=js$util.json.toString(tmpjson);
    changeParentValue(key);
    hideDivDialog(key);
}
function resetValue(){
    /* //删除单一值
    var singles = document.getElementsByName("single");
    for(i = 0;i < singles.length;i++){
        singles[i].value = "";
    }
    //删除排除单一值
    var notsingles = document.getElementsByName("notsingle");
    for(i = 0;i < notsingles.length;i++){
        notsingles[i].value = "";
    }
    //name="range" 删除值
    var rangefroms = document.getElementsByName("rangefrom");
    for(i = 0;i < rangefroms.length;i++){
        rangefroms[i].value = "";
    }
    var exrangefroms = document.getElementsByName("exrangefrom");
    for(i = 0;i < exrangefroms.length;i++){
        exrangefroms[i].value = "";
    }
    var rangetos = document.getElementsByName("rangeto");
    for(i = 0;i < rangetos.length;i++){
        rangetos[i].value = "";
    }
    var exrangetos = document.getElementsByName("exrangeto");
    for(i = 0;i < exrangetos.length;i++){
        exrangetos[i].value = "";
    }*/
      
    initRecordInfoByKey("single");
    //删除单一值
    var theTable=document.getElementById('singletable');
    ocument.getElementById('div1').innerHTML="";
    var rows=[];
    for(var i=1;i<=rowcountInfo.single;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" onchange=\"setCursorInfo('single','"+i+"');\" id=\"single"+i+"\" name=\"single\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div1').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='singletable'>"+rows.join("")+"</table>";
   
    initRecordInfoByKey("range");
    //name="range" 删除值
    var theTable=document.getElementById('varrangetable');
    document.getElementById('div2').innerHTML="";
    var rows=[];
    for(var i=1;i<=rowcountInfo.range;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" id=\"rangefrom"+i+"\" name=\"rangefrom\" style=\"width: 160px;\"/></td>");
        rows.push("<td><input type=\"text\" id=\"rangeto"+i+"\" onchange=\"setCursorInfo('range','"+i+"');\" name=\"rangeto\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div2').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='varrangetable'>"+rows.join("")+"</table>";

   
    initRecordInfoByKey("notsingle");
    //删除排除单一值
    var theTable=document.getElementById('notsingletable');
    document.getElementById('div3').innerHTML="";
    var rows=[];
    for(var i=1;i<=rowcountInfo.notsingle;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" id=\"notsingle"+i+"\" onchange=\"setCursorInfo('notsingle','"+i+"');\" name=\"notsingle\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div3').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='notsingletable'>"+rows.join("")+"</table>";
  
    initRecordInfoByKey("exrange");
    var theTable=document.getElementById('exrangetable');
    document.getElementById('div4').innerHTML="";
    var rows=[];
    for(var i=1;i<=rowcountInfo.exrange;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" id=\"exrangefrom"+i+"\" name=\"exrangefrom\" style=\"width: 160px;\"/></td>");
        rows.push("<td><input type=\"text\" id=\"exrangeto"+i+"\" onchange=\"setCursorInfo('exrange','"+i+"');\" name=\"exrangeto\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div4').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='exrangetable'>"+rows.join("")+"</table>";
    
    getAllCount();
    configClick(Multi_Key);
    setFocus(Multi_Key);
}

function initTable(){
    /* //删除单一值
    var singles = document.getElementsByName("single");
    for(i = 0;i < singles.length;i++){
        singles[i].value = "";
    }
    //删除排除单一值
    var notsingles = document.getElementsByName("notsingle");
    for(i = 0;i < notsingles.length;i++){
        notsingles[i].value = "";
    }
    //name="range" 删除值
    var rangefroms = document.getElementsByName("rangefrom");
    for(i = 0;i < rangefroms.length;i++){
        rangefroms[i].value = "";
    }
    var exrangefroms = document.getElementsByName("exrangefrom");
    for(i = 0;i < exrangefroms.length;i++){
        exrangefroms[i].value = "";
    }
    var rangetos = document.getElementsByName("rangeto");
    for(i = 0;i < rangetos.length;i++){
        rangetos[i].value = "";
    }
    var exrangetos = document.getElementsByName("exrangeto");
    for(i = 0;i < exrangetos.length;i++){
        exrangetos[i].value = "";
    }*/
      
    // initRecordInfoByKey("single");
    //删除单一值
    var theTable=document.getElementById('singletable');
    document.getElementById('div1').innerHTML="";
    var rows=[];
    for(var i=1;i<=rowcountInfo.baksingle;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" onchange=\"setCursorInfo('single','"+i+"');\" id=\"single"+i+"\" name=\"single\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div1').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='singletable'>"+rows.join("")+"</table>";
   
    //initRecordInfoByKey("range");
    //name="range" 删除值
    var theTable=document.getElementById('varrangetable');
   document.getElementById('div2').innerHTML="";
    var rows=[];
    for(var i=1;i<=rowcountInfo.bakrange;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" id=\"rangefrom"+i+"\" name=\"rangefrom\" style=\"width: 160px;\"/></td>");
        rows.push("<td><input type=\"text\" id=\"rangeto"+i+"\" onchange=\"setCursorInfo('range','"+i+"');\" name=\"rangeto\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div2').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='varrangetable'>"+rows.join("")+"</table>";

   
    //initRecordInfoByKey("notsingle");
    //删除排除单一值
    var theTable=document.getElementById('notsingletable');
    document.getElementById('div3').innerHTML="";
    var rows=[];
    for(var i=1;i<=rowcountInfo.baknotsingle;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" id=\"notsingle"+i+"\" onchange=\"setCursorInfo('notsingle','"+i+"');\" name=\"notsingle\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div3').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='notsingletable'>"+rows.join("")+"</table>";
  
    //  initRecordInfoByKey("exrange");
    var theTable=document.getElementById('exrangetable');
    document.getElementById('div4').innerHTML="";
    var rows=[];
    for(var i=1;i<=rowcountInfo.bakexrange;i++){
        rows.push("<tr>");
        rows.push("<td><input type=\"text\" id=\"exrangefrom"+i+"\" name=\"exrangefrom\" style=\"width: 160px;\"/></td>");
        rows.push("<td><input type=\"text\" id=\"exrangeto"+i+"\" onchange=\"setCursorInfo('exrange','"+i+"');\" name=\"exrangeto\" style=\"width: 160px;\"/></td>");
        rows.push("</tr>"); 
    }
    document.getElementById('div4').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='exrangetable'>"+rows.join("")+"</table>";
    
//    getAllCount();
//    configClick(Multi_Key);
//    setFocus(Multi_Key);
}
function init(key){
    //   var dialogArguments = ;//.jsonValue.value;

    //if(typeof dialogArguments == 'undefined'){
    //   return;
    //  }
    var jsonObj = document.getElementById(key+"New");
    var From = document.getElementById(key+"From");
    var To = document.getElementById(key+"To");
    //    var jsonObj = dialogArguments["form1"][key+"New"];
    //    var From = dialogArguments.form1[key+"From"];
    //    var To = dialogArguments.form1[key+"To"];
    if(typeof jsonObj != 'undefined' && jsonObj.value == ''){
        if(typeof From != 'undefined' && typeof To != 'undefined'){
            if(From.value.match(/[\S]+/)){
                if(!To.value.match(/[\S]+/)){
                    document.getElementById('single1').value = From.value;
                }else{
                    document.getElementById("rangefrom1").value = From.value;
                    document.getElementById("rangeto1").value = To.value;
                }
            }
        }
        return;
    }
    //初始化
    if(typeof jsonObj != 'undefined' && jsonObj.value != '' && jsonObj.value!='undefined'){
        ret = "var obj=" + decodeURIComponent(jsonObj.value);
        // 生成json对象
        eval(ret);
        //选择单一值
        if(typeof obj[key+"_single"] != "undefined"){
            var a = obj[key+'_single'].split(';');
            for(i=0 ;i< a.length;i++){
                var objId = 'single'+(i+1);
                //                if(i > 199){
                //                    var v = document.getElementById('single200').value;
                //                    document.getElementById('single200').value = v+ ';'+a[i];
                //                }else{
                document.getElementById(objId).value = a[i];
            //                }
            }
            if(typeof To != 'undefined' && To.value==""){
                document.getElementById('single1').value = From.value;
            }
        }
        //排除单一值
        if(typeof obj[key+"_exsingle"] != "undefined"){
            var b = obj[key+'_exsingle'].split(';');
            for(i=0 ;i< b.length;i++){
                var objId = 'notsingle'+(i+1);
                //                if(i > 199){
                //                    var v = document.getElementById('notsingle200').value;
                //                    document.getElementById('notsingle200').value = v+ ';'+b[i];
                //                }else{
                document.getElementById(objId).value = b[i];
            //                }
            }
        }
        //选择范围
        if(typeof obj[key+"_range"] != "undefined"){
            var c = obj[key+'_range'];
            for(i =0 ;i < c.length; i++){
                //                if(i > 199) return;
                var arr = c[i];
                if(typeof arr == "undefined")
                    return;
                document.getElementById('rangefrom'+ (i+1)).value = arr['from'];
                document.getElementById('rangeto'+ (i+1)).value = arr['to'];
            }
            if(typeof From != 'undefined' && typeof To != 'undefined'&& From.value!="" && To.value!=""){
                document.getElementById("rangefrom1").value = From.value;
                document.getElementById("rangeto1").value = To.value;
            }
        }

        //排除范围
        if(typeof obj[key+"_exrange"] != "undefined"){
            var d = obj[key+'_exrange'];
            for(i =0 ;i < c.length; i++){
                //                if(i > 199) return;
                var arr = d[i];
                if(typeof arr == "undefined")
                    return;
                document.getElementById('exrangefrom'+ (i+1)).value = arr['from'];
                document.getElementById('exrangeto'+ (i+1)).value = arr['to'];
            }
        }
    }

}
function getAllCount(){
    getCount("single",tlg1);
    getCount("notsingle",tlg3);
    getCount("rangefrom",tlg2);
    getCount("exrangefrom",tlg4);
}
function getCount(key,lgtext){
    var values = document.getElementsByName(key);
    var count =0;
    for(i = 0;i < values.length;i++){
        if(values[i].value != ""){
            count++;
        }
    }
  
    document.getElementById(key+"count").innerHTML = lgtext+ "("+count+")";
}
function resetTabValue(key){
    var tabvalue = document.getElementById(key+"tab").value;
    if(tabvalue==""||tabvalue=="1"){
        initRecordInfoByKey("single");
        //删除单一值
        var theTable=document.getElementById('singletable');
        document.getElementById('div1').innerHTML="";
        var rows=[];
        for(var i=1;i<=rowcountInfo.single;i++){
            rows.push("<tr>");
            rows.push("<td><input type=\"text\" onchange=\"setCursorInfo('single','"+i+"');\" id=\"single"+i+"\" name=\"single\" style=\"width: 160px;\"/></td>");
            rows.push("</tr>"); 
        }
        document.getElementById('div1').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='singletable'>"+rows.join("")+"</table>";
    }else if(tabvalue=="2"){
        initRecordInfoByKey("range");
        //name="range" 删除值
        var theTable=document.getElementById('varrangetable');
        document.getElementById('div2').innerHTML="";
        var rows=[];
        for(var i=1;i<=rowcountInfo.range;i++){
            rows.push("<tr>");
            rows.push("<td><input type=\"text\" id=\"rangefrom"+i+"\" name=\"rangefrom\" style=\"width: 160px;\"/></td>");
            rows.push("<td><input type=\"text\" id=\"rangeto"+i+"\" onchange=\"setCursorInfo('range','"+i+"');\" name=\"rangeto\" style=\"width: 160px;\"/></td>");
            rows.push("</tr>"); 
        }
        document.getElementById('div2').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='varrangetable'>"+rows.join("")+"</table>";

    }else if(tabvalue=="3"){
        initRecordInfoByKey("notsingle");
        //删除排除单一值
        var theTable=document.getElementById('notsingletable');
       document.getElementById('div3').innerHTML="";
        var rows=[];
        for(var i=1;i<=rowcountInfo.notsingle;i++){
            rows.push("<tr>");
            rows.push("<td><input type=\"text\" id=\"notsingle"+i+"\" onchange=\"setCursorInfo('notsingle','"+i+"');\" name=\"notsingle\" style=\"width: 160px;\"/></td>");
            rows.push("</tr>"); 
        }
        document.getElementById('div3').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='notsingletable'>"+rows.join("")+"</table>";
    }else if(tabvalue=="4"){
        initRecordInfoByKey("exrange");
        var theTable=document.getElementById('exrangetable');
       document.getElementById('div4').innerHTML="";
        var rows=[];
        for(var i=1;i<=rowcountInfo.exrange;i++){
            rows.push("<tr>");
            rows.push("<td><input type=\"text\" id=\"exrangefrom"+i+"\" name=\"exrangefrom\" style=\"width: 160px;\"/></td>");
            rows.push("<td><input type=\"text\" id=\"exrangeto"+i+"\" onchange=\"setCursorInfo('exrange','"+i+"');\" name=\"exrangeto\" style=\"width: 160px;\"/></td>");
            rows.push("</tr>"); 
        }
        document.getElementById('div4').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='exrangetable'>"+rows.join("")+"</table>";
    }
    getAllCount();
    configClick(key);
    setFocus(key);
}
function getClipboard() {
    if (window.clipboardData) {
        return(window.clipboardData.getData('Text'));
    }
    else if (window.netscape) {
        netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if (!clip) return;
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if (!trans) return;
        trans.addDataFlavor('text/unicode');
        clip.getData(trans,clip.kGlobalClipboard);
        var str = new Object();
        var len = new Object();
        try {
            trans.getTransferData('text/unicode',str,len);
        }
        catch(error) {
            return null;
        }
        if (str) {
            if (Components.interfaces.nsISupportsWString) str=str.value.QueryInterface(Components.interfaces.nsISupportsWString);
            else if (Components.interfaces.nsISupportsString) str=str.value.QueryInterface(Components.interfaces.nsISupportsString);
            else str = null;
        }
        if (str) {
            return(str.data.substring(0,len.value / 2));
        }
    }
    return null;
}
function addTableRow(tabvalue,newRowCount){
    if(typeof newRowCount!= "undefined"&&newRowCount!=null){
        if(newRowCount>MaxRowCount){
            newRowCount= MaxRowCount;
        }
    
        if(tabvalue==""||tabvalue=="1"){
            //单一值
            var theTable=document.getElementById('singletable');
            var rows=[];
            for(var i=rowcountInfo.single;i<=newRowCount;i++){
                rows.push("<tr>");
                rows.push("<td><input type=\"text\" id=\"single"+i+"\" onchange=\"setCursorInfo('single','"+i+"');\" name=\"single\" style=\"width: 160px;\"/></td>");
                rows.push("</tr>"); 
                cursorInfo.single= cursorInfo.single+1;
            }
            // theTable.innerHTML = theTable.innerHTML+rows.join("");
            document.getElementById('div1').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='singletable'>"+theTable.innerHTML+rows.join("")+"</table>";

            rowcountInfo.single=newRowCount;
        }else if(tabvalue=="2"){
            //范围值
            var theTable=document.getElementById('varrangetable');
            var rows=[];
            for(var i=rowcountInfo.range;i<=newRowCount;i++){
                rows.push("<tr>");
                rows.push("<td><input type=\"text\" id=\"rangefrom"+i+"\" name=\"rangefrom\" style=\"width: 160px;\"/></td>");
                rows.push("<td><input type=\"text\" id=\"rangeto"+i+"\" onchange=\"setCursorInfo('range','"+i+"');\" name=\"rangeto\" style=\"width: 160px;\"/></td>");
                rows.push("</tr>");
                cursorInfo.range=cursorInfo.range+1;
            }
            //            theTable.innerHTML = theTable.innerHTML+rows.join("");
            document.getElementById('div2').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='varrangetable'>"+theTable.innerHTML+rows.join("")+"</table>";
            
            rowcountInfo.range=newRowCount;
        }else if(tabvalue=="3"){
            //排除单一值
            
            var theTable=document.getElementById('notsingletable');
            var rows=[];
            for(var i=rowcountInfo.notsingle;i<=newRowCount;i++){
                rows.push("<tr>");
                rows.push("<td><input type=\"text\" id=\"notsingle"+i+"\" onchange=\"setCursorInfo('notsingle','"+i+"');\" name=\"notsingle\" style=\"width: 160px;\"/></td>");
                rows.push("</tr>"); 
                cursorInfo.notsingle= cursorInfo.notsingle+1;
            }
            //            theTable.innerHTML = theTable.innerHTML+rows.join("");
            document.getElementById('div3').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='notsingletable'>"+theTable.innerHTML+rows.join("")+"</table>";
   
            rowcountInfo.notsingle=newRowCount;
        }else if(tabvalue=="4"){
            //排除范围值
            var theTable=document.getElementById('exrangetable');
            var rows=[];
            for(var i=rowcountInfo.exrange;i<=newRowCount;i++){
                rows.push("<tr>");
                rows.push("<td><input type=\"text\" id=\"exrangefrom"+i+"\" name=\"exrangefrom\" style=\"width: 160px;\"/></td>");
                rows.push("<td><input type=\"text\" id=\"exrangeto"+i+"\" onchange=\"setCursorInfo('exrange','"+i+"');\" name=\"exrangeto\" style=\"width: 160px;\"/></td>");
                rows.push("</tr>"); 
                cursorInfo.exrange=cursorInfo.exrange+1;
            }
            //            theTable.innerHTML = theTable.innerHTML+rows.join("");
            document.getElementById('div4').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='exrangetable'>"+theTable.innerHTML+rows.join("")+"</table>";
            rowcountInfo.exrange=newRowCount;
        }
    }else{
        return;
    }
}

function addTableSingleRow(tabvalue,newRowCount){
    if(typeof newRowCount!= "undefined"&&newRowCount!=null){
        if(newRowCount>MaxRowCount){
            newRowCount= MaxRowCount;
        }
    
        if(tabvalue==""||tabvalue=="1"){
            //单一值
            var theTable=document.getElementById('singletable');
            var rowCount = theTable.rows.length; //获得当前表格的行数
            var tmtr = theTable.insertRow(rowCount);//在tale里动态的增加tr
            var tmtd=document.createElement("td");
            tmtd.innerHTML='<input type="text" onchange=\"setCursorInfo(\'single\','+(rowCount)+');\" id="single'+(rowCount)+'" name="single" style="width: 160px;"/>'
            tmtr.appendChild(tmtd);
            
            
            /*   
            var rows=[];
            for(var i=rowcountInfo.single+1;i<=newRowCount;i++){
                rows.push("<tr>");
                rows.push("<td><input type=\"text\" id=\"single"+i+"\" name=\"single\" style=\"width: 160px;\"/></td>");
                rows.push("</tr>"); 
            }*/
            // theTable.innerHTML = theTable.innerHTML+rows.join("");
            //       document.getElementById('div1').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='singletable'>"+theTable.innerHTML+rows.join("")+"</table>";

            rowcountInfo.single=newRowCount;
        }else if(tabvalue=="2"){
            //范围值
            var theTable=document.getElementById('varrangetable');
            var rowCount = theTable.rows.length; //获得当前表格的行数
            var tmtr = theTable.insertRow(rowCount);//在tale里动态的增加tr


            var tmtd=document.createElement("td");
            tmtd.innerHTML='<input type="text" id="rangefrom'+rowCount+'" name="rangefrom" style="width: 160px;"/>'

            var tmtd1=document.createElement("td");
            tmtd1.innerHTML='<input type="text" id="rangeto'+rowCount+'" onchange=\"setCursorInfo(\'range\','+(rowCount)+');\" name="rangeto" style="width: 160px;"/>'
            tmtr.appendChild(tmtd);
            tmtr.appendChild(tmtd1);
            /*  
            var rows=[];
            for(var i=rowcountInfo.range+1;i<=newRowCount;i++){
                rows.push("<tr>");
                rows.push("<td><input type=\"text\" id=\"rangefrom"+i+"\" name=\"rangefrom\" style=\"width: 160px;\"/></td>");
                rows.push("<td><input type=\"text\" id=\"rangeto"+i+"\" name=\"rangeto\" style=\"width: 160px;\"/></td>");
                rows.push("</tr>"); 
            }*/
            //            theTable.innerHTML = theTable.innerHTML+rows.join("");
            //   document.getElementById('div2').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='varrangetable'>"+theTable.innerHTML+rows.join("")+"</table>";
    
            rowcountInfo.range=newRowCount;
        }else if(tabvalue=="3"){
            //排除单一值
            var theTable=document.getElementById('notsingletable');
            var rowCount = theTable.rows.length; //获得当前表格的行数
            var tmtr = theTable.insertRow(rowCount);//在tale里动态的增加tr
            var tmtd=document.createElement("td");
            tmtd.innerHTML='<input type="text" id="notsingle'+rowCount+'" onchange=\"setCursorInfo(\'notsingle\','+(rowCount)+');\" name="notsingle" style="width: 160px;"/>'
            tmtr.appendChild(tmtd);
            
            /*
            var rows=[];
            for(var i=rowcountInfo.notsingle+1;i<=newRowCount;i++){
                rows.push("<tr>");
                rows.push("<td><input type=\"text\" id=\"notsingle"+i+"\" name=\"notsingle\" style=\"width: 160px;\"/></td>");
                rows.push("</tr>"); 
            }*/
            //            theTable.innerHTML = theTable.innerHTML+rows.join("");
            //document.getElementById('div3').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='notsingletable'>"+theTable.innerHTML+rows.join("")+"</table>";
   
            rowcountInfo.notsingle=newRowCount;
        }else if(tabvalue=="4"){
            var theTable=document.getElementById('exrangetable');
            var rowCount = theTable.rows.length; //获得当前表格的行数
            var tmtr = theTable.insertRow(rowCount);//在tale里动态的增加tr


            var tmtd=document.createElement("td");
            tmtd.innerHTML='<input type="text" id="exrangefrom'+rowCount+'" name="exrangefrom" style="width: 160px;"/>'

            var tmtd1=document.createElement("td");
            tmtd1.innerHTML='<input type="text" id="exrangeto'+rowCount+'" onchange=\"setCursorInfo(\'exrange\','+(rowCount)+');\" name="exrangeto" style="width: 160px;"/>'
            tmtr.appendChild(tmtd);
            tmtr.appendChild(tmtd1);
            //排除范围值
            /*
            var rows=[];
            for(var i=rowcountInfo.exrange+1;i<=newRowCount;i++){
                rows.push("<tr>");
                rows.push("<td><input type=\"text\" id=\"erfrom"+i+"\" name=\"exrangefrom\" style=\"width: 160px;\"/></td>");
                rows.push("<td><input type=\"text\" id=\"erto"+i+"\" name=\"exrangeto\" style=\"width: 160px;\"/></td>");
                rows.push("</tr>"); 
            }*/
            //            theTable.innerHTML = theTable.innerHTML+rows.join("");
            // document.getElementById('div4').innerHTML="<table border='0' cellpadding='0' cellspacing='0'   id='exrangetable'>"+theTable.innerHTML+rows.join("")+"</table>";
   
            rowcountInfo.exrange=newRowCount;
        }
    }else{
        return;
    }
}
function pasteData(key){
    //粘贴板数据
    var opData = null;
    try{
        opData  = getClipboard();
        
    }catch(e){
        alert('error:' + e);
    }
    if(opData == null || opData == ""){
        alert(_BYD_SRM_LG.hmyfzrhsj);
    }else{
        //行分割
        var opLineBreak = new RegExp("\\r\\n", "g");
        //单元格分割
        var opTab = new RegExp("\\t", "g");
        //行
        var opRows = null;
        var ffopLineBreak = new RegExp("\\n", "g");
        if (window.clipboardData) {
            opRows = opData.split(opLineBreak);
        }
        else if (window.netscape) {
            opRows = opData.split(ffopLineBreak);
        }
        var tabvalue = document.getElementById(key+"tab").value;
        addTableRow(tabvalue,opRows.length)
        if(tabvalue==""||tabvalue=="1"){
            //单一值
            var singles = document.getElementsByName("single");
            for(i = 0;i < singles.length;i++){
                if(typeof opRows[i]=='undefined'){
                    singles[i].value = "";
                }else{
                    singles[i].value = opRows[i].split(opTab)[0];
                }
            }
        }else if(tabvalue=="2"){
            //范围值
            var rangefroms = document.getElementsByName("rangefrom");
            var rangetos = document.getElementsByName("rangeto");
            for(i = 0;i < rangefroms.length;i++){
                if(typeof opRows[i]!='undefined'){
                    var temp =opRows[i].split(opTab);
                    if(typeof temp[0]!='undefined')
                        rangefroms[i].value = temp[0];
                    if(typeof temp[1]!='undefined')
                        rangetos[i].value = temp[1];
                }
            }
        }else if(tabvalue=="3"){
            //排除单一值
            var notsingles = document.getElementsByName("notsingle");
            for(i = 0;i < notsingles.length;i++){
                if(typeof opRows[i]=='undefined'){
                    notsingles[i].value = "";
                }else{
                    notsingles[i].value = opRows[i].split(opTab)[0];
                }
            }
        }else if(tabvalue=="4"){
            //排除范围值
            var exrangefroms = document.getElementsByName("exrangefrom");
            var exrangetos = document.getElementsByName("exrangeto");
            for(i = 0;i < exrangefroms.length;i++){
                if(typeof opRows[i]!='undefined'){
                    var temp =opRows[i].split(opTab);
                    if(typeof temp[0]!='undefined')
                        exrangefroms[i].value = temp[0];
                    if(typeof temp[1]!='undefined')
                        exrangetos[i].value = temp[1];
                }
            }
        }
    }


}
function changeParentValue(key){
    //var returnvalue =window.showModalDialog(url, this, 'dialogLeft='+(screen.width-400)/2+';dialogTop=50;dialogWidth=400px;dialogHeight=390px');
    var returnvalue =document.getElementById("returnValue").value;
    if(typeof returnvalue != 'undefined' && returnvalue!=""){
        //保存到json隐藏域
        document.getElementById(key).value = encodeURIComponent(returnvalue);
        document.getElementById(key+"New").value = encodeURIComponent(returnvalue);
        var From = document.getElementById(key+'From');
        var To = document.getElementById(key+'To');
        var FromOld = document.getElementById(key+'FromOld');
        var ToOld = document.getElementById(key+'ToOld');
        ret = "var obj=" + returnvalue;
        // 生成json对象
        eval(ret);
        //选择单一值
        if(typeof obj[key+"_single"] != "undefined"&& obj[key+"_single"]!=""){
            var a = obj[key+'_single'].split(';');
            if(a.length> 0){
                From.value = a[0];
                To.value = "";
                FromOld.value = a[0];
                ToOld.value = "";
            }else{
                From.value = "";
                To.value = "";
                FromOld.value = "";
                ToOld.value = "";
            }
        }else if(typeof obj[key+"_range"] != "undefined"){
            var c = obj[key+'_range'];
            if(c.length > 0) {
                var arr = c[0];
                if(typeof arr == "undefined")
                    return;
                From.value = arr['from'];
                To.value = arr['to'];
                FromOld.value = arr['from'];
                ToOld.value = arr['to'];
            }else{
                From.value = "";
                To.value = "";
                FromOld.value = "";
                ToOld.value = "";
            }
        }

    }else{
        var returnvalue = decodeURIComponent(document.getElementById(key).value);
        if(returnvalue!=""){
            var From = document.getElementById(key+'From');
            var To = document.getElementById(key+'To');
            var FromOld = document.getElementById(key+'FromOld');
            var ToOld = document.getElementById(key+'ToOld');
            ret = "var obj=" + returnvalue;
            // 生成json对象
            eval(ret);
            //选择单一值
            if(typeof obj[key+"_single"] != "undefined"&& obj[key+"_single"]!=""){
                var a = obj[key+'_single'].split(';');
                if(a.length> 0){
                    From.value = a[0];
                    To.value = "";
                    FromOld.value = a[0];
                    ToOld.value = "";
                }else{
                    From.value = "";
                    To.value = "";
                    FromOld.value = "";
                    ToOld.value = "";
                }
            }else if(typeof obj[key+"_range"] != "undefined"){
                var c = obj[key+'_range'];
                if(c.length > 0) {
                    var arr = c[0];
                    if(typeof arr == "undefined")
                        return;
                    From.value = arr['from'];
                    To.value = arr['to'];
                    FromOld.value = arr['from'];
                    ToOld.value = arr['to'];
                }else{
                    From.value = "";
                    To.value = "";
                    FromOld.value = "";
                    ToOld.value = "";
                }
            }
        }
    }
}
