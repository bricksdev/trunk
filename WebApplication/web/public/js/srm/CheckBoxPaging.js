/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var checkedRecords = [];
var onCompleteFunction=function(grid,gridid,primarykey){
    if(checkedRecords.length>0){
        //循环sigma中所有的行
        Sigma.$grid(gridid).forEachRow(function(row,record,i,grid){
            //循环records2中所有的值
            for(var i=0;i<checkedRecords.length;i++){
                //判断records2中是否有与sigma相同的值
                if(checkedRecords[i][primarykey]==record[primarykey]){
                    //有相同的值就选中一行
                    Sigma.$grid(gridid).checkRow(row,true);
                }
            }
        });
    }
}
var beforeLoadFunction=function(requestParameter,gridid,primarykey){
    if(Sigma.$grid(gridid).getSelectedRecords().length>0){
        var thispageCheckedRecord=Sigma.$grid(gridid).getSelectedRecords();

        for(var i=0;i<thispageCheckedRecord.length;i++){

            if(i!=0 && thispageCheckedRecord[i]["__gt_row_key__"]==thispageCheckedRecord[i-1]["__gt_row_key__"]){

            }else{

                var bool=false;
                //判断Sigma选中的是否在records2存在
                for(var j=0;j<checkedRecords.length;j++){
                    if(checkedRecords[j][primarykey]==thispageCheckedRecord[i][primarykey])
                        bool=true;
                }
                //如果不存在把选中的添加到records2中
                if(!bool)
                    checkedRecords[checkedRecords.length]=thispageCheckedRecord[i];

            }
        }

    }
}
var onCellClickFunction=function(  value,  record,  cell,  row,  colNo,  columnObj,  grid,gridid,primarykey){
    //点击第一列的checkBox
    if(colNo==0)
    {
        var bool=false;
        var bool2=false;
        var node=0;//记录records2中的下标
        //该循环判断点击checkBox,该行的record在records2中是否存在，如果存在记录records2的下标
        for(var i=0;i<checkedRecords.length;i++){
            if(record !=undefined && record !="undefined" && record!="" &&  record!=null && checkedRecords[i][primarykey]==record[primarykey]){
                bool=true;
                node=i;
            }

        }
        //获取Sigma中所有选中的行
        var allselected=Sigma.$grid(gridid).getSelectedRecords();
        //该循环判断点击checkBox,该行的record是否是选中的行
        for(var j=0;j<allselected.length;j++){
            if(bool){
                if(allselected[j][primarykey]==record[primarykey])
                {
                    bool2=true;
                }
            }
        }
        //“!bool2&&bool”如果是true则说明该行原先是被选中的，而此次点击之后则未被选中，所以要删除records2中的该行
        if(!bool2&&bool){

            for(var v=node;v<checkedRecords.length;v++){
                checkedRecords[v]=checkedRecords[v+1];
            }
            checkedRecords.length=checkedRecords.length-1;
        }
    }
}
function dealCheckedRecords(gridid,primarykey){
    if(Sigma.$grid(gridid).getSelectedRecords().length>0){

        var records1=Sigma.$grid(gridid).getSelectedRecords();
        for(var i=0;i<records1.length;i++){
            if(i!=0 && records1[i]["__gt_row_key__"]==records1[i-1]["__gt_row_key__"]){

            }else{
                var bool=false;
                for(var j=0;j<checkedRecords.length;j++){
                    if(checkedRecords[j][primarykey]==records1[i][primarykey])
                        bool=true;
                }
                if(!bool)
                    checkedRecords[checkedRecords.length]=records1[i];
            }
        }
    }
}
function saveTable(gridid,primarykey,pinput){
    if(Sigma.$grid(gridid).getSelectedRecords().length>0){

        var records1=Sigma.$grid(gridid).getSelectedRecords();
        for(var i=0;i<records1.length;i++){
            if(i!=0 && records1[i]["__gt_row_key__"]==records1[i-1]["__gt_row_key__"]){

            }else{
                var bool=false;
                for(var j=0;j<checkedRecords.length;j++){
                    if(checkedRecords[j][primarykey]==records1[i][primarykey])
                        bool=true;
                }
                if(!bool)
                    checkedRecords[checkedRecords.length]=records1[i];
            }
        }
    }
    var returnValue="";
    if(checkedRecords.length>0){
        var sign=";";
        for(var k=0;k<checkedRecords.length;k++){
            if(returnValue==""){
                returnValue=checkedRecords[k][primarykey];
            }else{
                returnValue=returnValue+sign+checkedRecords[k][primarykey];
            }

        }
        parent.addSelectValue(returnValue,pinput);
        parent.hideDialog();
    }else{
        window.parent.$Dom.get("errmsg").innerHTML=_BYD_SRM_LG.qxz;
        window.parent.$Dom.get("errmsg").style.display = '';
        showMsg();
    }
       
}
