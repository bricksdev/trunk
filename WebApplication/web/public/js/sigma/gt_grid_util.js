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
        alert(chkbox.disabled)
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
