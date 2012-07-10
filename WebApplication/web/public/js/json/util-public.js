/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 从一个table对象中过的一个json对象，例如：YAHOO.example.InlineCellEditing
 */

function gettablevalue(myObject){

    var tmpa=myObject.oDT.getRecordSet();
    //YAHOO.lang.JSON.isValid(myObject.oDT);
    var tbvalue = new Array();
    for(i=0;i<tmpa.getLength();i++){
        tbvalue.push(tmpa.getRecord(i)._oData);
    }
    
    var JSONDataSource={
        itemslist:tbvalue
    }
    
    return JSONDataSource.toJSONString();
}
function checkTableInput(table,arry){
    var flag=true;
    var mymsgtype=0;
    var msgcolumn="";
    var msgrow=0;
    var tmpa = table.oDT.getRecordSet();
    if(tmpa.getLength()<1){
        mymsgtype=-1;
        flag=false;
       return {
        Flag:flag,
        MyMsgType:mymsgtype
    };
    }else{
        var arraycolumns=table.oDC;
        for(i=0;i<tmpa.getLength();i++){
            record = tmpa.getRecord(i);
            if(arraycolumns==undefined){
                alert('please join the smyColumnDefs of table into table\'s return value');
                
                return { flag :false};
        }
            for(j=0;j<arraycolumns.length;j++){
                var  isNotCheck =true;
                for( k=0;k<arry.length;k++){
                    if(j==arry[k]){
                        isNotCheck=false;
                    }
                }
                if(isNotCheck && (record.getData(arraycolumns[j].key)==null || record.getData(arraycolumns[j].key)==""||record.getData(arraycolumns[j].key).length==0)){
                    mymsgtype=2;
                    msgrow=i+1;
                    msgcolumn=arraycolumns[j].label;//alert('<fmt:message key="MCreateDeliverTable1" bundle="${biaodan}"></fmt:message>'+(i+1)+'<fmt:message key="MCreateDeliverTable2" bundle="${biaodan}"></fmt:message>“'+arraycolumns[j].label+'”<fmt:message key="MCreateDeliverTable3" bundle="${biaodan}"></fmt:message>');
                    flag = false;
                  
                    return {
                        Flag:flag,
                        MyMsgType:mymsgtype,
                        MsgColumn:msgcolumn,
                        MsgRow:msgrow
                    };
                }

            }
        }

    }

    return {
        Flag:flag,
        MyMsgType:mymsgtype,
        MsgColumn:msgcolumn,
        MsgRow:msgrow
    };
}

