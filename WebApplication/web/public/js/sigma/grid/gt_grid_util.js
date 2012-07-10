/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//增加Sigma表中的方法
BSigma={
    Grid:{
        Column:{//列中增加新方法
            createCheckColumn: function(grid,cfg){ //新的创建复选框


                var id = cfg.id;
                grid=Sigma.$grid(grid);
                var gridId=grid.id;//表格ID
                var checkValid = cfg.checkValid;//选择操作
                var checkValue = cfg.checkValue;
                var checkType=cfg.checkType || 'checkbox';

                if (!checkValue){
                    checkValue = Sigma.$chk(cfg.fieldIndex)?
                    'record["'+cfg.fieldIndex+'"];'	: 'grid.getUniqueField(record);';
                }
                if (typeof checkValue == 'string'){
                    //
                    checkValue = new Function( 'value' ,'record','col','grid','colNo','rowNo',
                        [
                        'return ', checkValue
                        ].join('')
                        );
                }
                if (!checkValid){
                    checkValid = function(cvalue ,value,record,colObj,_g,colNo,rowNo){

                        return _g.checkedRows[cvalue];
                    };
                }

                cfg.header='';
                cfg.title= cfg.title || grid.getMsg('CHECK_ALL');
                cfg.width=30;
                cfg.resizable = false ;
                cfg.printable = false ;
                cfg.sortable = false ;
                var checkBoxName= 'gt_'+gridId+'_chk_'+id ;
                cfg.hdRenderer = function(h,c,_g){
                    return '<input type="'+checkType+'" class="gt-f-totalcheck" name="'+checkBoxName+'" />';
                };
                cfg.renderer = function(value ,record,colObj,_g,colNo,rowNo){
                    //新增加属性
                    var isDisabled=false;
                    if(cfg.isDisabled){//是否可选
                        isDisabled =cfg.isDisabled(record);
                    }
                    var cvalue= checkValue(value ,record,colObj,_g,colNo,rowNo);
                    var checkFlag= checkValid(cvalue,value ,record,colObj,_g,colNo,rowNo)?'checked="checked"':'';

                    var disabled=isDisabled ? "disabled" :"";//设置复选框是否可选
                    return '<input type="'+checkType+'" class="gt-f-check" value="'+cvalue+'" '+checkFlag+'  '+disabled+' name="'+checkBoxName+'" />';
                };
                return cfg;
            },
            OldCreateCheckColumn:Sigma.Grid.createCheckColumn,//框架自带方法

            setCreateBCheckColumnFn:function(){
                Sigma.checkOneBox=BSigma.checkOneBox;//初始换单个是否可选
                Sigma.Grid.createCheckColumn=this.createCheckColumn;//覆盖表格方法
            }
        }
    },
    Util:{
        configColumn:function(){
            BSigma.Grid.Column.setCreateBCheckColumnFn();
        },
        onLoad:function(fn, win){
            this.configColumn();
            Sigma.Util.onLoad(fn,win);
        }
    },
    checkOneBox:function(chkbox, grid,chk){//复选框单个选择
        if(chkbox.disabled){//复选框为不可用
            return false;
        }
        grid=Sigma.$grid(grid);
        chkbox=Sigma.$(chkbox);
        if (chkbox.checked==chk){
            return chk;
        }
        var cell=Sigma.U.getParentByTagName('td',chkbox);
        var row=cell.parentNode;
        var mrow = grid.getTwinRows(row)[0];
        if (chk===true || chk===false){
            if(Sigma.$invoke(this,'onRowCheck',[mrow, chk, grid])===false){
                return !!chkbox.checked;
            }
            chkbox.checked=chk;
        }
        if (chkbox.checked){
            grid.checkedRows[chkbox.value]=true;
            if (grid.selectRowByCheck){
                grid.selectRow(mrow, true);
            }
        }else{
            delete grid.checkedRows[chkbox.value];
            if (grid.selectRowByCheck){
                grid.selectRow(mrow, false);
            }
        }
        return !!chkbox.checked;
    }
}
WNSigma={
    GridDefault:{
        crossDomain:false,
        request:function(F,_,E,D,B){
            var C=this;
            C.requesting=true;
            var A=_[C.CONST.action];
            if(F){
                try{
                    if(typeof WNSigma.GridDefault.crossDomain!="undefined" && WNSigma.GridDefault.crossDomain){
                        C.ajax=new WNSigma.Ajax(F);
                    }else{
                        C.ajax=new Sigma.Ajax(F);
                    }
                    C.ajax.encoding=C.encoding||C.ajax.encoding;
                    C.ajax.method=C.ajaxMethod||C.ajax.method;
                    C.ajax.mimeType=C.mimeType||C.ajax.mimeType;
                    C.ajax.jsonParamName=C.jsonParamName||C.ajax.jsonParamName;
                    C.ajax.onSuccess=D||Sigma.$empty;
                    C.ajax.onFailure=B||Sigma.$empty;
                    C.ajax.setQueryParameters(C.queryParameters);
                    C.ajax.send({
                        data:_
                    })
                }catch($){
                    B({
                        status:"Exception "+$.message
                    },$)
                }
            }else B({
                status:"url is null"
            })
        }
    },
    Ajax:Sigma.$class( {

        properties : function(){
            return {
                method: 'POST',
                jsonParamName : Sigma.AjaxDefault.paramName,
                async: true,
                urlEncoded: true,
                encoding: null, //'GBK',
                mimeType: null, //'text/html',

                beforeSend: Sigma.$empty,
                onComplete: Sigma.$empty,
                onSuccess: Sigma.$empty,
                onFailure: Sigma.$empty,
                onCancel: Sigma.$empty,
                YCM:YAHOO.util.Connect,
                xhr : '',
                url: '',
                data: '',

                paramType : 'jsonString', // jsonString queryString xml

                headers: {
                    'X-Requested-With': 'XMLHttpRequest',
                    'Accept': 'text/javascript, text/html,application/xml,application/json, text/xml, */*'
                },

                autoCancel: false,

                evalScripts: false,
                evalResponse: false,
                responseContentType :'',

                dataUrl : false,

                queryParameters : null



            };

        },


        //为跨域写单独的代码，调用yui的ajax
        //----------------------------------------------------------------------------------
        crossdomainAjax:function(url,postdata,parent){

            var responseSuccess=function(o){
                //var   resText = o.responseText;
                //alert(o);
                //alert(resText);
                parent._onSuccess(o,parent);
            };
            var responseFailure=function(o){
                //alert(o);
                parent._onFailure(o);
            };
            var responseComplete=function(o){
            //alert(o);
            // load$Page.loadComplete(o);
            // this._onSuccess(o);
            };
            var responseAbort=function(o){
                //alert(o);
                parent.cancel(o);
            };
            var callback={
                cache:false,
                success:responseSuccess,
                failure:responseFailure,
                complete:responseComplete,
                abort:responseAbort,
                xdr: true
            };

            // this.YCM.asyncRequest('POST', url, callback,postdata);
            this.YCM.asyncRequest(this.method, url, callback,postdata);
        },
        setQueryParameters : function(queryParameters){
            this.queryParameters=queryParameters;
        },


        initialize: function(options){
            options= options || {};
            if (Sigma.$type(options,'string')){
                options={
                    url : options
                };
            }

            //if (!(this.xhr = this.getXHR())) { return; } 注释掉不用XMLHttpRequest
            var _header=Sigma.$extend(this.headers,options.headers);
            Sigma.$extend(this,options);
            if (this.mimeType){
                _header['X-Response-MimeType']=this.mimeType;
            }
            this.headers = _header;
        },


        send: function(options){

            this.running = true;

            if (Sigma.$type(options,'string')){
                options={
                    data : options
                };
            }
            options = Sigma.$extend({
                data: this.data,
                url: this.url,
                method:
                this.method
            }, options);
            var data = options.data, url = options.url, method = String
            (options.method).toLowerCase();

            if (Sigma.$invoke(this,'beforeSend',[this.xhr,data])===false){
                return this;
            }

            if (this.urlEncoded && method == 'post'){
                var encoding = (this.encoding) ? '; charset=' + this.encoding : '';
                this.setHeader('Content-type','application/x-www-form-urlencoded' +
                    encoding);
            }

            switch(Sigma.$type(data)){
                case 'object':
                    if (this.paramType =='jsonString') {
                        var _data=Sigma.$json(data);
                        data = { };
                        data[this.jsonParamName]=_data;
                    }
                    data = Sigma.toQueryString(data);
                    break;
                default:
            // do nothing;
            }


            var _queryParameters;
            if (this.queryParameters && Sigma.$type(this.queryParameters,'object')){
                _queryParameters =  Sigma.toQueryString(this.queryParameters);
            }else if (Sigma.$type(this.queryParameters,'string')){
                _queryParameters = this.queryParameters;
            }

            if (_queryParameters && Sigma.$type(data,'string')){
                data = data + '&'+ _queryParameters;
            }

            //alert(_queryParameters)
            if ( method == 'post'){
                // todo fixed url too long. err code : 122
                var idx= url.indexOf('?');
                if (idx>=0){
                    data=url.substring(idx+1)+'&'+data;
                    url=url.substring(0,idx);
                }

            }else if (data && ( method == 'get' || this.dataUrl) ){
                url = url + (url.indexOf('?')>=0 ? '&' : '?') + data;
                data = null;
            }
            //注释掉不用XMLHttpRequest
            //		var _ajax=this;
            //
            //		this.xhr.open(method.toUpperCase(), url, this.async);
            //		this.xhr.onreadystatechange = function(){
            //			return _ajax.onStateChange.apply(_ajax,arguments);
            //		};
            //		for (var key in this.headers ){
            //			try{
            //				this.xhr.setRequestHeader(key, this.headers[key]);
            //			}catch(e){
            //
            //			}
            //		}
            //		//alert(data);
            //		this.xhr.send(data);
            //		if (!this.async) { this.onStateChange();}
            this.crossdomainAjax(url,data,this);

            return this;
        },
        //注释掉不用XMLHttpRequest
        //	onStateChange: function(){
        //		if (this.xhr.readyState != 4 || !this.running) {return;}
        //		this.running = false;
        //		this.status = 0;
        //		try {
        //			this.status = this.xhr.status;
        //		}catch (e){
        //
        //		}
        //		this.onComplete();
        //		if (this.isSuccess()){
        //			this._onSuccess();
        //		}else{
        //			this._onFailure();
        //		}
        //		this.xhr.onreadystatechange = Sigma.$empty;
        //	},

        isScript: function(){
            return (/(ecma|java)script/).test(this.getHeader('Content-type'));

        },

        //注释掉不用XMLHttpRequest
        //	isSuccess: function(){
        //		var status=this.xhr.status;
        //		return ((status >= 200) && (status < 300));
        //	},

        _onSuccess: function(o,p){
            p.response = {
                'text': o.responseText,
                'xml': o.responseXML
            };
            p.onSuccess(p.response);
        },

        _onFailure: function(e,p){
            var A={};
            A["status"]="";
            p.onFailure(A,e);
        },

        setHeader: function(name, value){
            this.headers[name]= value;
            return this;
        },
        //注释掉不用XMLHttpRequest
        //	getHeader: function(name){
        //		try{
        //			return this.xhr.getResponseHeader(name);
        //		}catch (e){
        //			return null;
        //		}
        //	},

        //注释掉不用XMLHttpRequest
        //	getXHR: function(){
        //		return (window.XMLHttpRequest) ? new XMLHttpRequest() :((window.ActiveXObject) ? new ActiveXObject('Microsoft.XMLHTTP') : false);
        //	},

        cancel: function(o,p){
            //if (!this.running) {return this;}
            //this.running = false;
            //注释掉不用XMLHttpRequest
            //		this.xhr.abort();
            //		this.xhr.onreadystatechange = Sigma.$empty;
            //		this.xhr = this.getXHR();
            p.onCancel();
            return p;
        }


    } )
};
FixSigmaBug={
    FixHeadCss:function(){
        var $=null;
        return{
            createStyleSheet:function(C,$,A){
                // alert(sigmaGrid$util.firstLoad);
                if(!sigmaGrid$util.firstLoad){
                    var oldhead=document.getElementsByTagName("head")[0];
                    var oldstyle=oldhead.getElementsByTagName("style");
                    //alert(oldstyle[(oldstyle.length-1)].innerHTML);
                    oldhead.removeChild(oldstyle[(oldstyle.length-1)]);
                }
                sigmaGrid$util.firstLoad=false;
                var D;
                A=A||Sigma.doc;
                var B=A.getElementsByTagName("head");
                if(!B||B.length<1){
                    B=A.createElement("head");
                    if(A.documentElement)A.documentElement.insertBefore(B,A.body);else A.appendChild(B);
                    B=A.getElementsByTagName("head")
                }
                var E=B[0],F=A.createElement("style");
                F.setAttribute("type","text/css");
                if($)F.setAttribute("id",$);
                if(Sigma.isIE){
                    try{
                        E.appendChild(F);
                        D=F.styleSheet;
                        D.cssText=C
                    }catch(_){
                    }
                //                        F.styleSheet.cssText=C;
                }else{
                    try{
                        F.appendChild(A.createTextNode(C))
                    }catch(_){
                        F.cssText=C
                    }
                    E.appendChild(F);
                    D=F.styleSheet?F.styleSheet:(F.sheet||A.styleSheets[A.styleSheets.length-1])
                }
                this.cacheStyleSheet(D);
                return D
            },
            getRules:function(_,A){
                A=A||Sigma.doc;
                if(!$||_){
                    $={};

                    var C=A.styleSheets;
                    for(var D=0,B=C.length;D<B;D++)this.cacheStyleSheet(C[D])
                }
                return $
            },
            getRule:function(_,$){
                var A=this.getRules($);
                return A[_.toLowerCase()]
            },
            updateRule:function($,B,A){
                var _=this.getRule($);
                if(_)_.style[B]=A
            },
            cacheStyleSheet:function(A){
                $=$||{};

                try{
                    var B=A.cssRules||A.rules;
                    for(var C=B.length-1;C>=0;--C)$[B[C].selectorText.toLowerCase()]=B[C]
                }catch(_){}
            }
        }
    }()
}