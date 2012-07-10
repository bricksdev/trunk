/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function copyValue(type){
    var tmpjson = new Object();
    var exsinglekey = type +"_exsingle";
    var rangekey = type+"_range";
    var exrangekey = type+"_exrange";
    var singlekey = type+"_single";
    //单一值
    var singles = document.getElementsByName("single");
    if(singles != undefined && singles.length > 0){
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
        tmpjson[singlekey]=a;
    }

    //排除单一值
    var notsingles = document.getElementsByName("notsingle");
    if(notsingles != undefined && notsingles.length > 0){
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
        tmpjson[exsinglekey]=b;
    }


    //范围值
    var rangefroms = document.getElementsByName("rangefrom");
    if(rangefroms != undefined && rangefroms.length > 0){
        var c = new Array();
        for(i =1 ; i <= 200 ;i++){
            var from = document.getElementById("rangefrom"+i).value;
            var to = document.getElementById("rangeto"+i).value;
            if(from.match(/[\S]+/) && to.match(/[\S]+/)){
                c.push({
                    from:from,
                    to:to
                });
            }
        }
        tmpjson[rangekey]=c;
    }
    var exrangefroms = document.getElementsByName("exrangefrom");
    if(exrangefroms != undefined && exrangefroms.length > 0){
        var d = new Array();
        for(i =1 ; i <= 200 ;i++){
            var from = document.getElementById("erfrom"+i).value;
            var to = document.getElementById("erto"+i).value;
            if(from.match(/[\S]+/) && to.match(/[\S]+/)){
                d.push({
                    from:from,
                    to:to
                });
            }
        }
        tmpjson[exrangekey]=d;
    }
    
     /**var tmpjson = new Object();
    var exsinglekey = type +"_exsingle";
    var rangekey = type+"_range";
    var exrangekey = type+"_exrange";
    var singlekey = type+"_single";
    tmpjson[exsinglekey]=b;
    tmpjson[singlekey]=a;
    tmpjson[rangekey]=c;
    tmpjson[exrangekey]=d;*/

    document.getElementById("returnValue").value=tmpjson.toJSONString();    
    changeParentValue(type);
    parent.hideDialog(type);
}
function resetValue(){
    //删除单一值
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
    }
}
function init(type){
    //   var dialogArguments = ;//.jsonValue.value;

    //if(typeof dialogArguments == 'undefined'){
    //   return;
    //  }
    var jsonObj = parent.document.getElementById(type+"New");
    var From = parent.document.getElementById(type+"From");
    //    var jsonObj = dialogArguments["form1"][type+"New"];
    //    var From = dialogArguments.form1[type+"From"];
    //    var To = dialogArguments.form1[type+"To"];
    
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
        if(typeof obj[type+"_single"] != "undefined"){
            var a = obj[type+'_single'].split(';');
            for(i=0 ;i< a.length;i++){
                var objId = 'single'+(i+1);
                if(i > 999){
                    var v = document.getElementById('single200').value;
                    document.getElementById('single200').value = v+ ';'+a[i];
                }else{
                    document.getElementById(objId).value = a[i];
                }
            }
            if(typeof To != 'undefined' && To.value==""){
                document.getElementById('single1').value = From.value;
            }
        }
        //排除单一值
        if(typeof obj[type+"_exsingle"] != "undefined"){
            var b = obj[type+'_exsingle'].split(';');
            for(i=0 ;i< b.length;i++){
                var objId = 'notsingle'+(i+1);
                if(i > 199){
                    var v = document.getElementById('notsingle200').value;
                    document.getElementById('notsingle200').value = v+ ';'+b[i];
                }else{
                    document.getElementById(objId).value = b[i];
                }
            }
        }
        //选择范围
        if(typeof obj[type+"_range"] != "undefined"){
            var c = obj[type+'_range'];
            for(i =0 ;i < c.length; i++){
                if(i > 199) return;
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
        if(typeof obj[type+"_exrange"] != "undefined"){
            var d = obj[type+'_exrange'];
            for(i =0 ;i < c.length; i++){
                if(i > 199) return;
                var arr = d[i];
                if(typeof arr == "undefined")
                    return;
                document.getElementById('erfrom'+ (i+1)).value = arr['from'];
                document.getElementById('erto'+ (i+1)).value = arr['to'];
            }
        }
    }

}
function getAllCount(){
    getCount("single");
    getCount("notsingle");
    getCount("rangefrom");
    getCount("exrangefrom");
}
function getAllCount1(){
    getCount("single");
    getCount("rangefrom");
}
function getCount(type){
    var values = document.getElementsByName(type);    
    
    var count =0;
    for(i = 0;i < values.length;i++){
        if(values[i].value != ""){
            count++;
        }
    }
    var temp = document.getElementById(type+"count").innerHTML;
    document.getElementById(type+"count").innerHTML = temp+ "("+count+")";
}
function resetTabValue(type){
    var tabvalue = document.getElementById(type+"tab").value;
    if(tabvalue==""||tabvalue=="1"){
        //删除单一值
        var singles = document.getElementsByName("single");
        for(i = 0;i < singles.length;i++){
            singles[i].value = "";
        }
    }else if(tabvalue=="2"){
        //name="range" 删除值
        var rangefroms = document.getElementsByName("rangefrom");
        for(i = 0;i < rangefroms.length;i++){
            rangefroms[i].value = "";
        }
        var rangetos = document.getElementsByName("rangeto");
        for(i = 0;i < rangetos.length;i++){
            rangetos[i].value = "";
        }

    }else if(tabvalue=="3"){
        //删除排除单一值
        var notsingles = document.getElementsByName("notsingle");
        for(i = 0;i < notsingles.length;i++){
            notsingles[i].value = "";
        }
    }else if(tabvalue=="4"){
        var exrangefroms = document.getElementsByName("exrangefrom");
        for(i = 0;i < exrangefroms.length;i++){
            exrangefroms[i].value = "";
        }

        var exrangetos = document.getElementsByName("exrangeto");
        for(i = 0;i < exrangetos.length;i++){
            exrangetos[i].value = "";
        }
    }
}
function pasteData(type){
    //粘贴板数据
    var opData = null;
    try{
       opData  = window.clipboardData.getData("text");
    }catch(e){
        alert('error:' + e);
    }
    if(opData == null || opData == ""){
        alert("还没有复制任何数据");
    }else{
        //行分割
        var opLineBreak = new RegExp("\\r\\n", "g");
        //单元格分割
        var opTab = new RegExp("\\t", "g");
        //行
        var opRows = opData.split(opLineBreak);
        var tabvalue = document.getElementById(type+"tab").value;
        if(tabvalue==""||tabvalue=="1"){
            //单一值
            var singles = document.getElementsByName("single");
             var j,i;
            for(i = 0;i < singles.length;i++){
                if(singles[i].value!="")
                {
                }
                else
                {
                    break;
                }
            }

             for(j=i,i=0;j<singles.length;i++,j++)
            {
                if(typeof opRows[i]=='undefined'){
                    singles[j].value = "";
                }else{
                    singles[j].value = opRows[i].split(opTab)[0];
                }
            }
//            for(i = 0;i < singles.length;i++){
//                if(typeof opRows[i]=='undefined'){
//                    singles[i].value = "";
//                }else{
//                    singles[i].value = opRows[i].split(opTab)[0];
//                }
//            }
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
function changeParentValue(type){
    //var returnvalue =window.showModalDialog(url, this, 'dialogLeft='+(screen.width-400)/2+';dialogTop=50;dialogWidth=400px;dialogHeight=390px');

    //返回值
    var fvalue="";
    var From = parent.document.getElementById(type);
    
    var returnvalue =document.getElementById("returnValue").value;    
    if(typeof returnvalue != 'undefined' && returnvalue!=""){
        
        //保存到json隐藏域
        parent.document.getElementById(type).value = encodeURIComponent(returnvalue);
        
        parent.document.getElementById(type+"New").value = encodeURIComponent(returnvalue);
        /*
        var From = parent.document.getElementById(type+'From');
        var To = parent.document.getElementById(type+'To');
        var FromOld = parent.document.getElementById(type+'FromOld');
        var ToOld = parent.document.getElementById(type+'ToOld');
        */
        ret = "var obj=" + returnvalue;    
               
        // 生成json对象
        eval(ret);
        //选择单一值       
        if(typeof obj[type+"_single"] != "undefined"&& obj[type+"_single"]!=""){            
            var a = obj[type+'_single'].split(';');            
            for(var i=0;i<a.length;i++){
                fvalue += a[i]+";";
            }           
            
        }
        //范围
        if(typeof obj[type+"_range"] != "undefined"){
            var b = obj[type+'_range'];
            for(var i=0;i<b.length;i++){
                var arr = b[i];
                fvalue += arr['from']+"~"+arr['to']+";";
            }         
        }

    }   
   From.value = fvalue;
}
