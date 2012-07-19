<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />                              <!--语言及字符集-->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />                              <!--语言及字符集-->

    <script type="text/javascript">

        function attachEventFunc(objName){
            var obj = document.getElementById(objName);
            if(obj == null){
                var objs = document.getElementsByName(objName);
                for(var i = 0; i < objs.length; i++){
                    if(objs[i].type == "text"){
                        obj = objs[i];
                        bydToUpperCase(obj);
                        bydTrim(obj);
                    }
                }
            }else{
                bydToUpperCase(obj);
                bydTrim(obj);
            }
        }
        function bydOnLoad(){
            try{
                var eles = document.getElementsByTagName("INPUT");
                var blurAttr = null;
                for(var i = 0; i < eles.length; i++){
                    if(eles[i].type == "text"){

                        var obj = eles[i];

                        if(obj.style.display == "none" || obj.readOnly){
                            continue;
                        }

                        var idKey = obj.id;
                        if((idKey == null || idKey == "") || (idKey != "" && obj.name != "" && idKey != obj.name)){
                            idKey = obj.name;
                        }
                        if(obj == null || typeof idKey == "undenfined"){
                            continue;
                        }
                        blurAttr = eles[i].getAttribute("onblur")
                        var attachFunc = null;
                        if(blurAttr == null || blurAttr == ""){

                            attachFunc = function(srcObj){
                                var objTemp = srcObj.srcElement ? srcObj.srcElement : srcObj.target;
                                attachEventFunc(objTemp.name);
                            };
                        }else{
                            attachFunc = function(srcObj){
                                var objTemp = srcObj.srcElement ? srcObj.srcElement : srcObj.target;
                                attachEventFunc(objTemp.name);
                                blurAttr;
                            };
                        }
                        if(window.addEventListener){
                            eles[i].addEventListener('blur', attachFunc, false);
                        }else{
                            eles[i].attachEvent("onblur", attachFunc);
                        }
                        //                      mvc_attachEvent(obj, "blur", attachFunc(idKey));
                    }
                }
            }catch(e){
                // alert(e.description);
            }
        }

        function bydTrim(obj){

            if(obj == null || typeof obj == "undefined"){
                return;
            }
            obj.value = obj.value.replace(/(^\s*)|(\s*$)/g,"");
        }

        var exceptFilds = "materialDesc";
        function bydToUpperCase(obj){
            if(obj == null || typeof obj == "undefined"){
                return;
            }
            var objValue = obj.value;
            var isUpper = objValue.match(/[^0-9a_z]*/g);
            var idKey = obj.id;
            if((idKey == null || idKey == "") || (idKey != "" && obj.name != "" && idKey != obj.name)){
                idKey = obj.name;
            }
            if(isUpper != null){
                if(exceptFilds.indexOf(idKey) == -1){
                    obj.value = obj.value.toUpperCase();
                }
            }
        }
        if(window.addEventListener){
            window.addEventListener("load", bydOnLoad, false);
        }else{
            window.attachEvent("onload", bydOnLoad);
        }


        function openWin(src, width, height, showScroll){
            window.open(src,"","location:No;status:No;help:No;dialogWidth:"+width+";dialogHeight:"+height+";scroll:"+showScroll+";");
        }

    </script>
</head>

