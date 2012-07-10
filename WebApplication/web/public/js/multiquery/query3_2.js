/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function checkAllRange(){
    if(!checkRange("suppiercode")||!checkRange("plantcode")||!checkRange("materialcode")){
        return false;
    }
    return true;
}
function checkJsonAll(){
    checkJson("suppiercode");
    checkJson("plantcode");
    checkJson("materialcode");
}
function checkRange(key,info){
    var To = document.getElementById(key+"To").value;
    var From = document.getElementById(key+"From").value;
    if(To!=""){
        if(From==""){
            window.parent.document.getElementById("errmsg").innerHTML=_BYD_SRM_LG.qxz+info+'!';
            window.parent.showMsg();
            document.getElementById(key+"From").focus();
            return false;
        }
        return true;
    }else{
        return true;
    }
}
function makeJson(key){
    var from = document.getElementById(key+"From").value;
    var to = document.getElementById(key+"To").value;
    var a = "";
    var b = "";
    var c = new Array();
    var d = new Array();
    if(to!=""){
        if(from==""){
            return "";
        }else{
            c.push({
                from:from,
                to:to
            });
        }
    }else{
        if(from==""){
            return "";
        }else{
            a= from;
        }
    }
    var tmpjson = new Object();
    var exsinglekey = key +"_exsingle";
    var rangekey = key+"_range";
    var exrangekey = key+"_exrange";
    var singlekey = key+"_single";
    tmpjson[singlekey]=a;
    tmpjson[exsinglekey]=b;
    tmpjson[rangekey]=c;  
    tmpjson[exrangekey]=d;
    return js$util.json.toString(tmpjson);
}
function checkJson(key){
    var json=document.getElementById(key).value;
    var newjson = document.getElementById(key+"New").value;
    if(newjson==""){
        document.getElementById(key).value = encodeURIComponent(makeJson(key));
    }else{
        if(json==""){
            document.getElementById(key).value = encodeURIComponent(makeJson(key));
        }else{
            modJson(key);
        }
    }

}
function modJson(key){
    var json=document.getElementById(key).value;
    var to = document.getElementById(key+"To").value;
    var from = document.getElementById(key+"From").value;
    var oldTo = document.getElementById(key+"ToOld").value;
    var oldFrom = document.getElementById(key +"FromOld").value;
    if(json!=""){
        var obj = eval( "(" + decodeURIComponent(json) + ")" );
        if(to!="" && oldTo!=""){
            if(from!=""&&oldFrom!=""){
                var c = obj[key+'_range'];
                var arr = c[0];
                arr['from']=from;
                arr['to']=to;
            }else if(from==""&&oldFrom!=""){
                return;
            }

        }else if(to!=""&&oldTo==""){
            if(from!=""&&oldFrom!=""){
                var c = obj[key+'_range'];
                var arr = c[0];
                if(typeof arr == "undefined"){
                    c.push({
                        from:from,
                        to:to
                    });
                }else{
                    if(c[c.length-1]){
                        var arr = c[c.length-1];
                        arr['from']=from;
                        arr['to']=to;
                    }
                }
                if(typeof obj[key+"_single"] != "undefined"){
                    if(obj[key+"_single"].indexOf(";")>-1){
                        obj[key+"_single"]=obj[key+"_single"].substring(obj[key+"_single"].indexOf(";")+1);
                    }else{
                        obj[key+"_single"]="";
                    }
                }

            }else if(from==""&&oldFrom!=""){
                return;
            }else if(from!=""&&oldFrom==""){
                var c = obj[key+'_range'];
                var arr = c[0];
                if(typeof arr == "undefined"){
                    c.push({
                        from:from,
                        to:to
                    });
                }else{
                    if(c[c.length-1]){
                        var arr = c[c.length-1];
                        arr['from']=from;
                        arr['to']=to;
                    }
                }

            }else if(from==""&&oldFrom==""){
                return;
            }
        }else if(to==""&&oldTo!=""){
            if(from!=""&&oldFrom!=""){

                if(typeof obj[key+'_range'] != "undefined"){
                    var c = obj[key+'_range'];
                    var arr = c[0];
                    if(typeof arr == "undefined"){
                        return;
                    }else{
                        c= c.slice(1);
                        obj[key+'_range']=c;
                    }
                }
                if(typeof obj[key+"_single"] != "undefined"){
                    if(obj[key+"_single"].indexOf(";")>-1){
                        obj[key+"_single"]=obj[key+"_single"]+";"+from;
                    }else{
                        obj[key+"_single"]=from;
                    }
                }

            }else if(from==""&&oldFrom!=""){
                if(typeof obj[key+'_range'] != "undefined"){
                    var c = obj[key+'_range'];
                    var arr = c[0];
                    if(typeof arr == "undefined"){
                        return;
                    }else{
                        c= c.slice(1);
                        obj[key+'_range']=c;
                    }
                }

            }
        }else if(to==""&&oldTo==""){
            if(from!=""&&oldFrom!=""){
                if(typeof obj[key+"_single"] != "undefined"){
                    if(obj[key+"_single"].indexOf(";")>-1){
                        obj[key+"_single"]=from+obj[key+"_single"].substring(obj[key+"_single"].indexOf(";"));
                    }else{
                        obj[key+"_single"]=from;
                    }
                }

            }else if(from==""&&oldFrom!=""){
                if(obj[key+"_single"].indexOf(";")>-1){
                    obj[key+"_single"]=obj[key+"_single"].substring(obj[key+"_single"].indexOf(";")+1);
                }else{
                    obj[key+"_single"]="";
                }

            }else if(from!=""&&oldFrom==""){
                if(obj[key+"_single"].indexOf(";")>-1){
                    obj[key+"_single"]=from+obj[key+"_single"].substring(obj[key+"_single"].indexOf(";"));
                }else{
                    obj[key+"_single"]=from;
                }
            }else if(from==""&&oldFrom==""){
                return;
            }
        }
        document.getElementById(key+"New").value = encodeURIComponent(js$util.json.toString(obj));
        document.getElementById(key).value = encodeURIComponent(js$util.json.toString(obj));
    }

}

