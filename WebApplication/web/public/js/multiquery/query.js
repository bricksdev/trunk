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
    checkJson("suppiercode",'vendor','suppiercode');
    checkJson("plantcode",'plant','plantcode');
    checkJson("materialcode",'material','materialcode');
}
function checkRange(type){
    var To = document.getElementById(type+"To").value;
    var From = document.getElementById(type+"From").value;
    if(To!=""){
        if(From==""){
            if(type== "suppiercode"){
                alert("请选择供应商范围上限");
            }
            if(type == "plantcode"){
                alert("请选择工厂范围上限");
            }
            if(type == "materialcode"){
                alert("请选择物料范围上限");
            }
            document.getElementById(type+"From").focus();
            return false;
        }
        return true;
    }else{
        return true;
    }
}
function makeJson(type,key){
    var from = document.getElementById(type+"From").value;
    var to = document.getElementById(type+"To").value;
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
    return tmpjson.toJSONString();
}
function checkJson(type,key,id){
    var json=document.getElementById(type).value;
    var newjson = document.getElementById(type+"New").value;
    if(newjson==""){
        document.getElementById(type).value = encodeURIComponent(makeJson(type,key));
    }else{
        if(json==""){
            document.getElementById(type).value = encodeURIComponent(makeJson(type,key));
        }else{
            modJson(type,key,id);
        }
    }

}
function modJson(type,key,id){
    var json=document.getElementById(type).value;
    var to = document.getElementById(type+"To").value;
    var from = document.getElementById(type+"From").value;
    alert(33);
    var oldTo = document.getElementById(type+"ToOld").value;
    var oldFrom = document.getElementById(type +"FromOld").value;
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
                if(obj.plant_single.indexOf(";")>-1){
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
        document.getElementById(id).value = encodeURIComponent(obj.toJSONString());
    }


}

