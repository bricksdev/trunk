/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 工具类
 */
if(!window.Util){
    window.Util={}
}

Util.Extend = function(original,extended, isDeep){
    if (arguments.length<2){
        extended = original;
        original = this;
    }
    for (var property in extended) {
        var v=extended[property];
        if (isDeep && v && Util.Base.$type(v,'object','array') ){
            v=Util.Base.$clone(v, isDeep);
        }
        if (v!==undefined) {
            original[property] = v;
        }

    }
    return original;
};


Util.Base={
    $reallyType:function(obj){
        if (obj && obj.constructor && obj.constructor.toString) {
            var arr = obj.constructor.toString().match(/function\s*(\w+)/);
            if (arr && arr.length == 2) {
                return arr[1];
            }
        }
        return undefined;
    }
}

Util.Extend(Util.Base , {

    //'string', 'array', 'object', 'arguments', 'collection','number'
    $type : function (obj){
        var argNum=arguments.length;
        if (argNum>1){
            for (var i=1;i<argNum;i++){
                if(Util.Base.$type(obj)==arguments[i]) {
                    return true;
                }
            }
            return false;
        }
        var type = typeof obj;
        if (obj === null) {
            return 'object';
        }
        if (type == 'undefined') {
            return 'undefined';
        }
        if (obj.htmlElement) {
            return 'element';
        }
        if (type == 'object'  && obj.nodeType && obj.nodeName){
            switch(obj.nodeType){
                case 1:
                    return 'element';
                case 3:
                    return (/\S/).test(obj.nodeValue) ? 'textnode' : 'whitespace';
            }
        }
        if (Util.Base.$isArray(obj)) {
            return 'array';
        }
        if (type == 'object' && typeof obj.length == 'number'){
            return (obj.callee) ? 'arguments' : 'collection';
        }else if (type == 'function' && typeof obj.length == 'number' && obj[0]!==undefined ){
            return  'collection';
        }

        return type;
    },
    $chk:function (obj){
        return !!(obj || obj === 0 || obj==='' );
    },
    
    $isArray : function(a){
        return Object.prototype.toString.apply(a) === '[object Array]' ;
    },

    $array : function(iterable,start, end ,isDeep) {
        var results = [];
        if (iterable) {
            if (!Util.Base.$chk(start)){
                start=0;
            }
            if (!Util.Base.$chk(end)){
                end=iterable.length;
            }
            if (Util.Base.$type(iterable,'arguments', 'collection') ||  Util.Base.$type(iterable,'array')  &&  (start>0 || end<iterable.length) ){
                for (var i = start; i < end; i++) {
                    results.push(iterable[i]);
                }
            }else if (Util.Base.$type(iterable,'array') ){
                results=results.concat(iterable);
            }else{
                for (var k in iterable ){
                    if (iterable.hasOwnProperty(k)){
                        results.push( iterable[k] );
                    }
                }
            }
        }
        return results;
    },
    
    $clone : function(obj,isDeep){
        var newObj;
        if (!obj){
            newObj=obj;
        }else if (Uitl.$type(obj,'array','arguments', 'collection')){
            newObj=Util.Base.$array(obj,0,obj.length,isDeep);
        }else{
            newObj= Util.Base.$extend({},obj,isDeep);
        }
        return newObj;
    },
	

    $clear : function (timer){
        window.clearTimeout(timer);
        window.clearInterval(timer);
        if ( CollectGarbage ){
            CollectGarbage();
        }
        return null;
    },
		
    $thread : function(fn,timeout){
        //fn();
        //return;
        var nfn=fn;
        window.setTimeout(nfn ,timeout || 20);	
    },

    $each : function(iterable, fn, bind,arg){
        var resultList=[];
        if ( Sigma.$type(iterable,'array','arguments','collection') || iterable&&!Sigma.$type(iterable,'string')&&Sigma.$type(iterable.length,'number') ) {
            for (var i = 0, j = iterable.length; i < j; i++) {
                resultList.push( fn.call(bind || iterable, iterable[i], i, iterable,arg) );
            }
        } else {
            for (var name in iterable) {
                resultList.push( fn.call(bind || iterable, iterable[name], name,iterable,arg) );
            }
        }
        return resultList;
    }
} );

Util.Date={
    $pattern:function(date,fmt){
        date = date || new Date();
        fmt = fmt || "yyyy-MM-dd";
        var o = {
            "M+" : date.getMonth()+1, //月份  
            "d+" : date.getDate(), //日  
            "h+" : date.getHours()%12 == 0 ? 12 : date.getHours()%12, //小时  
            "H+" : date.getHours(), //小时  
            "m+" : date.getMinutes(), //分  
            "s+" : date.getSeconds(), //秒  
            "q+" : Math.floor((date.getMonth()+3)/3), //季度  
            "S" : date.getMilliseconds() //毫秒  
        };  
        var week = {  
            "0" : "\u65e5",  
            "1" : "\u4e00",  
            "2" : "\u4e8c",  
            "3" : "\u4e09",  
            "4" : "\u56db",  
            "5" : "\u4e94",  
            "6" : "\u516d"  
        };  
        if(/(y+)/.test(fmt)){  
            fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));  
        }  
        if(/(E+)/.test(fmt)){  
            fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[date.getDay()+""]);  
        }  
        for(var k in o){  
            if(new RegExp("("+ k +")").test(fmt)){  
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));  
            }  
        }  
        return fmt;  
    }
}

