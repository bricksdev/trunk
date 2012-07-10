/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.form.component.wms;

import cn.com.annotations.Element;
import cn.com.annotations.Link;
import cn.com.annotations.enums.ElementTagType;
import cn.com.tags.form.ContainHTMLElementTag;
import cn.com.tags.form.ElementComponent;
import cn.com.tags.form.containstag.DivElementTag;
import cn.com.tags.form.containstag.LabelElementTag;
import cn.com.tags.form.containstag.SelectElementTag;
import cn.com.tags.form.containstag.SpanElementTag;
import cn.com.tags.form.singletag.InputElementTag;
import cn.com.tags.form.singletag.LinkElementTag;
import cn.com.tags.form.singletag.SelectOptionElementTag;
import cn.com.utils.StringUtil;
import cn.com.wapps.permission.Permission;
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
public class WmsConditionComponent extends ElementComponent {

    private Permission permission = null;

    public WmsConditionComponent(Permission permission) {
        this.permission = permission;
    }

    @Override
    public void parser(ContainHTMLElementTag parentElement) {

        // 生成单元素标签
        DivElementTag conditionsDiv = new DivElementTag(group.name() + "ConditionsDiv", "vmi_planmanage");
        // 生成单元素标签
        DivElementTag boxsDiv = new DivElementTag(group.name() + "boxsDiv", "vmiuf_box");
        conditionsDiv.addChildrenTag(boxsDiv);
        // 设定页面标题信息
        DivElementTag titleDiv = new DivElementTag(group.name() + "titleDiv", "vmiuf_box_h2");
        SpanElementTag titleContent = new SpanElementTag();
        titleContent.setValue(parser.getForm().label());
        titleDiv.addChildrenTag(titleContent);
        boxsDiv.addChildrenTag(titleDiv);

        // 生成条件内容
        DivElementTag boxDiv = new DivElementTag(group.name() + "boxDiv", "vmiuf_box_sc");
        boxsDiv.addChildrenTag(boxDiv);
        // 元素
        Element[] elements = group.elements();
        List<Element> fileElements = new ArrayList<Element>(3);
        for (Element e : elements) {
            // 将文件元素设定到最后
            if (e.type() == ElementTagType.FILE) {
                fileElements.add(e);
                continue;
            }

            DivElementTag contentDiv = new DivElementTag(e.id() + "Div", "vmiAddG_box_smcf");
            // 标签元素
            LabelElementTag labelElement = new LabelElementTag();
            labelElement.setValue(e.label());
            contentDiv.addChildrenTag(labelElement);


            // 元素内容
            DivElementTag elementDiv = new DivElementTag(e.id() + "InputDiv", "vmiAddG_cx_spanf");

            switch (e.type()) {
                case LINK:
                    LinkElementTag linkElement = new LinkElementTag();
                    // 设定动态参数参数
                    String href = "#";
                    Link link = e.link();
                    String value = parser.getValue(e.id());

                    // 设定链接属性
                    if (!StringUtil.isEmpty(link.url())) {
                        href = link.url();
                    }
                    // 设定参数列表
                    if (link.parameters() != null && link.parameters().length > 0) {

                        if (!href.contains("?")) {
                            href += "?";
                        }
                        for (String parameter : link.parameters()) {

                            href += (parameter + "=" + parser.getValue(parameter));
                        }
                    }

                    linkElement.setValue(value);
                    linkElement.setOnclick(e.onclick());

                    elementDiv.addChildrenTag(linkElement);
                    break;
                case SELECT:
                    SelectElementTag selectElement = new SelectElementTag();
                    String selectedValue = parser.getValue(e.id());
//                            selectElement.setValue(selectedValue);
                    selectElement.setReadonly(e.readonly());
                    selectElement.setDisabled(e.disabled());
                    selectElement.setId(e.id());
                    String[] values = e.source().value();
                    if (values != null && values.length > 0) {
                        int idx = 0;
                        for (String d : values) {
                            SelectOptionElementTag optionElement = new SelectOptionElementTag();
                            optionElement.setDisplay(e.source().display()[idx++]);
                            optionElement.setValue(d);
                            // 设定option被选择
                            if (d.equals(selectedValue)) {
                                optionElement.setSelected(true);
                            }

                            selectElement.addChildrenTag(optionElement);
                        }
                    }
                    // 作为异常报错的提示标示
                    selectElement.setTitle(e.label());
                    selectElement.setCssClass("vmi_inp_c");
                    selectElement.setRequired(e.required());
                    selectElement.setTabindex(String.valueOf(tabIndex.next()));
                    elementDiv.addChildrenTag(selectElement);
                    break;
                case DATE:
                    InputElementTag inputElement = new InputElementTag();
                    inputElement.setId(e.id());
                    inputElement.setMaxLength(e.maxLength());
                    inputElement.setType(ElementTagType.TEXT.name());
                    inputElement.setValue(parser.getValue(e.id(), e.formate()));
                    inputElement.setOnblur(e.onblur());
                    inputElement.setReadonly(e.readonly());
                    inputElement.setOnfocus("__showCalendar(this);");
                    elementDiv.addChildrenTag(inputElement);
                    // 作为异常报错的提示标示
                    inputElement.setTitle(e.label());
                    inputElement.setCssClass("vmi_inp_c");
                    inputElement.setRequired(e.required());
                    inputElement.setTabindex(String.valueOf(tabIndex.next()));
                    break;
                case DATE_TIME:
                    inputElement = new InputElementTag();
                    inputElement.setId(e.id());
                    inputElement.setMaxLength(e.maxLength());
                    inputElement.setType(ElementTagType.TEXT.name());
                    inputElement.setValue(parser.getValue(e.id(), e.formate()));
                    inputElement.setOnblur(e.onblur());
                    inputElement.setReadonly(e.readonly());

                    inputElement.setOnfocus("__showTime(this);");
                    elementDiv.addChildrenTag(inputElement);
                    inputElement.setCssClass("vmi_inp_c");
                    inputElement.setTabindex(String.valueOf(tabIndex.next()));
                    // 作为异常报错的提示标示
                    inputElement.setTitle(e.label());
                    inputElement.setRequired(e.required());
                    break;
                default:
                    inputElement = new InputElementTag();
                    inputElement.setId(e.id());
                    inputElement.setMaxLength(e.maxLength());
                    inputElement.setType(e.type().name());
                    inputElement.setValue(permission.validateSecret(e.id()) ? parser.getValue(e.id()) : "***");
                    inputElement.setOnblur(e.onblur());
                    inputElement.setReadonly(e.readonly());
                    inputElement.setCssClass("vmi_inp_c");
                    // 作为异常报错的提示标示
                    inputElement.setTitle(e.label());
                    inputElement.setRequired(e.required());
                    inputElement.setTabindex(String.valueOf(tabIndex.next()));
                    elementDiv.addChildrenTag(inputElement);
                    break;
            }
            // 是否必须元素处理
            if (e.required()) {
                SpanElementTag requiredElement = new SpanElementTag(e.id() + "RequiredDiv", "vmiuf_cx_smcf");
                requiredElement.setValue("*");
                elementDiv.addChildrenTag(requiredElement);
            }
            contentDiv.addChildrenTag(elementDiv);
            boxDiv.addChildrenTag(contentDiv);
        }

        // 文件导入元素节点
        if (!fileElements.isEmpty()) {
            DivElementTag fileDiv = new DivElementTag(group.name() + "FileDiv", "conditionFileDiv");
            for (Element e : fileElements) {

                if (e.link() != null) {
                    LinkElementTag linkElement = new LinkElementTag();
                    // 设定动态参数参数
                    String href = "#";
                    Link link = e.link();

                    // 设定链接属性
                    if (!StringUtil.isEmpty(link.url())) {
                        href = link.url();
                    }
                    // 设定参数列表
                    if (link.parameters() != null && link.parameters().length > 0) {

                        if (!href.contains("?")) {
                            href += "?";
                        }
                        for (String parameter : link.parameters()) {

                            href += (parameter + "=" + parser.getValue(parameter));
                        }
                    }

                    linkElement.setValue(e.label());
                    fileDiv.addChildrenTag(linkElement);
                }

                InputElementTag inputElement = new InputElementTag();
                inputElement.setId(e.id());
                inputElement.setType(e.type().name());
                fileDiv.addChildrenTag(inputElement);

                inputElement = new InputElementTag();
                inputElement.setId("import" + e.id());
                inputElement.setType(ElementTagType.BUTTON.name());
                inputElement.setValue("IMPORT");
                fileDiv.addChildrenTag(inputElement);
            }
            boxDiv.addChildrenTag(fileDiv);
        }

        // 保存条件输入元素
        parentElement.addChildrenTag(conditionsDiv);
    }
}
