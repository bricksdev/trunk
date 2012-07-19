var $=function(id){
    return document.getElementById(id);
};
var netAjax = new Object();
netAjax.READY_STATE_COMPLETE=4;
netAjax.Loader = function(url,callbacker,method,param,onerror)
{
    this.url = url;
    this.method = method ? method : "GET";
    this.param = param ? param : null;
    this.xmlHttp = null;
    this.oncallback = callbacker.complete;
    this.onerror = onerror;
    this.loadXmlDoc(url);
    this.callbacker = callbacker;
}
netAjax.Loader.prototype={
    loadXmlDoc:function(url){
        if (window.XMLHttpRequest){
            this.xmlHttp = new XMLHttpRequest();
        } else if (window.ActiveXObject){
            this.xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); //IE
        }
        if (this.xmlHttp){
            try{
                var loaderTemp = this;
                this.xmlHttp.onreadystatechange = function(){
                    loaderTemp.onReadyState.call(loaderTemp);
                }
                if (this.method == "POST"){
                    this.xmlHttp.open('POST',url,true);
                    this.xmlHttp.setRequestHeader("Content-Length",this.param.length);
                    this.xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                }else{
                    this.xmlHttp.open('GET',url,true);
                }
                this.xmlHttp.send(this.param);
            }catch(err){
                if(!this.onerror){
                    this.onerror.call(this.callbacker);
                }else{
                    this.defaultError.call(this);
                }

            }
        }
    },
    onReadyState:function(){
        var http = this.xmlHttp;
        var ready = http.readyState;
        if (ready == netAjax.READY_STATE_COMPLETE){
            if(http.status == 200 || http.status == 0){
                this.oncallback.call(this.callbacker, this.httpData.call(this));
            }else{
                if(!this.onerror){
                    this.onerror.call(this.callbacker);
                }else{
                    this.defaultError.call(this);
                }
            }
        }
    },
    httpData:function httpData() {
        var r = this.xmlHttp;
        var ct = r.getResponseHeader("content-type");
        var data = ct && ct.indexOf("xml") >= 0;
        data = (data ? r.responseXML: r.responseText);
        //        if (type == "script"){
        //            eval.call(window, "(" + data + ")");
        //        }
        return data;
    },
    defaultError:function(){
        alert("error :" + this.xmlHttp.getAllResponseHeaders());
    }
}
function AjaxBase(){
    this.flag = true;

    this.showDiv=function (id){
        this.g(id).style.display="block";
    };
    this.hiddenDiv=function (id){
        this.g(id).style.display="none";
    };
    this.operation=function(method, paramIds){

        if(this.flag)
        {
            this.flag = false;
            this.sendRequest(method,paramIds);

        }

    };
    this.sendRequest=function(method,paramIds){
        var ids = paramIds.split(",");
        var params = "&__METHOD=" + method + "&";
        // if out setted href
        if(this.href == null){
            this.href = location.href;
        }

        if(this.href.indexOf("?")==-1){
            params = "?__METHOD=" + method + "&";
        }
        for(var i=0;i<ids.length;i++){
            if(ids[i].length==0) {
                continue;
            }
            params += ids[i] + "=" + encodeURI($(ids[i]).value) + "&";
        }
        params += Math.random();

        new netAjax.Loader(this.href+params,this);
    };
    this.appendEvent = function(eid, fun){

        if(document.addEventListener){
            window.addEventListener(eid.slice(2, eid.length),fun,false);

        }else{
            window.attachEvent(eid,fun);

        }

    };
    this.keydownEvent = function(fun){
        document.onkeydown = fun;
    };
    this.getEvent = function(){
        if(document.all)
            return window.event;//如果是ie
        func=this.getEvent.caller;
        while(func!=null){
            var arg0=func.arguments[0];
            if(arg0){
                if((arg0.constructor==Event || arg0.constructor ==MouseEvent) || (typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation)){
                    return arg0;
                }
            }
            func=func.caller;
        }
        return null;
    }
}
/**
 * @param method 提交的方法
 * @param url 提交的url
 * @param parameters 需传递的参数
 * @param settingids 需赋值的内容 如果不传参将设定为默认返回的内容都设定值
 * @param tdContent 代表当前对象，主要设定表格中的内容使用
 * @param returnType 返回的文件类型，如果HTML将直接替换settingids的内容，其他将设定内容，默认为text
 *
 */
var OperateAjax = function(method,url,parameters, settingids, tdContent, returnType){
    this.method = method;
    this.href = url;
    this.parameters = parameters;
    this.settingids = settingids;
    this.backType = returnType;
    this.tdContent = tdContent;
    this.g = $;
    this.complete = function(xmlHttp){
        try{
            if((this.backType == null || typeof(this.backType) == "undefined") ){
                var ret = "var bakObjs = "+xmlHttp;
                // 生成json对象
                eval(ret);
                if(!(this.tdContent == null || typeof(this.tdContent) == "undefined")){

                    var tds = tdContent.parentNode.parentNode.children;
                    this.g.prototype.tds = tds;
                    this.g = function(id){
                        for(var idx = 0; idx < tds.length; idx++){
                            if(tds[idx].nodeName != "#text" && id==tds[idx].children[0].id){
                                return tds[idx].children[0];
                            }
                        }
                        return null;
                    };

                }
                this.setted(bakObjs);

            }else{
//                $(this.settingids).innerHTML = xmlHttp;
                  document.getElementById(this.settingids).innerHTML = xmlHttp;
            }
        }catch(err){
        // TODO 不处理
        alert('error');
        }
    };
    this.setted = function(bakObjs){
        if((this.settingids == null || typeof(this.settingids) == "undefined") ){
            for(var key in bakObjs){
                this.g(key).value = bakObjs[key];
            }
        }else{
            var ids = settingids.split(",");
            for(var idx = 0; idx < ids.length; idx++){
                this.g(ids[idx]).value = bakObjs[ids[idx]];
            }
        }
    };
    this.execute = function(){
        this.operation(this.method, this.parameters);
    };

};
OperateAjax.prototype = new AjaxBase();
/**
 * AJAX主提交方式
 * @param method 提交的方法
 * @param url 提交的url
 * @param parameters 需传递的参数
 * @param settingids 需赋值的内容 如果不传参将设定为默认返回的内容都设定值
 * @param tdContent 代表当前对象，主要设定表格中的内容使用
 * @param returnType 返回的文件类型，如果HTML将直接替换settingids的内容，其他将设定内容，默认为text
 */
function AjaxExecute(method,url,parameters, settingids, tdContent, returnType){
    new OperateAjax(method,url,parameters, settingids, tdContent, returnType).execute();
}