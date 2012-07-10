/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.form.component;

import cn.com.annotations.Element;
import cn.com.annotations.Grid;
import cn.com.annotations.Link;
import cn.com.annotations.enums.ElementTagType;
import cn.com.tags.form.ContainHTMLElementTag;
import cn.com.tags.form.ElementComponent;
import cn.com.tags.form.containstag.*;
import cn.com.tags.form.singletag.InputElementTag;
import cn.com.tags.form.singletag.LinkElementTag;
import cn.com.tags.form.singletag.SelectOptionElementTag;
import cn.com.utils.StringUtil;
import java.util.List;

/**
 *
 * @author xy
 */
public class TableComponent extends ElementComponent {

    @Override
    public void parser(ContainHTMLElementTag parentElement) {
        Grid[] grids = group.grids();
        TableElementTag tableElement = null;
        TableTrElementTag tableTrElement = null;
        TableTdElementTag tableTdElement = null;
        DivElementTag tableDiv = null;
        for (Grid g : grids) {
            tableDiv = new DivElementTag(g.id() + "HeaderTableDiv", "headerTableDiv");
            // 添加对表格中行进行增加及删除操作的DIV
            // TODO 需要追加如果配置的删除追加就必须为可选择
            if (g.addabled() || g.deletabled()) {
                DivElementTag buttonDiv = new DivElementTag(g.id() + "ButtonDiv", "buttonDiv");
                if (g.addabled()) {
                    InputElementTag buttonElement = new InputElementTag();
                    buttonElement.setId("addItem");
                    buttonElement.setCssClass("tableButton");
                    buttonElement.setType(ElementTagType.BUTTON.name());
                    buttonElement.setValue("ADD.BUTTON");
                    buttonElement.setOnclick("addedTableTr('" + g.id() + "ContentTable',false);");
                    buttonDiv.addChildrenTag(buttonElement);
                }
                if (g.deletabled()) {
                    InputElementTag buttonElement = new InputElementTag();
                    buttonElement.setId("deleteItem");
                    buttonElement.setCssClass("tableButton");
                    buttonElement.setType(ElementTagType.BUTTON.name());
                    buttonElement.setOnclick("deleteTableTr('" + g.id() + "ContentTable');");
                    buttonElement.setValue("DELETE.BUTTON");
                    buttonDiv.addChildrenTag(buttonElement);
                }
                if (g.copyabled()) {
                    InputElementTag buttonElement = new InputElementTag();
                    buttonElement.setId("addItem");
                    buttonElement.setCssClass("tableButton");
                    buttonElement.setType(ElementTagType.BUTTON.name());
                    buttonElement.setValue("COPY.BUTTON");
                    buttonElement.setOnclick("addedTableTr('" + g.id() + "ContentTable',true);");
                    buttonDiv.addChildrenTag(buttonElement);
                }
                tableDiv.addChildrenTag(buttonDiv);
            }


            // 表格元素
            tableElement = new TableElementTag(g.id() + "HeaderTable", "inputTableHeader");
            // 表格头元素
            tableTrElement = new TableTrElementTag(g.id() + "TableTh", "tableTh");
            if (g.selectabled()) {
                tableTdElement = new TableTdElementTag("checkTd");
                if (g.isMultiple()) {
                    InputElementTag checkboxElement = new InputElementTag();

                    checkboxElement.setId("selectAll");
                    checkboxElement.setType(ElementTagType.CHECKBOX.name());
                    checkboxElement.setOnclick("__selectAll('selectInput', this.checked);");
                    tableTdElement.addChildrenTag(checkboxElement);
                }
                tableTrElement.addChildrenTag(tableTdElement);
            }
            for (Element column : g.columns()) {
                tableTdElement = new TableTdElementTag(column.id() + "HeaderTd");
                tableTdElement.setValue(column.label());
                if (column.required()) {
                    SpanElementTag requiredElement = new SpanElementTag(column.id() + "RequiredDiv", "requriedDiv");
                    requiredElement.setValue("*");
                    tableTdElement.addChildrenTag(requiredElement);
                }
                tableTrElement.addChildrenTag(tableTdElement);
            }
            tableElement.addChildrenTag(tableTrElement);

            // 追加表头
            tableDiv.addChildrenTag(tableElement);
            parentElement.addChildrenTag(tableDiv);

            tableDiv = new DivElementTag(g.id() + "contentTableDiv", "contentTableDiv");
            // 填充实体bean中的数据
            // 表格元素
            tableElement = new TableElementTag(g.id() + "ContentTable", "inputTableContent");
            List tableData = parser.getGridValue(g.id());
            if (tableData != null) {
                int idx = 0;
                String cssClass = null;
                for (Object obj : tableData) {
                    if (idx++ % 2 == 0) {
                        cssClass = "oldTr";
                    } else {
                        cssClass = "newTr";
                    }
                    tableTrElement = new TableTrElementTag(g.id() + "TableTr" + idx, cssClass);
                    // check选项
                    if (g.selectabled()) {
                        tableTdElement = new TableTdElementTag("checkTd");

                        InputElementTag checkboxElement = new InputElementTag();

                        checkboxElement.setId("selectInput");
                        if (g.isMultiple()) {
                            checkboxElement.setType(ElementTagType.CHECKBOX.name());
                        } else {
                            checkboxElement.setType(ElementTagType.RADIO.name());
                        }
                        checkboxElement.setValue(String.valueOf(idx - 1));

                        tableTdElement.addChildrenTag(checkboxElement);
                        tableTrElement.addChildrenTag(tableTdElement);
                    }
                    for (Element e : g.columns()) {
                        tableTdElement = new TableTdElementTag(e.id() + "HeaderTd");

                        switch (e.type()) {
                            case LINK:
                                LinkElementTag linkElement = new LinkElementTag();
                                // 设定动态参数参数
                                String value = parser.invokeValue(obj, e.id());
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

                                        href += (parameter + "=" + parser.invokeValue(obj, parameter));
                                    }
                                }

                                linkElement.setHref(href);

                                linkElement.setValue(value);

                                tableTdElement.addChildrenTag(linkElement);
                                break;
                            case SELECT:
                                SelectElementTag selectElement = new SelectElementTag();
                                String selectedValue = parser.invokeValue(obj, e.id());
//                                        selectElement.setValue(selectedValue);
                                selectElement.setId(g.id() + _SPLIT_DO + e.id());

                                String[] values = e.source().value();
                                if (values != null && values.length > 0) {
                                    int idx1 = 0;
                                    for (String d : values) {
                                        SelectOptionElementTag optionElement = new SelectOptionElementTag();
                                        optionElement.setDisplay(e.source().display()[idx1++]);
                                        optionElement.setValue(d);
                                        // 设定option被选择
                                        if (d.equals(selectedValue)) {
                                            optionElement.setSelected(true);
                                        }

                                        selectElement.addChildrenTag(optionElement);
                                    }
                                }
                                selectElement.setTitle(e.label());
                                selectElement.setRequired(e.required());
                                selectElement.setTabindex(String.valueOf(tabIndex.next()));
                                tableTdElement.addChildrenTag(selectElement);
                                break;
                            case DATE:
                                InputElementTag inputElement = new InputElementTag();
                                inputElement.setId(g.id() + _SPLIT_DO + e.id());
                                inputElement.setMaxLength(e.maxLength());
                                inputElement.setType(ElementTagType.TEXT.name());
                                inputElement.setValue(parser.getValue(e.id(), e.formate()));
                                inputElement.setOnblur(e.onblur());
                                inputElement.setReadonly(e.readonly());
                                inputElement.setOnfocus("__showCalendar(this);");
                                tableTdElement.addChildrenTag(inputElement);
                                inputElement.setTitle(e.label());
                                inputElement.setRequired(e.required());
                                
                                inputElement.setTabindex(String.valueOf(tabIndex.next()));
                                break;
                            case DATE_TIME:
                                inputElement = new InputElementTag();
                                inputElement.setId(g.id() + _SPLIT_DO + e.id());
                                inputElement.setMaxLength(e.maxLength());
                                inputElement.setType(ElementTagType.TEXT.name());
                                inputElement.setValue(parser.getValue(e.id(), e.formate()));
                                inputElement.setOnblur(e.onblur());
                                inputElement.setReadonly(e.readonly());
                                inputElement.setTabindex(String.valueOf(tabIndex.next()));
                                inputElement.setOnfocus("__showTime(this);");

                                inputElement.setTitle(e.label());
                                inputElement.setRequired(e.required());
                                tableTdElement.addChildrenTag(inputElement);
                                break;
                            default:
                                inputElement = new InputElementTag();
                                inputElement.setId(g.id() + _SPLIT_DO + e.id());
                                inputElement.setMaxLength(e.maxLength());
                                inputElement.setType(e.type().name());
                                inputElement.setOnblur(e.onblur());
                                inputElement.setReadonly(e.readonly());
                                inputElement.setValue(parser.invokeValue(obj, e.id()));
                                inputElement.setTabindex(String.valueOf(tabIndex.next()));

                                inputElement.setTitle(e.label());
                                inputElement.setRequired(e.required());

                                tableTdElement.addChildrenTag(inputElement);
                                break;
                        }
//                        if (e.required()) {
//                            SpanElementTag requiredElement = new SpanElementTag(e.id() + "RequiredDiv", "requriedDiv");
//                            requiredElement.setValue("*");
//                            tableTdElement.addChildrenTag(requiredElement);
//                        }
                        tableTrElement.addChildrenTag(tableTdElement);
                    }
                    tableElement.addChildrenTag(tableTrElement);
                }

            }

            tableDiv.addChildrenTag(tableElement);

        }
        if (tableDiv != null) {
            // 保存表格元素
            parentElement.addChildrenTag(tableDiv);
        }
    }
}
