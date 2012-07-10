/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.form.component;

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
 *
 * @author xy
 */
public class ConditionComponent extends ElementComponent {

    private Permission permission = null;

    public ConditionComponent(Permission permission) {
        this.permission = permission;
    }
    @Override
    public void parser(ContainHTMLElementTag parentElement) {

        // 生成单元素标签
        DivElementTag conditionsDiv = new DivElementTag(group.name() + "ConditionsDiv", "conditionsDiv");
        // 元素
        Element[] elements = group.elements();
        List<Element> fileElements = new ArrayList<Element>(3);
        for (Element e : elements) {
            // 将文件元素设定到最后
            if (e.type() == ElementTagType.FILE) {
                fileElements.add(e);
                continue;
            }

            DivElementTag elementDiv = new DivElementTag(e.id() + "Div", "conditionDiv");
            // 标签元素
            LabelElementTag labelElement = new LabelElementTag(e.id() + "Label", "displayLabel");
            labelElement.setValue(e.label());
            elementDiv.addChildrenTag(labelElement);


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
                    // 作为异常报错的提示标示
                    inputElement.setTitle(e.label());
                    inputElement.setRequired(e.required());
                    inputElement.setTabindex(String.valueOf(tabIndex.next()));
                    elementDiv.addChildrenTag(inputElement);
                    break;
            }
            // 是否必须元素处理
            if (e.required()) {
                SpanElementTag requiredElement = new SpanElementTag(e.id() + "RequiredDiv", "requriedDiv");
                requiredElement.setValue("*");
                elementDiv.addChildrenTag(requiredElement);
            }
            conditionsDiv.addChildrenTag(elementDiv);
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
            conditionsDiv.addChildrenTag(fileDiv);
        }

        // 保存条件输入元素
        parentElement.addChildrenTag(conditionsDiv);
    }
}