<div id="vmi_global_content">
    <div id="vmi_top_header">

        <div class="vmi_top_header_a">
            <div class="vmi_top_header_aa"></div>
            <div class="vmi_top_header_ab">


                <div class="vmi_lang_w">
                    欢迎叶 新武&nbsp;|&nbsp;383926登录
                    |&nbsp;<a href="/wms_web/views/maindata/ToDoList.jsp">待办事务</a>

                    |&nbsp;<a href="/wms_web/views/home/changePW.jsp">修改密码</a>
                    |&nbsp;<a href="https://ssobpstest.byd.com.cn/ssobpstest/logout">注销</a>
                    |&nbsp;<a href="/wms_web/views/home/downloadAction.jsp">帮助文档</a>
                    |&nbsp;<a onclick="openWin('/wms_web/views/release/newsReleaseEdit.jsp', '500px', '400px', 'no')" style="CURSOR: pointer" >功能发布</a>

                </div>
            </div>
        </div>

        <div class="vmi_top_header_b">
            <ul>
                <li id="two1" onclick="setTab('two',1,11)"><a href="#;">收货管理</a></li>
                <li id="two8" onclick="setTab('two',8,11)"><a href="#;">质检管理</a></li>

                <li id="two2" onclick="setTab('two',2,11)"><a href="#;">出仓管理</a></li>
                <li id="two3" onclick="setTab('two',3,11)"><a href="#;">库存管理</a></li>
                <li id="two4" onclick="setTab('two',4,11)" ><a href="#;">盘点管理</a></li>
                <li id="two5" onclick="setTab('two',5,11)" ><a href="http://bpstest.byd.com.cn:7888/BPSReport" >报表管理</a></li>
                <li id="two6" onclick="setTab('two',6,11)" ><a href="#;">主数据管理</a></li>
                <li id="two9" onclick="setTab('two',9,11)" ><a href="#;">半成品</a></li>

                <li id="two10" onclick="setTab('two',10,11)" ><a href="/wms_web/views/wms/semiFinished/OtherDataPage.jsp">基础数据维护</a></li>
                <li id="two11" onclick="setTab('two',11,11)" ><a href="/wms_web/views/wms/receive/merger/BatchMerger.jsp">批次合并</a></li>
                <li id="two7" onclick="setTab('two',7,11)" ><a href="#;">其他</a></li>
            </ul>

        </div>

        <div class="vmi_top_header_c">

            <div id="con_two_1" style="display:none">
                <li><a href="${pageContext.servletContext.contextPath}/views/receipt/asns/Asn.jsp">送货单查询</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/views/receipt/GoodsMove.jsp">收货管理</a></li>
                <li><a href="/wms_web/views/wms/receive/warehouseEntry/WarehouseEntrySearch.jsp">进仓单进仓</a></li>
                <li><a href="/wms_web/views/wms/receive/warehouseEntry/WarehouseEntrySearchForBy.jsp">副产品进仓</a></li>
                <li><a href="/wms_web/views/wms/receive/warehouseEntry/WarhouseMaterialTransferBackEntrySearch.jsp">调拨退料进仓</a></li>

                <li><a href="/wms_web/views/wms/receive/V124Menu.jsp">退货管理</a></li>
                <li><a href="/wms_web/views/wms/receive/OffsetManager.jsp">冲销管理</a></li>
                <li><a href="/wms_web/views/documents/ReturnBillsQuery.jsp">退货单查询</a></li>
                <li><a href="/wms_web/views/wms/receive/WarehousingPlan.jsp">计划进仓</a></li>
                <li><a href="/wms_web/views/documents/WarehouseEntryBillsQuery.jsp">进仓单查询</a></li>

                <li><a href="/wms_web/views/wms/barcode/BarcodeMenu.jsp">条码管理</a></li>

            </div>
            <div id="con_two_8" style="display:none">
                <li><a href="${pageContext.servletContext.contextPath}/views/inspect/Inspect.jsp">质检查询</a></li>
                <li><a href="/wms_web/views/wms/receive/InspectionSheetsQuery.jsp">检验单查询</a></li>
                <li><a href="/wms_web/views/wms/receive/IqcTranManagement.jsp">质检管理</a></li>
                <li><a href="/wms_web/views/wms/receive/iqc/InspectionSheetCreate.jsp">创建复检单</a></li>
                <li><a href="/wms_web/views/wms/receive/iqc/TranIqcManagement.jsp">批量质检</a></li>

            </div>
            <div id="con_two_2" style="display:none">
                <li><a href="/wms_web/views/wms/deliveryplan/DeliveryPlan_1.jsp">计划出仓</a></li>
                <li><a href="/wms_web/views/wms/outbound/Wout.jsp?urlvalue=A">出仓确认</a></li>
                <li><a href="/wms_web/views/wms/outbound/Wpick.jsp">生成拣配单</a></li>
                <li><a href="/wms_web/views/wms/outbound/ObligateStockQuery.jsp?flag=2">拣配单查询</a></li>
                <li><a href="/wms_web/views/wms/outbound/ObligateStockQuery.jsp?flag=1">页面拣配</a></li>

                <li><a href="/wms_web/views/documents/RequisitionBillsQuery.jsp">出仓单查询</a></li>
                <li><a href="/wms_web/views/documents/RequisitionBillsQueryForReserve.jsp">开单预留查询</a></li>
                <li><a href="/wms_web/views/wms/outbound/F020101.jsp">SAP过帐任务</a></li>
                <li><a href="/wms_web/views/wms/outbound/Woffset.jsp">出仓冲销</a></li>
                <li><a href="/wms_web/views/wms/outbound/Pro262.jsp">生产订单退料</a></li>
                <li><a href="/wms_web/views/wms/outbound/ReturnManage.jsp">退料管理</a></li>

                <li><a href="/wms_web/views/wms/outbound/ScrapManage.jsp">报废管理</a></li>
            </div>

            <div id="con_two_3" style="display:none">
                <li><a href="/wms_web/views/stock/InTransitStockQuery.jsp">在途库存查询</a></li>
                <li><a href="/wms_web/views/stock/ReceiveroomStockQuery.jsp">收料房库存查询</a></li>
                <li><a href="/wms_web/views/stock/StockLiveQuery.jsp">实时库存查询</a></li>

                <li><a href="/wms_web/views/stock/QCRecordsQuery.jsp">质检信息查询</a></li>
                <li><a href="/wms_web/views/stock/MoveTrackQuery.jsp">储位移动查询</a></li>
                <li><a href="/wms_web/views/stock/BatchesQuery.jsp">批次信息查询</a></li>
                <li><a href="/wms_web/views/stock/BatchesTrackQuery.jsp">批次跟踪</a></li>
                <li><a href="/wms_web/views/stock/TransactionsQuery.jsp">事务查询</a></li>
                <li><a href="/wms_web/views/stock/VouchersQuery.jsp">凭证查询</a></li>

                <li><a href="/wms_web/views/stock/StockAging.jsp">库存账龄</a></li>
                <li><a href="/wms_web/views/wms/stock/MaterielRealStockSearch.jsp">有效库存查询</a></li>


                <!--
                    <li><a href="#">库存冻结</a></li>
                    <li><a href="#">库存解冻</a></li>
                    <li><a href="/wms_web/views/wms/stock/BinSkuStock.jsp">储位-物料库存</a></li>
                    <li><a href="#">工厂间调拨</a></li>
                    <li><a href="#">库位间调拨</a></li>
                -->
            </div>

            <div id="con_two_4" style="display:none">

                <li><a href="/wms_web/views/wms/stock/StockFreezeThawSearch.jsp">库存冻结解冻</a></li>

                <li><a href="/wms_web/views/wms/stock/FreezeThawRecordSearch.jsp">库存冻结解冻记录查询</a></li>
                <li><a href="/wms_web/views/stock/InventoryStockQuery.jsp">盘点库存清单</a></li>
                <li><a href="/wms_web/views/documents/ProduceStockQuery.jsp">生产订单领料单</a></li>
                <li><a href="/wms_web/views/stock/InventoryReportsAtAnyTimeQuery.jsp">任意时间库存报表</a></li>
                <li><a href="/wms_web/views/stock/InventoryDailyQuery.jsp">库存日报表</a></li>
                <li><a href="/wms_web/views/stock/InventoryMonthlyQuery.jsp">库存月报表</a></li>

            </div>
            <div id="con_two_5" style="display:none">

                <li><a href="http://bpstest.byd.com.cn:7888/BPSReport/views/report/InTransitStockDailyQuery.jsp">在途库存日报表</a></li>
                <li><a href="http://bpstest.byd.com.cn:7888/BPSReport/views/report/ReceiveroomStockDailyQuery.jsp">收料房库存日报表</a></li>
                <li><a href="http://bpstest.byd.com.cn:7888/BPSReport/views/report/StockDailyQuery.jsp">库存日报表</a></li>
                <li><a href="http://bpstest.byd.com.cn:7888/BPSReport/views/report/StockMonthlyQuery.jsp">库存月报表</a></li>
                <li><a href="http://bpstest.byd.com.cn:7888/BPSReport/views/report/StockPeriodQuery.jsp">任意时间库存报表</a></li>

                <li><a href="http://bpstest.byd.com.cn:7888/BPSReport/views/report/TransactionDailyQuery.jsp">物料进出库日报表</a></li>
                <li><a href="http://bpstest.byd.com.cn:7888/BPSReport/views/report/WaitingQCDailyQuery.jsp">待检物料日报表</a></li>
                <li><a href="http://bpstest.byd.com.cn:7888/BPSReport/views/report/StockAging.jsp">物料库存账龄月报表</a></li>
                <li><a href="/wms_web/views/report/DuiZhangHuiZongQuery.jsp">对账明细表</a></li>
            </div>
            <div id="con_two_6" style="display:none">
                <!--
                    <li><a href="#">物料信息表</a></li>
                    <li><a href="#">工厂信息表</a></li>
                    <li><a href="#">库位信息表</a></li>
                -->

                <li><a href="/wms_web/views/maindata/BinQuery.jsp">储位信息表</a></li>
                <li><a href="/wms_web/views/maindata/BatchBinRecordInfo.jsp">寄售信息记录</a></li>
                <li><a href="/wms_web/views/maindata/RecordInfoQuery.jsp">信息记录查询</a></li>
                <li><a href="/wms_web/views/maindata/LocationList.jsp">库位信息管理</a></li>
                <li><a href="/wms_web/views/maindata/VendorPriorityList.jsp">供应商优先级信息管理</a></li>
                <!-- 增加配额管理url -->

                <li><a href="/wms_web/views/maindata/QuotaManage.jsp">配额管理</a></li>
                <li><a href="/wms_web/views/maindata/StockMoveType.jsp">库存类型转移</a></li>
                <li><a href="/wms_web/views/maindata/FreezePick.jsp">冻结库存拣配</a></li>
                <li><a href="/wms_web/views/stock/BincodeReverseQuery.jsp">出仓区库存返回</a></li>
                <li><a href="/wms_web/views/maindata/RealTimeGetProductOrder.jsp">订单实时同步</a></li>
            </div>

            <div id="con_two_7" style="display:none">

                <li><a href="/wms_web/views/wms/outbound/ScanGuoZhangItem.jsp">扫描过账</a></li>
                <li><a href="/wms_web/views/misc/BinsQuery.jsp">储位标识单打印</a></li>
                <li><a href="/wms_web/views/stock/UnitConvert.jsp">库存单位转换</a></li>
                <li><a href="/wms_web/views/maindata/UserMaterialManager.jsp">用户关联物料管理</a></li>
                <li><a href="/wms_web/views/wms/outbound/CancelWoffset.jsp">取消物料凭证</a></li>

                <li><a href="/wms_web/views/maindata/MaterialBindingBinManagement.jsp">物储关联管理</a></li>
                <li><a href="/wms_web/views/wms/stock/DisplaceStockSearch.jsp">转移库存查询</a></li>
                <li><a href="/wms_web/views/stock/UploadStock.jsp">[上传库存]</a></li>
                <li><a href="/wms_web/views/stock/UploadSingleCarUsAge.jsp">上传单车用量</a></li>
                <!--                <li><a href="<#c:url value="/views/wms/receive/merger/BatchMerger.jsp"/>"><#fmt:message key="BatchMerger" bundle="" /></a></li>-->
                <li><a href="/wms_web/views/wms/receive/deliverymodule/DeliveryManager.jsp">交货管理</a></li>
            </div>

            <div id="con_two_9" style="display:none">
                <li><a href="/wms_web/views/wms/semiFinished/InWarehousePage.jsp">半成品进仓</a></li>
                <li><a href="/wms_web/views/wms/semiFinished/OutWarehousePage.jsp">半成品出仓</a></li>
                <!--                <li><a href="<#c:url value="/views/wms/semiFinished/OtherDataPage.jsp"/>">其他数据维护</a></li>
                                <li><a href="<#c:url value="/views/wms/semiFinished/WarehouseManagerPage.jsp"/>">库存管理</a></li>
                                <li><a href="<#c:url value="/views/wms/semiFinished/MainDataPage.jsp"/>">主数据</a></li>-->

            </div>

        </div>

    </div>

</div>



<%--当没有部署新功能时，通告消息不显示 add by djh1221639 2011-02-17 start--%>
<!--<byd:Notice>-->
<style type="text/css">
    .vmi_global_content_new{
        width: 950px;
        color: #454545;
        padding: 0px;
        height: auto;
        margin-top: 0px;
        margin-right: auto;
        margin-bottom: 0px;
        margin-left: auto;
    }
</style>
<div class="vmi_global_content_new">
    <!--        <a onclick="openWin('/wms_web/views/release/newsRelease.jsp?__METHOD=onLoad', '500px', '400px', 'no')" style="CURSOR: pointer" >-->
    <marquee width="700"  direction="right" behavior="slide" scrollamount="50" scrolldelay="500" onmouseover="this.stop()" onmouseout="this.start()" ><font color="red"><b>${__noteInformation}</b></font></marquee>
    <!--        </a>-->
</div>
<!--</byd:Notice>-->
<%--当没有部署新功能时，通告消息不显示 add by djh1221639 2011-02-17 end--%>