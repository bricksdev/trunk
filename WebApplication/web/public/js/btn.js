/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function testbutton() {
    if(confirm("送货单发送后将不能再更改或删除,请确认是否要发送？")){
        var url = contextpath +"/views/wms/stockManage/ProcessAsn.jsp?__METHOD=send";
        var grid = Sigma.$grid("sigmaGrid");

        var tbvalue ="";
        var isselect= grid.getSelectedRecords();
        for(var i=0;i<isselect.length;i++){
            var t1 = isselect[i]["ASNNO"];
            if(t1 == undefined || t1.replace(/(^\s*)|(\s*$)/g,"")=="") {
                t1 = " ";
            }
            tbvalue+=t1+",";
        }
        var contentValue = "key="+tbvalue;
        $ajax.request(url, contentValue);
        grid.reload(true,true);
    }
}

function deletebutton (){
    if(confirm("送货单删除后将不能通过此单发货，请确认是否要删除？")){
        var url = contextpath +"/views/wms/stockManage/ProcessAsn.jsp?__METHOD=del";
        var grid = Sigma.$grid("sigmaGrid");

        var tbvalue ="";
        var isselect= grid.getSelectedRecords();
        for(var i=0;i<isselect.length;i++){
            var t1 = isselect[i]["ASNNO"];
            if(t1 == undefined || t1.replace(/(^\s*)|(\s*$)/g,"")=="") {
                t1 = " ";
            }
            tbvalue+=t1+",";
        }
        var contentValue = "key="+tbvalue;
        $ajax.request(url, contentValue);
        grid.reload(true,true);
    }
}

function ExportExcelFile(){
    alert("导出数据！");
}

function BatchImportbutton(){
    parent.window.location.href = "/wms_web/views/wms/stockManage/ImportMaterialsForNorth.jsp";
}

function wmouinvoiceDelBtn(){
    if(confirm("发货单删除后将不能使用此单，请确认是否要删除？")){
        var url = contextpath +"/views/wms/stockManage/DeleteInvoices?__METHOD=delete";
        var grid = Sigma.$grid("sigmaGrid");

        var tbvalue ="";
        var isselect= grid.getSelectedRecords();
        for(var i=0;i<isselect.length;i++){
            var t1 = isselect[i]["PICKID"];
            var status = isselect[i]["STATUS"];//STATUS
            if(status!='1'){
                return false;
            }
            if(t1 == undefined || t1.replace(/(^\s*)|(\s*$)/g,"")=="") {
                t1 = " ";
            }
            tbvalue+=t1+";";
        }
        var contentValue = "pickids="+tbvalue;
        $ajax.request(url, contentValue);
        grid.reload(true,true);
    }
}

function CloseBtn() {
    if(confirm("发货单关闭后将不能使用此单，请确认是否要关闭？")){
        var url = contextpath +"/views/wms/stockManage/DeleteInvoices?__METHOD=close";
        var grid = Sigma.$grid("sigmaGrid");

        var tbvalue ="";
        var isselect= grid.getSelectedRecords();
        for(var i=0;i<isselect.length;i++){
            var t1 = isselect[i]["PICKID"];
            if(t1 == undefined || t1.replace(/(^\s*)|(\s*$)/g,"")=="") {
                t1 = " ";
            }
            tbvalue+=t1+";";
        }
        var contentValue = "pickids="+tbvalue;
        $ajax.request(url, contentValue);
        grid.reload(true,true);
    }
}
