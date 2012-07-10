var center$id="center1";

var layout;
var _requireIds;
var $Dom = YAHOO.util.Dom;
var $d =function(domid){
    return $Dom.get(domid)
};
var _scriptEval = null;
var YCM = YAHOO.util.Connect;
Event = YAHOO.util.Event;

YAHOO.namespace("example.container");


var js$util={
    action:{
        setSuccess:function(m){
            load$Page.loadSuccess=m;
        },
        setFailure:function(m){
            load$Page.loadFailure=m;
        },
        goPage:function(url,form){

            PanelInit(url,'','',form);
        //load$Page.thisDomain(url, "", "", form)
        },
        goPageNew:function(url,form){
            PanelInit(url,'new','',form);
        //load$Page.thisDomain(url, "", "", form)
        },
        download:function(url,form){
            form.action = url;
            form.target = "_self";
            form.submit();
        }
    },
    config:{
        openType:{
            BLANK:"blank",
            IFRAME:"iframe",
            NEW:"new",
            HREF:"href"
        }
    },
    initPage:{
        setNewTab: function (id,homepage){//转换首页
            if(homepage!=null && homepage!=home_page_index  ){
                window.location.href=webserver+"/views/"+homepage+"?defaulttabid="+id;
            }
            var nav_mytop=document.getElementById("nav_mytop");
            var  mytop_li=nav_mytop.getElementsByTagName("li");
            for(i=0;i<mytop_li.length;i++){
                var topid=mytop_li[i].id;
                var menu=document.getElementById(topid);
                var con=document.getElementById("con_"+topid);
                if(("two_"+id)==topid){
                    menu.className="hover";
                    if(con!=null  && con!=undefined && con!="undefined"){
                        con.style.display="block";
                    }
                //   con.style.display="block";
                }else{
                    menu.className="";
                    if(con!=null  && con!=undefined && con!="undefined"){
                        con.style.display="none";
                    }
                }
            }
        }
    },
    loadpage:{
        wait:function(){
            //初始化等待加载页面
            if (!YAHOO.example.container.wait) {
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
            }

            //初始化页面加载信息


            YCM.startEvent.subscribe(function(){
                YAHOO.example.container.wait.show();
            });
            YCM.successEvent.subscribe(function(){
                YAHOO.example.container.wait.hide();
            });
            YCM.failureEvent.subscribe(function(){
                YAHOO.example.container.wait.hide();
            });
            YCM.abortEvent.subscribe(function(){
                YAHOO.example.container.wait.hide();
            });
            YCM.failureEvent.subscribe(function(){
                YAHOO.example.container.wait.hide();
            });
        //加载跨域swf文件
        // YCM.transport(webserver+'/public/yui/connection/connection.swf');
        },
        configNav1:function(){
            //初始化 一层菜单

            var nav$top= $d("nav_mytop");
            var nav$topdata=$MENU.System$Menu1;//一层菜单数据

            for(var i=0;i<nav$topdata.length;i++){

                nav$topdata[i]= js$util.urlReg.initUrl(nav$topdata[i])

                var li=  document.createElement("li");//创建li标签
                li.id="two_"+nav$topdata[i].pcode;
                //li 中添加 a 超链接选择地址
                var a=document.createElement("a");
                a.innerHTML=nav$topdata[i].name;

                if(nav$topdata[i].scope!=""){

                    switch(nav$topdata[i].opentp){
                        case js$util.config.openType.NEW:
                            var turl=js$util.urlReg.getUrlByTree(nav$topdata[i])+"?defaulttabid="+nav$topdata[i].pcode;
                            a.href="javascript:window.open('"+turl+"');void(0);";
                            break;
                        case js$util.config.openType.HREF:
                            a.href=js$util.urlReg.getUrlByTree(nav$topdata[i])+"?defaulttabid="+nav$topdata[i].pcode;
                            break;
                        default:
                            a.href="javascript:js$util.initPage.setNewTab('"+nav$topdata[i].pcode+"','"+nav$topdata[i].hpage+"')";//添加操链接
                            break;
                    }
                }else{
                    a.href="javascript:js$util.initPage.setNewTab('"+nav$topdata[i].pcode+"','"+nav$topdata[i].hpage+"')";//添加操链接
                }

                a.title=nav$topdata[i].name;
                li.appendChild(a);
                nav$top.appendChild(li);
            }
        },
        configNav2:function(){
            var nav$top= $d("page_system_nav2");
            var nav$topdata=$MENU.System$Menu2;//二层菜单数据

            for(var o in nav$topdata){
                var div=document.createElement("div");//创建一个 div
                div.id="con_two_"+o;
                div.style.display="none";//默认为隐藏
                var ul=document.createElement("ul");
                var nav2data=nav$topdata[o]
                var li=document.createElement("li");
                var a=document.createElement("a");
                a.innerHTML="&nbsp;&nbsp;";
                li.appendChild(a);
                ul.appendChild(li);

                for(var l =0;l<nav2data.length;l++){//往 ul 中添加 li

                    nav2data[l]= js$util.urlReg.initUrl(nav2data[l])

                    var li1=document.createElement("li");
                    //添加对应链接
                    var a1=document.createElement("a");


                    if(nav2data[l].scope!=""){
                        switch(nav2data[l].opentp){
                            case js$util.config.openType.NEW:
                                var turl=js$util.urlReg.getUrlByTree(nav2data[l])+"?defaulttabid="+nav2data[l].pcode;
                                a1.href="javascript:window.open('"+turl+"');void(0);";
                                break;
                            case js$util.config.openType.HREF:
                                a1.href=js$util.urlReg.getUrlByTree(nav2data[l])+"?defaulttabid="+nav2data[l].pcode;
                                break;
                            default:
                                a1.href="javascript:displayLayoutTree(false,'"+nav2data[l].pcode+"',null,'"+nav2data[l].hpage+"','"+nav2data[l].patercode+"')";
                                break;
                        }
                    }else{
                        a1.href="javascript:displayLayoutTree(false,'"+nav2data[l].pcode+"',null,'"+nav2data[l].hpage+"','"+nav2data[l].patercode+"')";
                    }


                    a1.innerHTML=nav2data[l].name;
                    li1.appendChild(a1);
                    ul.appendChild(li1);
                    if(l ==0 && js$util.lang.isNotEmpty($MENU.defaulttab$id) && nav2data[l].patercode==$MENU.defaulttab$id){//当前高亮的为那个菜单
                        this.defaultinfo=nav2data[l];
                    }


                }

                div.appendChild(ul);
                nav$top.appendChild(div);


            }

        //
        },
        defaultinfo:null//当前默认显示的pcode
    },
    json:{
        toObject:function(jsonString){ //将字符装换为json对象
            return  YAHOO.lang.JSON.parse(jsonString);

        },
        toString:function(data){
            return YAHOO.lang.JSON.stringify(data)
        }

    },
    urlReg:{
        subsyprojects:{},
        initUrl:function(tree_data){//初始化默认地址     为当前URL信息

            if(!js$util.lang.isNotEmpty(tree_data.protocol)){//默认协议
                tree_data.protocol=window.location.protocol.replace(":","");
            }

            if(!js$util.lang.isNotEmpty(tree_data.servernm)){//默认服务器地址
                tree_data.servernm=document.domain;
            }

            // if(!js$util.lang.isNotEmpty(tree_data.prot)){//默认服务器地址
            //tree_data.prot=window.location.port;
            // }

            // if(!js$util.lang.isNotEmpty(tree_data.subsyproject)){//默认服务器地址
            //   tree_data.subsyproject=$sy_config_contextPath.replace("/","");
            //   }
            return tree_data;
        },
        isThisDomain:function(tree_data){//判断是否在当前域 当前项目

            if(!js$util.lang.isNotEmpty(tree_data.protocol)){
                tree_data.protocol=window.location.protocol.replace(":","");
            }
            if(document.domain==tree_data.servernm && (window.location.port==tree_data.prot||tree_data.prot=='80'||tree_data.prot=='443') && window.location.protocol==tree_data.protocol+":" 
                && (contextpath==""||contextpath=="/"||contextpath=="/"+tree_data.subsyproject)
                ){
                return true;
            }else{
                return false;
            }
            return true;
        },
        getUrlByTree:function(tree_data){
            var tpurl="";
            if(tree_data.scope!=""){


                tpurl=tree_data.protocol+"://"+tree_data.servernm;
                if(tree_data.prot!=""){
                    tpurl=tpurl+":"+tree_data.prot;
                }
                if(tree_data.scope!="" && tree_data.scope.substring(0,1)!="/"){
                    tree_data.scope="/"+tree_data.scope;
                }
                if(tree_data.subsyproject!=""){
                    tpurl=tpurl+"/"+tree_data.subsyproject;
                }
                tpurl=tpurl+tree_data.scope;
                if(tree_data.param!=""){
                    tpurl=tpurl+"?"+tree_data.param+"&pageCode="+tree_data.pcode
                }
                
            }
            return tpurl;
        },
        getForwordUrl:function(tree_data){
            var tpurl="";

            tpurl=tree_data.protocol+"://"+tree_data.servernm;
            if(tree_data.prot!=""){
                tpurl=tpurl+":"+tree_data.prot;
            }
            if(tree_data.scope!="" && tree_data.scope.substring(0,1)!="/"){
                tree_data.scope="/"+tree_data.scope;
            }
            if(tree_data.subsyproject!=""){
                tpurl=tpurl+"/"+tree_data.subsyproject;
            }
            tpurl=tpurl+"/views/index.jsp?p_code="+tree_data.pcode;
            return tpurl;
        },
        conversion:function(url){
            //$1-$4  协议，域名，端口号，还有最重要的路径path！   $5-$7  文件名，锚点(#top)，query参数(?id=55)
            var re = /(\w+):\/\/([^\:|\/]+)(\:\d*)?(.*\/)([^#|\?|\n]+)?(#.*)?(\?.*)?/i;
            //re.exec(url);
            var arr = url.match(re);
            return arr;
        },
        prot:function (url){
            var t=this.conversion(url)[3];
            if(t ==undefined || t==null ){
                return "";
            }
            return t.substring(1, t.length);
        },
        domain:function (url){
            return this.conversion(url)[2];
        },
        basepath:function(url){//完整域名
            var d= this.conversion(url);
            var turl=d[1]+"://"+d[2];
            if(js$util.lang.isNotEmpty(d[3])){
                turl=turl+d[3];
            }
            return  turl;
        },
        href:function(url){

            window.location.href=url;
        },
        open:function(url){
            window.open(url);
        },
        path:function(url){//完整域名
            var d= this.conversion(url);
            var turl=d[1]+"://"+d[2];
            if(js$util.lang.isNotEmpty(d[3])){
                turl=turl+d[3];
            }
            turl=   turl+d[4];
            return  turl;
        },
        addParameter:function(url,name,value){
            if(url.indexOf("?")==-1){
                return url+"?"+name+"="+value;
            }else{
                return url+"&"+name+"="+value;
            }
        }

    },
    lang:{
        isNotEmpty:function(str){
            if(str!=undefined && str!=null && str!="" && str!="undefined"){
                return true;
            }else{
                return false;
            }

        }
    },
    script:{

        scriptEval : function(tag) {
            if ( _scriptEval === null ) {
                var root = document.documentElement,
                script = document.createElement(tag),
                id = tag + (new Date()).getTime();

                // Make sure that the execution of code works by injecting a script
                // tag with appendChild/createTextNode
                // (IE doesn't support this, fails, and uses .text instead)
                try {
                    script.appendChild( document.createTextNode( "window." + id + "=1;" ) );
                } catch(e) {}

                root.insertBefore( script, root.firstChild );
                //root.appendChild( script);
                if ( window[ id ] ) {
                    _scriptEval = true;
                    delete window[ id ];
                } else {
                    _scriptEval = false;
                }

                root.removeChild( script );
            }

            return _scriptEval;
        },
        globalEvalDynamicImport: function( data,tag,type ) {
            if ( data && js$util.lang.isNotEmpty( data ) ) {
                // Inspired by code by Andrea Giammarchi
                // http://webreflection.blogspot.com/2007/08/global-scope-evaluation-and-dom.html
                var head = document.head || document.getElementsByTagName( "head" )[0] || document.documentElement,
                script = document.createElement( tag );
                if(js$util.lang.isNotEmpty( type )&&"text/css"==type){
                    script.type="text/css";
                    script.rel = "stylesheet";
                    script.href=data;
                }else if(js$util.lang.isNotEmpty( type )&&"text/javascript"==type){
                    script.type="text/javascript";
                    script.src=data;
                }
                if ( js$util.script.scriptEval(tag) ) {
                    script.appendChild( document.createTextNode( data ) );
                } else {
                    script.text = data;
                }

                // Use insertBefore instead of appendChild to circumvent an IE6 bug.
                // This arises when a base node is used (#2709).
                head.insertBefore( script, head.firstChild );
                // head.appendChild( script);
            //    head.removeChild( script );
            }
        },

        getScriptForOther:function(str){
            var   matchAll   =   new   RegExp('(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)',   'img');
            var   matchOne   =   new   RegExp('(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)',   'im');
            var   a   =   str.match(matchAll)   ||   [];
            var   result   =   [];
            for(var   i   =   0;   i   <a.length;   i++){
                //    scripts[i].src = scripts[i].src.match(/src\s*=\s*(\"([^\"]*)\"|\'([^\']*)\'|([^\s]*)[\s>])/i);
                if(((a[i].match(matchOne)   ||   ['',   ''])[1])!=""){
                    result.push((a[i].match(matchOne)   ||   ['',   ''])[1]);
                 } else{
                    js$util.script.globalEvalDynamicImport(a[i].substring(a[i].indexOf("src=\"")+5,(a[i].indexOf("\">"))),"script","text/javascript");
                }
            }
            return   result;
        },
        getScript:function(str){
            var   matchAll   =   new   RegExp('(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)',   'img');
            var   matchOne   =   new   RegExp('(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)',   'im');
            var   matchExcept = 
            new RegExp('(<div\\s*id="sigma_config_div"\\s*style="display: none"\\s*>)((\n|\r|.)*?)(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)((\n|\r|.)*?)(<\/div>)','img');
            var except = str.match(matchExcept) ||   [];
             if(str.match(matchExcept)!=null){
                for(var k=0;k<except.length;k++){
                    str = str.replace(except[k], "");
                }
            }
            var   a   =   str.match(matchAll)   ||   [];
            var   result   =   [];
            for(var   i   =   0;   i   <a.length;   i++){
                //    scripts[i].src = scripts[i].src.match(/src\s*=\s*(\"([^\"]*)\"|\'([^\']*)\'|([^\s]*)[\s>])/i);
                if(((a[i].match(matchOne)   ||   ['',   ''])[1])!=""){
                    result.push((a[i].match(matchOne)   ||   ['',   ''])[1]);
                } else{
                    js$util.script.globalEvalDynamicImport(a[i].substring(a[i].indexOf("src=\"")+5,(a[i].indexOf("\">"))),"script","text/javascript");
                }
            }
            return   result;
        },
        evalScript:function(str){
            var   scripts   =   this.getScript(str);
            for(var   i   =   0;   i   <   scripts.length;   i++){

                try{


                    if(scripts[i]!=null && scripts[i]!=""){
                        //  alert(scripts[i])
                        //   var script=window.document.createElement("script");
                        //  script.text=scripts[i];//给新的script标签赋值
                        // script.type="text/javascript";
                        //js$util.script.globalEval(scripts[i]);
                        js$util.script.globalEvalDynamicImport( scripts[i],"script" );
                    //  window.document.getElementsByTagName("head")[0].appendChild(script);
                    // window.document.getElementsByTagName("head")[0].removeChild(script);
                    }
                }
                catch(e){

                }


            }
        },
        evalScriptForOther:function(str){
            var   scripts   =   this.getScriptForOther(str);
            for(var   i   =   0;   i   <   scripts.length;   i++){

                try{


                    if(scripts[i]!=null && scripts[i]!=""){
                        //  alert(scripts[i])
                        //   var script=window.document.createElement("script");
                        //  script.text=scripts[i];//给新的script标签赋值
                        // script.type="text/javascript";
                        //js$util.script.globalEval(scripts[i]);
                        js$util.script.globalEvalDynamicImport( scripts[i],"script" );
                    //  window.document.getElementsByTagName("head")[0].appendChild(script);
                    // window.document.getElementsByTagName("head")[0].removeChild(script);
                    }
                }
                catch(e){

                }


            }
        },
        importScript:function(src){
            js$util.script.globalEvalDynamicImport(src,"script","text/javascript");
        //            var script=window.document.createElement("script");
        //
        //            script.type="text/javascript";
        //            script.src=src;
        //            window.document.getElementsByTagName("head")[0].appendChild(script);
        //            window.document.getElementsByTagName("head")[0].removeChild(script);
        },
        importSigmaConfScript:function(src){
            var script=window.document.createElement("script");

            script.type="text/javascript";
            script.src=src;
            window.document.getElementsByTagName("head")[0].appendChild(script);

            window.document.getElementsByTagName("head")[0].removeChild(script);
            var   sigmagrid=new Sigma.Grid(gridOption);
            $sy_render_error(sigmagrid);
            $sy_render_fun_bps(sigmagrid);
            YAHOO.util.Event.addListener(window, "resize",function(e){
                sigmagrid._onResize();
            });
        },
        importCss:function(src){
            js$util.script.globalEvalDynamicImport(src,"link","text/css");
        //            var script=window.document.createElement("link");
        //            script.rel = "stylesheet";
        //            script.type="text/css";
        //            script.href=src;
        //            window.document.getElementsByTagName("head")[0].appendChild(script);
        //            window.document.getElementsByTagName("head")[0].removeChild(script);
        },
        globalEval: function( data ) {
            if ( data && js$util.lang.isNotEmpty( data ) ) {
                // We use execScript on Internet Explorer
                // We use an anonymous function so that context is window
                // rather than jQuery in Firefox
                ( window.execScript || function( data ) {
                    window[ "eval" ].call( window, data );
                } )( data );
            }
        }
    },
    tree:{
        configTree:function(treeData,p_code,nodecode){
            // alert(js$util.json.toString(treeData[p_code].childList))
            var  treeList=treeData[p_code].childList;//得到当前父菜单下所有子树
            //创建树
            var tree = new YAHOO.widget.TreeView("treediv");
            YAHOO.util.Event.on("expand", "click", function(e) {
                YAHOO.log("Expanding all TreeView  nodes.", "info", "example");
                tree.expandAll();
                YAHOO.util.Event.preventDefault(e);
            });

            //handler for collapsing all nodes
            YAHOO.util.Event.on("collapse", "click", function(e) {
                YAHOO.log("Collapsing all TreeView  nodes.", "info", "example");
                tree.collapseAll();
                YAHOO.util.Event.preventDefault(e);
            });

            for(var i=0;i<treeList.length;i++){
                var tree_data=treeList[i];//得到树信息
                tree_data=js$util.urlReg.initUrl(tree_data);
                var tmpNode = new YAHOO.widget.TextNode(tree_data.name, tree.getRoot(), false);//初始化树 (树名称,父树,是否打开)
                if(tree_data.scope!=""){
                    if(typeof $debug != 'undefined' && js$util.lang.isNotEmpty($debug) && $debug.isDebug){//开发调试模式
                        tree_data.servernm=$debug.config.servernm;
                        tree_data.prot=$debug.config.prot;
                        tree_data.protocol=$debug.config.protocol;
                        tree_data.subsyproject=$debug.config.subsyproject;
                    }
                    var tpurl=js$util.urlReg.getUrlByTree(tree_data);
                    if(js$util.urlReg.isThisDomain(tree_data)){//在同一项目路径下
                        if(tree_data.opentp==""){
                            tmpNode.href ="javascript:PanelInit('"+tpurl+"','iframe','"+tree_data.pcode+"','',false);dspMsg();";//
                        }else{
                            tmpNode.href ="javascript:PanelInit('"+tpurl+"','"+tree_data.opentp+"','"+tree_data.pcode+"','',false);dspMsg();";//
                        }
                        tmpNode.labelStyle="icon";
                    }else{
                        //var forwordurl=js$util.urlReg.getForwordUrl(tree_data);
                        if(nodecode==tree_data.pcode){
                            currentLocaction$util.dealCurrentLocationForTree(tmpNode);
                            PanelInit(tpurl,'',tree_data.pcode,'','',true);
                        }
                        if(tree_data.opentp==""){
                            tmpNode.href ="javascript:PanelInit('"+tpurl+"','iframe','"+tree_data.pcode+"','','',true);dspMsg();";//
                        }else{
                            tmpNode.href ="javascript:PanelInit('"+tpurl+"','"+tree_data.opentp+"','"+tree_data.pcode+"','','',true);dspMsg();";//
                        }
                        tmpNode.labelStyle="icon";
                    }
                }
                this.buildRandomTextBranch(tmpNode,tree_data.childList,nodecode);
            }
            tree.draw();
            Event.onDOMReady(function(e) {
                tree.collapseAll();
                YAHOO.util.Event.preventDefault(e);
            });
            // By default, trees with TextNodes will fire an event for when the label is clicked:
            tree.subscribe("labelClick", function(node) {
                currentLocaction$util.dealCurrentLocationForTree(node);
            });

        },
        buildRandomTextBranch:function(node,child,nodecode){
            if(child){
                for ( var i = 0; i < child.length ; i++ ) {
                    var ftree=child[i];
                    var tmpNode = new YAHOO.widget.TextNode(ftree.name, node, false);
                    tmpNode.expand();

                    if(typeof $debug != 'undefined' && js$util.lang.isNotEmpty($debug) && $debug.isDebug){//开发调试模式
                        ftree.servernm=$debug.config.servernm;
                        ftree.prot=$debug.config.prot;
                        ftree.protocol=$debug.config.protocol;
                        ftree.subsyproject=$debug.config.subsyproject;
                    }
                    if(ftree.scope!=""){
                        ftree=js$util.urlReg.initUrl(ftree);
                        var  tpurl=js$util.urlReg.getUrlByTree(ftree);
                        if(js$util.urlReg.isThisDomain(ftree)){//在同一项目路径下
                            if(ftree.opentp==""){
                                tmpNode.href ="javascript:PanelInit('"+tpurl+"','iframe','"+ftree.pcode+"','',false);dspMsg();";
                            }else{
                                tmpNode.href ="javascript:PanelInit('"+tpurl+"','"+ftree.opentp+"','"+ftree.pcode+"','',false);dspMsg();";
                            }
                            tmpNode.labelStyle="icon";
                        }else{
                            //var forwordurl=js$util.urlReg.getForwordUrl(ftree);
                            if(nodecode==ftree.pcode){
                                node.expand();
                                currentLocaction$util.dealCurrentLocationForTree(tmpNode);
                                PanelInit(tpurl,'',ftree.pcode,'','',true);
                            }
                            if(ftree.opentp==""){
                                tmpNode.href ="javascript:PanelInit('"+tpurl+"','iframe','"+ftree.pcode+"','','',true);dspMsg();";
                            }else{
                                tmpNode.href ="javascript:PanelInit('"+tpurl+"','"+ftree.opentp+"','"+ftree.pcode+"','','',true);dspMsg();";
                            }
                            tmpNode.labelStyle="icon";
                        }
                    }
                    this.buildRandomTextBranch(tmpNode,ftree.childList,nodecode);
                }
            }
        }
    }
}


//表格的一些功能

var $sy_render_error=function(mygrid){

    //解决翻页删除 编辑bug
    mygrid.updateEditState=function(){

    }
    mygrid.getTwinRows=function(C){
        var _=C,
        B=C.rowIndex<0?0 :C.rowIndex<0,
        $=!_.id?this.gridTbodyList[0]:this.freezeBodyTable.tBodies[0],
        A=$?$.rows[B]:null;
        if(!A&&$&&$.parentNode.tFoot)A=$.parentNode.tFoot.rows[B-$.rows.length];
        return _.id?[_,A,B]:[A,_,B]
    }

}
var $sy_render_fun_bps=function(mygrid,ifSpecialForCheck,crossDomain){
    document.getElementById("last_container_height").value="";
    document.getElementById("max_condition_height").value="";
    AppResize();

    $pageconfig.tables[mygrid.id]=mygrid;
    if(ifSpecialForCheck){
        Sigma.checkOneBox=BSigma.checkOneBox;
        Sigma.Grid.createCheckColumn=BSigma.Grid.Column.createCheckColumn;
    }
    WNSigma.GridDefault.crossDomain=crossDomain;
    Sigma.GridDefault.request=WNSigma.GridDefault.request;
    Sigma.Utils.CSS=FixSigmaBug.FixHeadCss;
    Sigma.GridDefault.render=function($){
        if(!this.rendered){
            $=Sigma.getDom($);
            this.container=$||this.container;
            this.initColumns();
            this.initCSS();
            this.createMain();
            this.createGridGhost();
            this.initToolbar();
            this.initMainEvent();
            this.createBody();
            this.rendered=true
        }
        return this
    }
    mygrid.render();
    //解决翻页删除 编辑bug
    mygrid.updateEditState=function(){
    }
    mygrid.getTwinRows=function(C){
        var _=C,
        B=C.rowIndex<0?0 :C.rowIndex<0,
        $=!_.id?this.gridTbodyList[0]:this.freezeBodyTable.tBodies[0],
        A=$?$.rows[B]:null;
        if(!A&&$&&$.parentNode.tFoot)A=$.parentNode.tFoot.rows[B-$.rows.length];
        return _.id?[_,A,B]:[A,_,B]
    }
}

var $sy_render_fun=function(mygrid){
    //保存表格数据 要在下一个页面跳转时清空该项目所有变量
    $pageconfig.tables[mygrid.id]=mygrid;
    mygrid.render();
    //解决翻页删除 编辑bug
    mygrid.updateEditState=function(){
    }
    mygrid.getTwinRows=function(C){
        var _=C,
        B=C.rowIndex<0?0 :C.rowIndex<0,
        $=!_.id?this.gridTbodyList[0]:this.freezeBodyTable.tBodies[0],
        A=$?$.rows[B]:null;
        if(!A&&$&&$.parentNode.tFoot)A=$.parentNode.tFoot.rows[B-$.rows.length];
        return _.id?[_,A,B]:[A,_,B]
    }

}
//var sigmaGrid$util={
//    ifMultiSigma:null,
//    crossDomain:false,
//    sigmagrid:null,
//    basepath:'',
//    gridid:null,
//    ifSpecialForCheck:false,
//    config:{
//        fields :null,
//        colsOption:null,
//        gridOption:null
//    },
//    sigmagrids:null,
//    configs:null,
//    firstLoad:true,
//    checkedRecords:[],
//    setCrossDomain:function(m){
//        sigmaGrid$util.crossDomain=m;
//    },
//    setBasepath:function(m){
//        sigmaGrid$util.basepath=m;
//    },
//    getGridId:function(){
//        var o =   sigmaGrid$util.sigmagrid;
//        return o;
//    },
//    getGrid:function(){
//        var o = sigmaGrid$util.sigmagrid;
//        return o;
//    },
//    getGridByIdForMultiSigma:function(sigid){
//        var o = sigmaGrid$util.sigmagrids[sigid];
//        return o;
//    },
//    getRadioValue:function(radioName)
//    {
//        var radios = document.getElementsByName(radioName);
//        for(var i = 0 ; i< radios.length;i++  )
//        {
//            var radio = radios.item(i);
//            if(radio.checked)
//            {
//                return radio.value;
//            }
//        }
//        return null;
//    },
//
//    destroy:function(){
//        sigmaGrid$util.ifMultiSigma=null;
//        sigmaGrid$util.sigmagrid=null;
//        sigmaGrid$util.sigmagrids=null;
//        sigmaGrid$util.configs=null;
//        sigmaGrid$util.gridid=null;
//        sigmaGrid$util.checkedRecords=[];
//        sigmaGrid$util.config.fields=null;
//        sigmaGrid$util.config.colsOption=null;
//        sigmaGrid$util.config.gridOption=null;
//        sigmaGrid$util.ifSpecialForCheck=false;
//        sigmaGrid$util.crossDomain=false;
//        sigmaGrid$util.basepath='';
//    },
//    setClientDsOption:function(m){
//        sigmaGrid$util.config.fields=m;
//    },
//    setMultiClientDsOption:function(sigmamark,m){
//        if(sigmaGrid$util.configs==null){
//            sigmaGrid$util.configs={};
//        }
//        if(typeof sigmaGrid$util.configs[sigmamark]=="undefined"){
//            sigmaGrid$util.configs[sigmamark]={};
//        }
//        if(typeof sigmaGrid$util.configs[sigmamark]["fields"]=="undefined"){
//            sigmaGrid$util.configs[sigmamark]["fields"]={};
//        }
//        sigmaGrid$util.configs[sigmamark]["fields"]=m;
//    },
//    setClientcolsOption:function(m){
//        sigmaGrid$util.config.colsOption=m
//    },
//    setMultiClientcolsOption:function(sigmamark,m){
//        if(sigmaGrid$util.configs==null){
//            sigmaGrid$util.configs={};
//        }
//        if(typeof sigmaGrid$util.configs[sigmamark]=="undefined"){
//            sigmaGrid$util.configs[sigmamark]={};
//        }
//        if(typeof sigmaGrid$util.configs[sigmamark]["colsOption"]=="undefined"){
//            sigmaGrid$util.configs[sigmamark]["colsOption"]={};
//        }
//        sigmaGrid$util.configs[sigmamark]["colsOption"]=m;
//    },
//    setClientgridOption:function(m){
//        sigmaGrid$util.config.gridOption=m
//    },
//    setMultiClientgridOption:function(sigmamark,m){
//        if(sigmaGrid$util.configs==null){
//            sigmaGrid$util.configs={};
//        }
//        if(typeof sigmaGrid$util.configs[sigmamark]=="undefined"){
//            sigmaGrid$util.configs[sigmamark]={};
//        }
//        if(typeof sigmaGrid$util.configs[sigmamark]["gridOption"]=="undefined"){
//            sigmaGrid$util.configs[sigmamark]["gridOption"]={};
//        }
//        sigmaGrid$util.configs[sigmamark]["gridOption"]=m;
//    },
//    initSigmaGrid:function(){
//        var sigma_config_div = null;
//        if(sigma_config_div!=null && typeof sigma_config_div!="undefined"){
//
//            var   sigma_config_divResText = sigma_config_div.innerHTML;
//            sigma_config_divResText = sigma_config_divResText.replace(/(?:<script.*?>)/i,'').replace(/(?:<\/script>)/i,'');
//            // var resObj = eval(sigma_config_divResText);
//            //js$util.script.globalEval(sigma_config_divResText);
//            js$util.script.globalEvalDynamicImport( sigma_config_divResText,"script" );
//            // resObj = null;
//            if(sigmaGrid$util.ifMultiSigma==null){
//                var dsOption= {
//                    fields :sigmaGrid$util.config.fields.fields,
//                    recordType : 'object'
//                };
//                sigmaGrid$util.gridid="sigmaGrid";
//                var gridOption={
//                    id : sigmaGrid$util.gridid,
//                    width: "100%",
//                    height: "100%",
//                    container : 'result_table_div',
//                    replaceContainer : false,
//                    dataset : dsOption ,
//                    columns : sigmaGrid$util.config.colsOption,
//                    skin: "srm",
//                    pageSize : 15 ,
//                    pageSizeList : [15,30],
//                    remotePaging : true,
//                    toolbarPosition : 'top',
//                    loadFailure:function(requestParameter){
//                        $Dom.get("errmsg").innerHTML=requestParameter.msg;
//                        $Dom.get("errmsg").style.display="";
//                        showMsg();
//                    }
//
//                };
//                //注入用户自定义gridOption
//                for(var o in sigmaGrid$util.config.gridOption){
//                    gridOption[o]=sigmaGrid$util.config.gridOption[o];
//                }
//                //gridOption["loadURL"]=sigmaGrid$util.basepath+gridOption["loadURL"];
//                //alert(gridOption["loadURL"]);
//                sigmaGrid$util.sigmagrid=new Sigma.Grid(gridOption);
//                $sy_render_error(sigmaGrid$util.sigmagrid);
//                if(sigmaGrid$util.ifSpecialForCheck){
//                    $sy_render_fun_bps(sigmaGrid$util.sigmagrid,sigmaGrid$util.ifSpecialForCheck,sigmaGrid$util.crossDomain);
//                }else{
//                    $sy_render_fun_bps(sigmaGrid$util.sigmagrid,'',sigmaGrid$util.crossDomain);
//                }
//                YAHOO.util.Event.removeListener(window,'resize');//add liujie
//                YAHOO.util.Event.addListener(window, 'resize',function(e){
//                    if(resizeTimer) clearTimeout(resizeTimer);
//                    resizeTimer = setTimeout("AppResize();sigmaGrid$util.sigmagrid._onResize();sigma_border();",1);
//                //                    AppResize();
//                //                    sigmaGrid$util.sigmagrid._onResize();
//                });
//            }else{
//
//
//                for(var conf in sigmaGrid$util.configs){
//                    var tempdiv =document.createElement("div");
//                    tempdiv.setAttribute("id", 'result_table_div'+conf);
//                    var result_table_div= document.getElementById("result_table_div").appendChild(tempdiv);
//                    var dsOption= {
//                        fields :sigmaGrid$util.configs[conf].fields.fields,
//                        recordType : 'object'
//                    };
//                    var gridOption={
//                        id : conf,
//                        width: "100%",
//                        height: "400",
//                        container : 'result_table_div'+conf,
//                        replaceContainer : false,
//                        dataset : dsOption ,
//                        columns : sigmaGrid$util.configs[conf].colsOption,
//                        skin: "srm",
//                        pageSize : 15 ,
//                        pageSizeList : [15,30],
//                        remotePaging : true,
//                        toolbarPosition : 'top',
//                        loadFailure:function(requestParameter){
//                            $Dom.get("errmsg").innerHTML=requestParameter.msg;
//                            $Dom.get("errmsg").style.display="";
//                            showMsg();
//                        }
//                    };
//                    //注入用户自定义gridOption
//                    for(var o in sigmaGrid$util.configs[conf].gridOption){
//                        gridOption[o]=sigmaGrid$util.configs[conf].gridOption[o];
//                    }
//                    if(sigmaGrid$util.sigmagrids==null){
//                        sigmaGrid$util.sigmagrids={};
//                    }
//                    sigmaGrid$util.sigmagrids[conf]=new Sigma.Grid(gridOption);
//                    $sy_render_error(sigmaGrid$util.sigmagrids[conf]);
//                    $sy_render_fun_bps(sigmaGrid$util.sigmagrids[conf],'',sigmaGrid$util.crossDomain);
//                    YAHOO.util.Event.removeListener(window,'resize');//add liujie
//
//                    YAHOO.util.Event.addListener(window, 'resize',function(e){
//                        if(resizeTimer) clearTimeout(resizeTimer);
//                        resizeTimer = setTimeout("AppResize();sigmaGrid$util.sigmagrids[conf]._onResize();",1);
//                        sigmaGrid$util.sigmagrids[conf]._onResize();
//                    });
//                }
//            }
//            Dom.setStyle(Dom.get('show_hide'), 'display', '');
//            if(resizeTimer) clearTimeout(resizeTimer);
//            resizeTimer = setTimeout("sigma_border();",1);
//        }else{
//            Dom.setStyle(Dom.get('show_hide'), 'display', 'none');                //当无SIGMA是隐藏显示查询的条件的按钮
//            YAHOO.util.Event.removeListener(window,'resize');
//            if(g_ie_version == 6){
//                ResizeApp();
//                YAHOO.util.Event.addListener(window, 'resize',function(e){
//                    ResizeApp();
//                });
//            }
//        }
//    //  load$Page.preRequest=YCM.asyncRequest('POST', webserver+"/public/js/srm/"+pcode+"_sigmaconf.js", callbackSigmaConf);
//    },
//    onCompleteFunction:function(grid,gridid,primarykey){
//        if(sigmaGrid$util.checkedRecords.length>0){
//            //循环sigma中所有的行
//            sigmaGrid$util.sigmagrid.forEachRow(function(row,record,i,grid){
//                //循环records2中所有的值
//                for(var i=0;i<sigmaGrid$util.checkedRecords.length;i++){
//                    //判断records2中是否有与sigma相同的值
//                    if(sigmaGrid$util.checkedRecords[i][primarykey]==record[primarykey]){
//                        //有相同的值就选中一行
//                        sigmaGrid$util.sigmagrid.checkRow(row,true);
//                    }
//                }
//            });
//        }
//    },
//    beforeLoadFunction:function(requestParameter,gridid,primarykey){
//        if(sigmaGrid$util.sigmagrid.getSelectedRecords().length>0){
//            var thispageCheckedRecord=sigmaGrid$util.sigmagrid.getSelectedRecords();
//
//            for(var i=0;i<thispageCheckedRecord.length;i++){
//
//                if(i!=0 && thispageCheckedRecord[i]["__gt_row_key__"]==thispageCheckedRecord[i-1]["__gt_row_key__"]){
//
//                }else{
//
//                    var bool=false;
//                    //判断Sigma选中的是否在records2存在
//                    for(var j=0;j<sigmaGrid$util.checkedRecords.length;j++){
//                        if(sigmaGrid$util.checkedRecords[j][primarykey]==thispageCheckedRecord[i][primarykey])
//                            bool=true;
//                    }
//                    //如果不存在把选中的添加到records2中
//                    if(!bool)
//                        sigmaGrid$util.checkedRecords[sigmaGrid$util.checkedRecords.length]=thispageCheckedRecord[i];
//
//                }
//            }
//
//        }
//    },
//    onCellClickFunction:function(  value,  record,  cell,  row,  colNo,  columnObj,  grid,gridid,primarykey){
//        //点击第一列的checkBox
//        if(colNo==0)
//        {
//            var bool=false;
//            var bool2=false;
//            var node=0;//记录records2中的下标
//            //该循环判断点击checkBox,该行的record在records2中是否存在，如果存在记录records2的下标
//            for(var i=0;i<sigmaGrid$util.checkedRecords.length;i++){
//                if(record !=undefined && record !="undefined" && record!="" &&  record!=null && sigmaGrid$util.checkedRecords[i][primarykey]==record[primarykey]){
//                    bool=true;
//                    node=i;
//                }
//
//            }
//            //获取Sigma中所有选中的行
//            var allselected=sigmaGrid$util.sigmagrid.getSelectedRecords();
//            //该循环判断点击checkBox,该行的record是否是选中的行
//            for(var j=0;j<allselected.length;j++){
//                if(bool){
//                    if(allselected[j][primarykey]==record[primarykey])
//                    {
//                        bool2=true;
//                    }
//                }
//            }
//            //“!bool2&&bool”如果是true则说明该行原先是被选中的，而此次点击之后则未被选中，所以要删除records2中的该行
//            if(!bool2&&bool){
//
//                for(var v=node;v<sigmaGrid$util.checkedRecords.length;v++){
//                    sigmaGrid$util.checkedRecords[v]=sigmaGrid$util.checkedRecords[v+1];
//                }
//                sigmaGrid$util.checkedRecords.length=sigmaGrid$util.checkedRecords.length-1;
//            }
//        }
//    },
//    dealCheckedRecords:function(gridid,primarykey){
//        if(sigmaGrid$util.sigmagrid.getSelectedRecords().length>0){
//
//            var records1=sigmaGrid$util.sigmagrid.getSelectedRecords();
//            for(var i=0;i<records1.length;i++){
//                if(i!=0 && records1[i]["__gt_row_key__"]==records1[i-1]["__gt_row_key__"]){
//
//                }else{
//                    var bool=false;
//                    for(var j=0;j<sigmaGrid$util.checkedRecords.length;j++){
//                        if(sigmaGrid$util.checkedRecords[j][primarykey]==records1[i][primarykey])
//                            bool=true;
//                    }
//                    if(!bool)
//                        sigmaGrid$util.checkedRecords[sigmaGrid$util.checkedRecords.length]=records1[i];
//                }
//            }
//        }
//    },
//    saveTable:function (gridid,primarykey,pinput){
//        if(sigmaGrid$util.sigmagrid.getSelectedRecords().length>0){
//
//            var records1=sigmaGrid$util.sigmagrid.getSelectedRecords();
//            for(var i=0;i<records1.length;i++){
//                if(i!=0 && records1[i]["__gt_row_key__"]==records1[i-1]["__gt_row_key__"]){
//
//                }else{
//                    var bool=false;
//                    for(var j=0;j<sigmaGrid$util.checkedRecords.length;j++){
//                        if(sigmaGrid$util.checkedRecords[j][primarykey]==records1[i][primarykey])
//                            bool=true;
//                    }
//                    if(!bool)
//                        sigmaGrid$util.checkedRecords[sigmaGrid$util.checkedRecords.length]=records1[i];
//                }
//            }
//        }
//        var returnValue="";
//        if(sigmaGrid$util.checkedRecords.length>0){
//            var sign=";";
//            for(var k=0;k<sigmaGrid$util.checkedRecords.length;k++){
//                if(returnValue==""){
//                    returnValue=sigmaGrid$util.checkedRecords[k][primarykey];
//                }else{
//                    returnValue=returnValue+sign+sigmaGrid$util.checkedRecords[k][primarykey];
//                }
//
//            }
//
//        }else{
//            $Dom.get("errmsg").innerHTML=_BYD_SRM_LG.qxz;
//            $Dom.get("errmsg").style.display = '';
//        }
//
//    },
//    getCheckedRecords:function(gridid,primarykey){
//        if(sigmaGrid$util.sigmagrid.getSelectedRecords().length>0){
//
//            var records1=sigmaGrid$util.sigmagrid.getSelectedRecords();
//            for(var i=0;i<records1.length;i++){
//                if(i!=0 && records1[i]["__gt_row_key__"]==records1[i-1]["__gt_row_key__"]){
//
//                }else{
//                    var bool=false;
//                    for(var j=0;j<sigmaGrid$util.checkedRecords.length;j++){
//                        if(sigmaGrid$util.checkedRecords[j][primarykey]==records1[i][primarykey])
//                            bool=true;
//                    }
//                    if(!bool)
//                        sigmaGrid$util.checkedRecords[sigmaGrid$util.checkedRecords.length]=records1[i];
//                }
//            }
//        }
//        return sigmaGrid$util.checkedRecords;
//    },
//    querySigmaGrid:function(clientQueryOption){
//        sigmaGrid$util.sigmagrid.cleanParameters();
//        for(var i = 0; i < clientQueryOption.queryCondition.length ; i++ ){
//            if(clientQueryOption.queryCondition[i].hasOwnProperty("type")){
//                var queryConditionType = clientQueryOption.queryCondition[i]["type"];
//                if(queryConditionType=="radio"){
//                    var radioValue = sigmaGrid$util.getRadioValue((clientQueryOption.queryCondition)[i].name);
//                    if(radioValue!=null){
//                        sigmaGrid$util.sigmagrid.addParameters((clientQueryOption.queryCondition)[i].paramid,radioValue);
//                    }
//
//                }
//            }
//            sigmaGrid$util.sigmagrid.addParameters((clientQueryOption.queryCondition)[i].id,Sigma.Util.getValue((clientQueryOption.queryCondition)[i].id));
//        }
//        var  pageinfo={
//            "pageSize":sigmaGrid$util.sigmagrid.navigator.pageInfo.pageSize,
//            "pageNum":1,
//            "startRowNum":1,
//            "endRowNum":-1
//        }
//        sigmaGrid$util.sigmagrid.setPageInfo(pageinfo);
//        sigmaGrid$util.sigmagrid.loadURL=clientQueryOption.loadURL;
//        //alert(sigmaGrid$util.sigmagrid.loadURL)
//        sigmaGrid$util.sigmagrid.reload();
//    }
//}
//try{
//    //注册sigma表格 自定义保存
//    Sigma.ToolFactroy.register(
//        '$sy_save',
//        {
//            cls : 'gt-tool-save',
//            toolTip :_BYD_VMI_LG.xuanzetianxiang,
//            action : function(event,grid) {
//                grid.$selectSaveAction();
//            }
//        }
//        );
//    //注册sigma表格 自定义的打开新的页面添加记录
//    Sigma.ToolFactroy.register(
//        '$sy_selectadd',
//        {
//            cls : 'gt-tool-add',
//            toolTip :_BYD_VMI_LG.xuanzetianxiang,
//            action : function(event,grid) {
//                grid.$selectAddAction();
//            }
//        }
//        );
//    //注册sigma表格 自定义的URL导出到excel
//    Sigma.ToolFactroy.register(
//        '$sy_gotoexcel',
//        {
//            cls : 'gt-tool-xls',
//            toolTip :_BYD_VMI_LG.daochudaoexcel,
//            action : function(event,grid) {
//                var tm=grid.getPageInfo().totalRowNum;//是否有记录
//                if(tm>0){
//                    var filFig=grid.getFilterInfo();
//                    var urlfig=grid.$sy_configToExcelUrl;
//                    var url=grid.$sy_toExcelUrl;
//                    for(var i=0;i<filFig.length;i++){
//                        var fil=filFig[i];
//                        for(var j=0;j<urlfig.length;j++){//拼装打印URL
//                            var fig=urlfig[j];
//                            var tmfilterKey=fig.filterKey;
//                            if(tmfilterKey==fil.columnId ){
//                                //var tlogic= getCompare(filFig.logic);//得到比较符号
//                                url=url+fig.joinsign+fig.urlkey+getCompare(fil.logic)+fil.value;
//
//                            }
//                        }
//                    }
//                    window.open(url);
//                }
//            }
//        }
//        );
//
//    //注册sigma表格 自定义的打印
//
//    Sigma.ToolFactroy.register(
//        '$sy_print',
//        {
//            cls : 'gt-tool-print',
//            toolTip :_BYD_VMI_LG.dayin,
//            action : function(event,grid) {
//
//                var tm=grid.getPageInfo().totalRowNum;//是否有记录
//                if(tm>0){
//                    var filFig=grid.getFilterInfo();
//                    var urlfig=grid.$sy_configUrl;
//                    var url=grid.$sy_printUrl;
//                    for(var i=0;i<filFig.length;i++){
//                        var fil=filFig[i];
//                        for(var j=0;j<urlfig.length;j++){//拼装打印URL
//                            var fig=urlfig[j];
//                            var tmfilterKey=fig.filterKey;
//                            if(tmfilterKey==fil.columnId ){
//                                //var tlogic= getCompare(filFig.logic);//得到比较符号
//                                url=url+fig.joinsign+fig.urlkey+getCompare(fil.logic)+fil.value;
//
//                            }
//                        }
//                    }
//                    if(grid.$sy_OpenWinConfig){
//                        window.open(url,grid.$sy_OpenWinConfig[0],grid.$sy_OpenWinConfig[1]);
//                    }else{
//                        window.open(url);
//                    }
//
//                }
//            }
//        }
//        );
//}catch(e){
//
//}
//根据查询比较条件比较符号装换
function getCompare(logic){
    var tlogic="=";
    switch(logic){
        case "equal":
            tlogic="=";
            break;
        case "notEqual":
            tlogic="!=";
            break;
        case "less":
            tlogic="<";
            break;
        case "great":
            tlogic=">";
            break;
        case "lessEqual":
            tlogic="<=";
            break;
        case "greatEqual":
            tlogic=">=";
            break;
        default :
            tlogic=logic;
    }
    return tlogic;
}






/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function obj2str(o){
    var r = [];
    if(typeof o =="string") return "\""+o.replace(/([\'\"\\])/g,"\\$1").replace(/(\n)/g,"\\n").replace(/(\r)/g,"\\r").replace(/(\t)/g,"\\t")+"\"";
    if(typeof o =="undefined") return "";
    if(typeof o == "object"){
        if(o===null) return "null";
        else if(!o.sort){
            for(var i in o)
                r.push(i+":"+obj2str(o[i]))
            r="{"+r.join()+"}"
        }else{
            for(var i =0;i<o.length;i++)
                r.push(obj2str(o[i]))
            r="["+r.join()+"]"
        }
        return r;
    }
    return o.toString();
}


function addOption(objSelect,strName,strvalue){
    // 建立Option对象
    var objOption = new Option(strName,strvalue);
    objOption.value=strvalue;
    objOption.text= strName;

    objSelect.add(objOption, null);
}
// 删除选项
function deleteOption(objSelect,type){

    if (type == true)
        objSelect.length = 0;
    else
        objSelect.remove(objSelect.selectedIndex);
}

function resizeSigma(){
    sigmaGrid$util.sigmagrid._onResize();
}
var currentLocaction$util={
    dealCurrentLocationForTree:function(node){
        var locationName = currentLocaction$util.getFullTreeNodeLocation(node);
        var currentlocation  = document.getElementById("currentlocation");
        if(currentlocation!=null){
            var oldvalue = currentlocation.innerHTML;
            if(oldvalue.indexOf("-")>0){
                var dealedvalue = oldvalue.substring(0,oldvalue.indexOf("-"));
                currentlocation.innerHTML=dealedvalue+"-"+locationName;
            }else{
                currentlocation.innerHTML=oldvalue+"-"+locationName;
            }
        }
        if(g_ie_version == 6){
            Dom.setStyle(Dom.get('title'), 'width',document.getElementById("iframeDiv").offsetWidth);
        }
    },
    getFullTreeNodeLocation:function(node){
        var templabel = node.label;
        if(typeof node.parent=="undefined" || typeof node.parent.label=="undefined" || node.parent.label==""){
            return templabel;
        }else{
            return this.getFullTreeNodeLocation(node.parent)+"-"+templabel;
        }
    },
    dealCurrentLocationForMenu:function(){
        var subMenu = document.getElementById("subMenu");
        var currentlocation  = document.getElementById("currentlocation");
        if(currentlocation!=null){
            for(var i=0;i<subMenu.childNodes.length;i++)
            {
                if(typeof subMenu.childNodes[i].childNodes[0] !="undefined"){
                    var activeSubMenu = subMenu.childNodes[i].childNodes[0];
                    var activeSubMenuClass = activeSubMenu.className;
                    var oldvalue;
                    if (g_ie_version == 6){
                        if(activeSubMenuClass=="actived-8"){
                            oldvalue = currentlocation.innerHTML;
                            if(oldvalue.indexOf("-")<0){
                                currentlocation.innerHTML=oldvalue+activeSubMenu.innerHTML;
                            }
                        }
                    }else{
                        if(activeSubMenuClass=="actived"){
                            oldvalue = currentlocation.innerHTML;
                            if(oldvalue.indexOf("-")<0){
                                currentlocation.innerHTML=oldvalue+activeSubMenu.innerHTML;
                            }
                        }
                    }
                }
            }
        }
    }
}

var flag="";
function showMsg(){
    flag="msg";
    Dom.get("l_pic").setAttribute("src", webserver+"/public/img/wrong.jpg");
    if(Dom.getStyle(Dom.get("img_infomsg"), "display")!="none"){
        Dom.setStyle(Dom.get("img_infomsg"), "display","none");
    }
    Dom.setStyle(Dom.get("errorInfo"), "display", "");
    Dom.setStyle(Dom.get("img_errmsg"), "display", "");
    var height = document.getElementById("title").offsetHeight;
    YAHOO.util.Dom.setStyle("img_errmsg", "top", height+5+"px");
    if(g_ie_version == 6){
        YAHOO.util.Dom.setStyle("img_errmsg","width",Dom.getStyle("title","width"));
        Dom.setStyle(Dom.get("iframeDiv"), "top", "58px") ;
    }else{
        Dom.setStyle(Dom.get("iframeDiv"), "top", "55px") ;
    }
    var heightResult=window.frames["contentIframe"].document.getElementById("result_table_div").offsetHeight;
    if(g_ie_version == 7){
        Dom.setStyle(window.frames["contentIframe"].document.getElementById("sigmaGrid_bodyDiv"), "height", (heightResult-98)+"px" );
    }else if(g_ie_version == 6){
        Dom.setStyle(window.frames["contentIframe"].document.getElementById("sigmaGrid_bodyDiv"), "height", (heightResult-90)+"px" );
    }
    else{
        Dom.setStyle(window.frames["contentIframe"].document.getElementById("sigmaGrid_bodyDiv"), "height", (heightResult-87)+"px" );
    }
    if(resizeTimer) clearTimeout(resizeTimer);
    resizeTimer = setTimeout('hideMsg()',3000);
}

function hideMsg(){
    flag="msg";
    Dom.setStyle(Dom.get("errorInfo"), "display", "");
    Dom.get("l_pic").setAttribute("src", webserver+"/public/img/wrong.jpg");
    Dom.get("l_pic").setAttribute("title", _BYD_VMI_LG.xsxs);
    if(Dom.getStyle(Dom.get("img_infomsg"), "display")!="none"){
        Dom.setStyle(Dom.get("img_infomsg"), "display","none");
    }
    Dom.setStyle(Dom.get("img_errmsg"), "display", "none") ;
    var height = document.getElementById("title").offsetHeight;
    YAHOO.util.Dom.setStyle("img_errmsg", "top", "0px");
    Dom.setStyle(Dom.get("img_errmsg"), "display", "none") ;
    Dom.setStyle(Dom.get("iframeDiv"), "top", "32px") ;
    var heightResult=window.frames["contentIframe"].document.getElementById("result_table_div").offsetHeight;
    if(g_ie_version == 6){
        Dom.setStyle(window.frames["contentIframe"].document.getElementById("sigmaGrid_bodyDiv"), "height", (heightResult-64)+"px" );
    }
    if(g_ie_version == 7){
        Dom.setStyle(window.frames["contentIframe"].document.getElementById("sigmaGrid_bodyDiv"), "height", (heightResult-75)+"px" );
    }
}

function showInfo(){
    flag="info";
    Dom.get("l_pic").setAttribute("src", webserver+"/public/img/warn.jpg");
    if(Dom.getStyle(Dom.get("img_errmsg"), "display")!="none"){
        Dom.setStyle(Dom.get("img_errmsg"), "display","none");
    }
    Dom.setStyle(Dom.get("errorInfo"), "display", "") ;
    Dom.setStyle(Dom.get("img_infomsg"), "display", "") ;
    var height = document.getElementById("title").offsetHeight;
    YAHOO.util.Dom.setStyle("img_infomsg", "top", height+5+"px");
    if(g_ie_version == 6){
        YAHOO.util.Dom.setStyle("img_infomsg","width",Dom.getStyle("title","width"));
        Dom.setStyle(Dom.get("iframeDiv"), "top", "58px") ;
    }else{
        Dom.setStyle(Dom.get("iframeDiv"), "top", "55px") ;
    }
    var heightResult=window.frames["contentIframe"].document.getElementById("result_table_div").offsetHeight;
    if(g_ie_version == 7){
        Dom.setStyle(window.frames["contentIframe"].document.getElementById("sigmaGrid_bodyDiv"), "height", (heightResult-98)+"px" );
    }else if(g_ie_version == 6){
        Dom.setStyle(window.frames["contentIframe"].document.getElementById("sigmaGrid_bodyDiv"), "height", (heightResult-90)+"px" );
    }
    else{
        Dom.setStyle(window.frames["contentIframe"].document.getElementById("sigmaGrid_bodyDiv"), "height", (heightResult-87)+"px" );
    }
    if(resizeTimer) clearTimeout(resizeTimer);
    resizeTimer = setTimeout('hideInfo()',3000);
}
function hideInfo(){
    flag="info";
    Dom.setStyle(Dom.get("errorInfo"), "display", "");
    Dom.get("l_pic").setAttribute("src", webserver+"/public/img/warn.jpg");
    Dom.get("l_pic").setAttribute("title", _BYD_VMI_LG.xsxs);
    if(Dom.getStyle(Dom.get("img_errmsg"), "display")!="none"){
        Dom.setStyle(Dom.get("img_errmsg"), "display","none");
    }
    Dom.setStyle(Dom.get("img_infomsg"), "display", "none") ;
    var height = document.getElementById("title").offsetHeight;
    YAHOO.util.Dom.setStyle("img_infomsg", "top", "0px");
    Dom.setStyle(Dom.get("img_infomsg"), "display", "none") ;
    //    YAHOO.util.Dom.setStyle("nav_body_right_panel_content_div", "top", height+"px");
    //    }
    Dom.setStyle(Dom.get("iframeDiv"), "top", "32px") ;
    var heightResult=window.frames["contentIframe"].document.getElementById("result_table_div").offsetHeight;
    if(g_ie_version == 6){
        Dom.setStyle(window.frames["contentIframe"].document.getElementById("sigmaGrid_bodyDiv"), "height", (heightResult-64)+"px" );
    }
    if(g_ie_version == 7){
        Dom.setStyle(window.frames["contentIframe"].document.getElementById("sigmaGrid_bodyDiv"), "height", (heightResult-75)+"px" );
    }
}

function dspMsg()
{
    Dom.setStyle(Dom.get('show_hide'), 'display', '');
    if(Dom.getStyle(Dom.get("img_infomsg"), "display")!="none"){
        hideInfo();
    }
    if(Dom.getStyle(Dom.get("img_errmsg"), "display")!="none"){
        hideMsg();
    }
    if(Dom.getStyle(Dom.get("errorInfo"),"display")!="none"){
        Dom.setStyle(Dom.get("errorInfo"),"display","none");
    }
}


function hideMsgIcon(){
    Dom.setStyle(Dom.get("img_infomsg"), "display", "none");
    Dom.setStyle(Dom.get("img_errmsg"), "display", "none");
}

function addButton(id,value,fun){
    var test=document.getElementById("sigmaGrid_toolBarBox").childNodes;
    var flag=true;
    for (var i=0;i<test.length;i++){
        if(test[i].id==id){
            flag=false;
        }
    }
    if(flag==true){
        addEvent(id,fun);
        var  nodetest=document.getElementById("sigmaGrid_toolBarBox");
        var sunnode=document.createElement("div");
        sunnode.className="mySigmaCss";
        sunnode.id=id;
        sunnode.innerHTML="<button class='button_lee'><span><em>"+value+"</em></span></button>";
        nodetest.appendChild(sunnode);
    }
}

function addEvent(id,fun){
    YAHOO.util.Event.on(id, "click",fun);
}