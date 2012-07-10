/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.form.implement.wms;

import cn.com.annotations.Group;
import cn.com.annotations.parser.AnnotationParser;
import cn.com.tags.form.ContainHTMLElementTag;
import cn.com.tags.form.ContextComponent;
import cn.com.tags.form.component.TabIndex;
import cn.com.tags.form.component.wms.WmsActionComponent;
import cn.com.tags.form.component.wms.WmsConditionComponent;
import cn.com.tags.form.component.wms.WmsTableComponent;
import cn.com.tags.form.containstag.DivElementTag;
import cn.com.template.form.FormTemplate;
import cn.com.wapps.permission.Permission;

/**
 * <pre>
 *     <form >
                <div id="vmi_content">
                    <div class="vmi_planmanage">
                        <div class="vmiuf_box">
                            <div class="vmiuf_box_h2">生产订单领料</div>
                            <div class="vmiuf_box_sc">
                                <div class="vmiAddG_box_smcf">
                                    <label >工厂</label><div class="vmiAddG_cx_span">

<!--                                        <input name="FACTORYCODE" id="FACTORYCODE"  class="vmi_inp_c" type="text" value=""    />-->
                                        <input class=vmi_inp_c  id=FACTORYCODE  name=FACTORYCODE  type=text />
                                    </div>
                                    <div class="vmiuf_cx_smcf"><a href="#" onclick="openwin('../../query/SelectFactoryCode.jsp','FACTORYCODE')">选择</a></div>
                                </div>
                                <div class="vmiAddG_box_smcf">
                                    <label >仓库号</label><div class="vmiAddG_cx_span">
<!--                                        <input name="WHCODE" id="WHCODE"       class="vmi_inp_c" type="text" value="" />-->
                                        <input class=vmi_inp_c  id=WHCODE  name=WHCODE  type=text />

                                    </div>
                                </div>
                                <div class="vmiAddG_box_smcf">
                                    <label >车间</label><div class="vmiAddG_cx_span">
                                        <input name="CPLANT" id="CPLANT"       class="vmi_inp_c" type="text" value="" />

                                    </div>
                                </div>
                                <!--
                                <div class="vmiAddG_box_smcf">
                                    <label >总帐科目</label><div class="vmiAddG_cx_span">
                                        <input name="GLACCOUNT" id="GLACCOUNT"       class="vmi_inp_c" type="text" value="" />

                                    </div>
                                </div>
                                -->

                                <div class="vmiAddG_box_smcf">
                                    <label >领料用途</label><div class="vmiAddG_cx_span">
                                        <input name="PICKINGLISTTXT" id="PICKINGLISTTXT"       class="vmi_inp_c" type="text" value="" />

                                    </div>
                                </div>

                                <div class="vmiAddG_box_smcf">
                                    <label >领料方式</label><div class="vmiAddG_cx_span">

                                        <SELECT NAME="CREATEMODE">
                                            <OPTION VALUE="1" >指定供应商</OPTION>
                                            <OPTION VALUE="2" >供应商优先级</OPTION>
                                            <OPTION VALUE="3" >配额</OPTION>
                                            <OPTION VALUE="4" >先进先出</OPTION>
                                            <OPTION VALUE="5" >自有优先</OPTION>

                                        </SELECT>
                                    </div>
                                    <div class="vmiuf_cx_smcf">*</div>
                                </div>
                                <div class="vmiAddG_box_smcf">
                                    <label >库存状态</label><div class="vmiAddG_cx_span">
                                        <SELECT NAME="STSTCD" id="STSTCD">
                                            <OPTION VALUE="01">非限制</OPTION>

                                            <OPTION VALUE="02">冻结库存</OPTION>
                                        </SELECT>
                                    </div>
                                    <div class="vmiuf_cx_smcf">*</div>
                                </div>




                            </div>
                        </div>
                    </div>

                    <div class="vmi_planmanage_title">
                        <div class="vmi_planmanage_qqq">
                            <div class="vmi_planmanage_sc_b">
                                <div class="vmi_btnmai_o">
                                    <div class="vmi_btnmai_a"></div>
                                    <div class="vmi_btnmai_b"><input name="input" id="addRT"  class="vmi_btnmain_b"  name="addrow"   type="button" value='添加新项' /></div>
                                    <div class="vmi_btnmai_c"></div>
                                </div>

                            </div>
                            <div class="vmi_planmanage_sc_b">
                                <div class="vmi_btnmai_o">
                                    <div class="vmi_btnmai_a"></div>
                                    <div class="vmi_btnmai_b"><input type="button" name="deleteselectedrows"  id="deleteRT" class="vmi_btnmain_b" type="button" value='删除'/></div>
                                    <div class="vmi_btnmai_c"></div>
                                </div>
                            </div>

                            <div class="vmi_planmanage_sc_a_xx">
                                <div class="vmi_btnmai_o">
                                    <div class="vmi_btnmai_a"></div>
                                    <div class="vmi_btnmai_b"><input name="input"  class="vmi_btnmain_b" type="button" value='生产订单批量上传' onclick="location.href='UploadPickingByProductionOrder.jsp?type=A'"/></div>
                                    <div class="vmi_btnmai_c"></div>
                                </div>
                            </div>

                            <div class="vmi_planmanage_sc_a_xx">
                                <div class="vmi_btnmai_o">

                                    <div class="vmi_btnmai_a"></div>
                                    <div class="vmi_btnmai_b"><input name="input"  class="vmi_btnmain_b" type="button" value='批量添加' onclick="showDialog('OrderQueryIFrame.jsp?type=proid');" /></div>
                                    <div class="vmi_btnmai_c"></div>
                                </div>
                           </div>
                        </div>
                    </div>
                    <div class="vmi_teble">
                        <div class="vmi_table_gdt">

                            <div id="cellediting" ></div>
                        </div>
                        <div class="vmiAddG_next_button">
                            <div class="vmiAddG_b_button">
                                <div class="vmiAddG_b_but"><input type="submit" name="preview" id="preview" class="vmi_btnmain_c" onclick="return checkinput();" value='提交' /></div>
                                <div class="vmiAddG_b_but">&nbsp;</div>
                                <div class="vmiAddG_b_but"> <input name="input"  class="vmi_btnmain_c"  type="button" value='取消'  onclick="location.href='PicklistByProductionOrder.jsp'"/></div>
                            </div>

                        </div>
                         <div style="display: none">
                                <input type="button" id="keydownClickID" name="keydownClickID">
                        </div>
                        <input type="hidden" id="inputdata" name="inputdata" value=""/>
                        <input type="hidden" id="flag" name="flag" value="828941395"/>
                        <input id="proidNew" name="proidNew" type="hidden">
                        <input id="proid" name="proid" type="hidden">
                        <div class="vmi_next">

                        </div>
                    </div>
                </div>
            </form>
 * </pre>
 *
 * @author kete
 */
public class WmsFormTemplate extends FormTemplate {

    @Override
    protected void parserComponents(AnnotationParser parser, ContainHTMLElementTag parentElement, Group group, Permission permission) {

        if (group != null) {
            DivElementTag contentDiv = new DivElementTag(group.name(), "vmi_content");

            ContextComponent context = new ContextComponent(contentDiv, parser, new TabIndex());
            //条件标签
            context.addComponent(new WmsConditionComponent(permission));
            // 表格标签
            context.addComponent(new WmsTableComponent());
            // 事件标注
            context.addComponent(new WmsActionComponent(permission));

            context.processComponents();
            parentElement.addChildrenTag(contentDiv);
        }
    }
}
