/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements.component.style.wms;

import cn.com.annotations.Element;
import cn.com.annotations.enums.ElementEnum;
import cn.com.elements.component.conditions.ConditionElementComponent;
import cn.com.exceptions.AppException;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.elements.containstag.div.DivElementTag;
import cn.com.elements.containstag.label.LabelElementTag;
import cn.com.elements.containstag.div.SpanElementTag;
import cn.com.utils.StringUtil;
import java.util.ArrayList;
import java.util.List;

/**
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
<div class="vmiuf_box_ufsc">
<label>请选择文件</label>
<input id="proOrderFile" class="input_sc" type="file" size="50" name="proOrderFile"/>
<span class="mvcRequire">*</span>
</div>
<div class="vmiuf_box_text">
<b>
<a href="DownloadTemplate.jsp?d=52">下载生产订单模板</a>
</b>
</div>
 * @author xy
 */
public class WmsConditionComponent extends ConditionElementComponent {

    private static final String _BOXS_DIV_ID = "boxsDiv";
    private static final String _BOXS_DIV_CSS = "vmiuf_box";
    private static final String _BOX_DIV_CSS = "vmiuf_box_sc";
    private static final String _BOX_DIV_ID = "boxDiv";
    private static final String _CONDITIONS_DIV_CSS = "vmi_planmanage";
    private static final String _CONDITIONS_DIV_ID = "ConditionsDiv";
    private static final String _CONDITION_FILE_DIV = "conditionFileDiv";
    private static final String _DIV_CSS = "vmiAddG_box_smcf";
    private static final String _DIV_ID = "Div";
    private static final String _ELEMENT_CSS = "vmi_inp_c";
    private static final String _INPUT_DIV_ID = "InputDiv";
    private static final String _INPUT_DIV_CSS = "vmiAddG_cx_spanf";
    private static final String _REQUIRED_CSS = "vmiuf_cx_smcf";
    private static final String _TITLE_CSS = "vmiuf_box_h2";
    private static final String _TITLE_DIV_ID = "titleDiv";

    @Override
    public void parser(ContainHTMLElementTag parentElement) throws AppException {

        // 生成单元素标签
        DivElementTag conditionsDiv = new DivElementTag(super.getParser().getGroup().name() + _CONDITIONS_DIV_ID, _CONDITIONS_DIV_CSS);
        // 生成单元素标签
        DivElementTag boxsDiv = new DivElementTag(super.getParser().getGroup().name() + _BOXS_DIV_ID, _BOXS_DIV_CSS);
        conditionsDiv.addChildrenTag(boxsDiv);

        // 设定页面标题信息
        DivElementTag titleDiv = new DivElementTag(super.getParser().getGroup().name() + _TITLE_DIV_ID, _TITLE_CSS);
        SpanElementTag titleContent = new SpanElementTag();
        titleContent.setValue(Element.LabelContent.getLabelContent(super.getParser().getForm().label()));
        titleDiv.addChildrenTag(titleContent);
        boxsDiv.addChildrenTag(titleDiv);

        // 生成条件内容
        DivElementTag boxDiv = new DivElementTag(super.getParser().getGroup().name() + _BOX_DIV_ID, _BOX_DIV_CSS);
        boxsDiv.addChildrenTag(boxDiv);
        // 元素
        Element[] elements = super.getParser().getGroup().elements();
        List<Element> fileElements = new ArrayList<Element>(3);
        for (Element e : elements) {
            // 将文件元素设定到最后
            if (e.type() == ElementEnum.FILE) {
                fileElements.add(e);
                continue;
            }

            DivElementTag contentDiv = new DivElementTag(e.id() + _DIV_ID, _DIV_CSS);
            // 标签元素
            LabelElementTag labelElement = new LabelElementTag();
            labelElement.setValue(Element.LabelContent.getLabelContent(StringUtil.isEmpty(e.label()) ? e.id().toUpperCase() : e.label()));
            contentDiv.addChildrenTag(labelElement);


            // 元素内容
            DivElementTag elementDiv = new DivElementTag(e.id() + _INPUT_DIV_ID, _INPUT_DIV_CSS);
            super.parserElement(null, e, elementDiv, _ELEMENT_CSS, _REQUIRED_CSS, super.getParser().getValue(e));

            contentDiv.addChildrenTag(elementDiv);
            boxDiv.addChildrenTag(contentDiv);
        }

        super.parserFileElements(fileElements, boxDiv, _CONDITION_FILE_DIV);

        // 保存条件输入元素
        parentElement.addChildrenTag(conditionsDiv);
    }
}
