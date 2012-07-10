/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var multiDialog = null;
function initMulti() {
    //590px
    multiDialog = new YAHOO.widget.Dialog("multiDialog",
    {
        width : "810px",
        height:"480px",
        fixedcenter : true,
        visible : false,
        modal:true,
        iframe:true,
        underlay:"shadow",
        close:true,
        closeaction:hideDialog ,
        constraintoviewport : true
    });
    multiDialog.render(document.body);

    var resize = new YAHOO.util.Resize("multiDialog", {
        handles: ["br"],
        autoRatio: false,
        minWidth: 300,
        minHeight: 100,
        status: false
    });

    resize.on("startResize", function(args) {

        if (this.cfg.getProperty("constraintoviewport")) {
            var D = YAHOO.util.Dom;

            var clientRegion = D.getClientRegion();
            var elRegion = D.getRegion(this.element);

            resize.set("maxWidth", clientRegion.right - elRegion.left - YAHOO.widget.Overlay.VIEWPORT_OFFSET);
            resize.set("maxHeight", clientRegion.bottom - elRegion.top - YAHOO.widget.Overlay.VIEWPORT_OFFSET);
        } else {
            resize.set("maxWidth", null);
            resize.set("maxHeight", null);
        }

    }, multiDialog, true);


    resize.on("resize", function(args) {
        var panelHeight = args.height;
        this.cfg.setProperty("height", panelHeight + "px");
    }, multiDialog, true);
}
//显示YUI对话框
function showDialog(url,title){
    document.getElementById("multiFrm").setAttribute("src", url);
    document.getElementById("dialogTitle").innerHTML=title;
    document.getElementById("multiDialog").style.display="";
    document.getElementById("multibd").style.height="435px";
    multiDialog.show();
}
var hideDialog=function hideDialog(){
    multiDialog.hide();
    document.getElementById("multiFrm").setAttribute("src", "javascript:''");
    document.getElementById("multiDialog").style.display="none";
}

function modewindow(url,title){
    showDialog(url,title);
}

YAHOO.util.Event.onDOMReady(initMulti);
function addSelectValue(record,pinput){
    window.frames["contentIframe"].document.getElementById(pinput).value=record;
}
Request = {
    QueryString : function(item){
        var svalue = location.search.match(new RegExp("[\?\&]" + item + "=([^\&]*)(\&?)","i"));
        return svalue ? svalue[1] : svalue;
    }
}

