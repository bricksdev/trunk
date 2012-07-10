/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//ajax请求
var $ajax={
    request:function(url,postData){
         $ajax_makeRequest(handleSuccess,handleFailure,url,postData);
    }
}
var ajax$req={
    uploadfile:function(sUrl,from,upload){//(URL 地址，from ID ,上传成功执行方法)
        YCM = YAHOO.util.Connect;
      
        YCM.setForm( $d(from),true,true);
        var callback = {        
            upload:function(o) {               
                YAHOO.example.container.wait.hide();
                upload(o);
            } 
        };      
        YCM.asyncRequest('POST', sUrl,callback);
    },
     uploadfileCrossDomain:function(sUrl,from,upload){//(URL 地址，from ID ,上传成功执行方法)
        YCM = YAHOO.util.Connect;

        YCM.setForm( $d(from),true,true);
        var callback = {
            upload:function(o) {
                YAHOO.example.container.wait.hide();
                upload(o);
            },
                xdr: true
        };
        YCM.asyncRequest('POST', sUrl,callback);
    },
    request:function(handleSuccess,handleFailure,sUrl,form){

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
    },    
    requestCrossDomain:function(handleSuccess,handleFailure,sUrl,form){

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
            argument:['foo','bar'],
                xdr: true
        };
    
        if(js$util.lang.isNotEmpty(form)){

            YAHOO.util.Connect.setForm(form);
}
        YAHOO.util.Connect.asyncRequest('POST', sUrl, callback);
    }

}




var $ajax_makeRequest=function (handleSuccess,handleFailure,sUrl,postData){


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
            if(o.responseText !== undefined){
                var err = "Transaction id: " + o.tId + " ";
                err += "HTTP status: " + o.status + " ";
                err += "Status code message: " + o.statusText + " ";
                alert(err);
            }
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

    YAHOO.util.Connect.asyncRequest('POST', sUrl, callback, postData);

}
