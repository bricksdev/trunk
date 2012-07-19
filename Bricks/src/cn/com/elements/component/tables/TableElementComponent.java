/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements.component.tables;

import cn.com.annotations.Element;
import cn.com.annotations.Grid;
import cn.com.annotations.enums.ElementEnum;
import cn.com.elements.component.ElementComponent;
import cn.com.elements.containstag.div.DivElementTag;
import cn.com.elements.containstag.label.LabelElementTag;
import cn.com.elements.containstag.table.TableElementTag;
import cn.com.elements.containstag.table.TableTdElementTag;
import cn.com.elements.containstag.table.TableThElementTag;
import cn.com.elements.containstag.table.TableTheadElementTag;
import cn.com.elements.containstag.table.TableTrElementTag;
import cn.com.elements.singletag.input.InputElementTag;
import cn.com.exceptions.AppException;
import cn.com.utils.StringUtil;
import java.util.List;

/**
 *
 * @author kete
 */
public abstract class TableElementComponent extends ElementComponent {

    private static final String _ADDED_TABLETR_FONT = "addedTableTr('";
    private static final String _ADDED_TABLETR_FOOT = "ContentTable',false);";
    private static final String _ADD_BUTTON_LABEL_ID = "ADD.BUTTON";
    private static final String _ADD_ITEM_ID = "addItem";
    private static final String _BODY_CONTAINER_CSS = "bodyContainer";
    private static final String _CHECK_TD_ID = "checkTd";
    private static final String _CHECK_THEAD_CSS = "checkThead";
    private static final String _CONTENT_DIV_CSS = "gridCell_standard";
    private static final String _CONTENT_DIV_ID = "_content_div";
    private static final String _CONTENT_TABLE_DIV_ID = "contentTableDiv";
    private static final String _CONTENT_TABLE_ELEMNTS_DIV_ID = "_ContentDiv";
    private static final String _CONTENT_TABLE_ID = "ContentTable";
    private static final String _CONTENT_TABLE_TD_ID = "HeaderTd";
    private static final String _CONTENT_TABLE_TR_ID = "TableTr";
    private static final String _COPY_BUTTON_LABEL = "COPY.BUTTON";
    private static final String _COPY_ITEM_ID = "addItem";
    private static final String _COPY_TABLE_TR_FOOT = "ContentTable',true);";
    private static final String _DELETE_BUTTON_LABEL = "DELETE.BUTTON";
    private static final String _DELETE_ITEM_ID = "deleteItem";
    private static final String _DELETE_TABLE_TR_FONT = "deleteTableTr('";
    private static final String _DELETE_TABLE_TR_FOOT = "ContentTable');";
    private static final String _DOUBLE_TR_CSS = "oldTr";
    private static final String _HEADER_TABLE_DIV_CSS = "headerTableDiv";
    private static final String _HEADER_TABLE_DIV_ID = "HeaderTableDiv";
    private static final String _HEADER_TABLE_ID = "HeaderTable";
    private static final String _INPUT_TABLE_CONTENT_CSS = "inputTableContent";
    private static final String _INPUT_TABLE_HEADER_CSS = "inputTableHeader";
    private static final String _PARAMETER_EQUAL_CHAR = "=";
    private static final String _SELECT_ALL_EVENT = "__selectAll('selectInput', this.checked);";
    private static final String _SELECT_ALL_ID = "selectAll";
    private static final String _SELECT_INPUT_ID = "selectInput";
    private static final String _SINGLE_TR_CSS = "newTr";
    private static final String _TABLE_BUTTON_CSS = "tableButton";
    private static final String _TABLE_BUTTON_DIV_CSS = "buttonDiv";
    private static final String _TABLE_BUTTON_DIV_ID = "ButtonDiv";
    private static final String _REQUIRED_CSS = "requriedDiv";

    /**
     * 设定Link元素的锚的内容
     * @param href
     * @param parameter
     * @param e
     * @throws AppException
     */
    @Override
    protected void settingLinkHref(StringBuilder href, String parameter, Object e) throws AppException {
        href.append(parameter).append(_PARAMETER_EQUAL_CHAR).append(e instanceof Element ? null : super.getParser().invokeValue(e, parameter));
    }

    /**
     * 解析表格选择框
     * @param g
     * @param idx
     * @param contentDiv
     * @param tableTdElement
     * @param tableTrElement
     */
    protected void parserCheckboxElement(Grid g, int idx, TableTrElementTag tableTrElement) {
        DivElementTag contentDiv = new DivElementTag(_CHECK_TD_ID, _CHECK_THEAD_CSS);
        TableTdElementTag tableTdElement = new TableTdElementTag(_CHECK_TD_ID);
        // check选项
        if (g.selectabled()) {
            InputElementTag checkboxElement = new InputElementTag();
            checkboxElement.setId(_SELECT_INPUT_ID);
            if (g.isMultiple()) {
                checkboxElement.setType(ElementEnum.CHECKBOX.name());
            } else {
                checkboxElement.setType(ElementEnum.RADIO.name());
            }
            checkboxElement.setValue(String.valueOf(idx - 1));
            contentDiv.addChildrenTag(checkboxElement);
            tableTdElement.addChildrenTag(contentDiv);
            tableTrElement.addChildrenTag(tableTdElement);
        }
    }

    protected int parserTableEvents(Grid g, TableElementTag tableContainer) {
        DivElementTag tableDiv = new DivElementTag(g.id() + _HEADER_TABLE_DIV_ID, _HEADER_TABLE_DIV_CSS);
        TableTdElementTag tdContainer = new TableTdElementTag();
        TableTrElementTag tableTrElement = new TableTrElementTag();
        // 添加对表格中行进行增加及删除操作的DIV
        // TODO 需要追加如果配置的删除追加就必须为可选择
        if (g.addabled() || g.deletabled()) {
            DivElementTag buttonDiv = new DivElementTag(g.id() + _TABLE_BUTTON_DIV_ID, _TABLE_BUTTON_DIV_CSS);
            if (g.addabled()) {
                InputElementTag buttonElement = new InputElementTag();
                buttonElement.setId(_ADD_ITEM_ID);
                buttonElement.setCssClass(_TABLE_BUTTON_CSS);
                buttonElement.setType(ElementEnum.BUTTON.name());
                buttonElement.setValue(Element.LabelContent.getLabelContent(_ADD_BUTTON_LABEL_ID));
                buttonElement.setOnclick(_ADDED_TABLETR_FONT + g.id() + _ADDED_TABLETR_FOOT);
                buttonDiv.addChildrenTag(buttonElement);
            }
            if (g.deletabled()) {
                InputElementTag buttonElement = new InputElementTag();
                buttonElement.setId(_DELETE_ITEM_ID);
                buttonElement.setCssClass(_TABLE_BUTTON_CSS);
                buttonElement.setType(ElementEnum.BUTTON.name());
                buttonElement.setOnclick(_DELETE_TABLE_TR_FONT + g.id() + _DELETE_TABLE_TR_FOOT);
                buttonElement.setValue(Element.LabelContent.getLabelContent(_DELETE_BUTTON_LABEL));
                buttonDiv.addChildrenTag(buttonElement);
            }
            if (g.copyabled()) {
                InputElementTag buttonElement = new InputElementTag();
                buttonElement.setId(_COPY_ITEM_ID);
                buttonElement.setCssClass(_TABLE_BUTTON_CSS);
                buttonElement.setType(ElementEnum.BUTTON.name());
                buttonElement.setValue(Element.LabelContent.getLabelContent(_COPY_BUTTON_LABEL));
                buttonElement.setOnclick(_ADDED_TABLETR_FONT + g.id() + _COPY_TABLE_TR_FOOT);
                buttonDiv.addChildrenTag(buttonElement);
            }
            tableDiv.addChildrenTag(buttonDiv);
        }
        int columnCount = parserTableThead(g, tableDiv);
        tdContainer.addChildrenTag(tableDiv);
        tableTrElement.addChildrenTag(tdContainer);
        tableContainer.addChildrenTag(tableTrElement);

        return columnCount;
    }

    /**
     * 解析表格内容
     * @param columnCount
     * @param tableContainer
     * @param g
     * @throws AppException
     */
    protected void parserTableTbody(int columnCount, TableElementTag tableContainer, Grid g) throws AppException {
        TableTrElementTag tableTrElement;
        DivElementTag contentDiv;
        TableTdElementTag tableTdElement;
        TableElementTag tableElement;
        DivElementTag tableDiv;
        // 表格内容
        tableTrElement = new TableTrElementTag();
        TableTdElementTag tdContainer = new TableTdElementTag();
        tdContainer.setColspan(String.valueOf(columnCount));
        tableTrElement.addChildrenTag(tdContainer);
        tableContainer.addChildrenTag(tableTrElement);
        tableDiv = new DivElementTag(g.id() + _CONTENT_TABLE_DIV_ID, _BODY_CONTAINER_CSS);
        // 填充实体bean中的数据
        // 表格元素
        tableElement = new TableElementTag(g.id() + _CONTENT_TABLE_ID, _INPUT_TABLE_CONTENT_CSS);
        List tableData = super.getParser().getGridValue(g.id());
        if (tableData != null) {
            int idx = 0;
            String cssClass = null;
            for (Object obj : tableData) {
                if (idx++ % 2 == 0) {
                    cssClass = _DOUBLE_TR_CSS;
                } else {
                    cssClass = _SINGLE_TR_CSS;
                }
                tableTrElement = new TableTrElementTag(g.id() + _CONTENT_TABLE_TR_ID + idx, cssClass);
                // 解析表格选择框
                parserCheckboxElement(g, idx, tableTrElement);
                for (Element e : g.columns()) {
                    tableTdElement = new TableTdElementTag(e.id() + _CONTENT_TABLE_TD_ID);
                    contentDiv = new DivElementTag(e.id() + _CONTENT_DIV_ID, _CONTENT_DIV_CSS);
                    // 解析单元素
                    parserElement(g.id(), e, contentDiv, null, null, super.getParser().invokeValue(obj, e));
                    tableTdElement.addChildrenTag(contentDiv);
                    tableTrElement.addChildrenTag(tableTdElement);
                }
                tableElement.addChildrenTag(tableTrElement);
            }
        }
        tableDiv.addChildrenTag(tableElement);
        tdContainer.addChildrenTag(tableDiv);
    }

    /**
     * 解析表格头
     * @param g
     * @param tableDiv
     * @return
     */
    protected int parserTableThead(Grid g, DivElementTag tableDiv) {
        int columnCount = 0;

        DivElementTag contentDiv;
        TableElementTag tableElement;
        // 表格元素
        tableElement = new TableElementTag(g.id() + _HEADER_TABLE_ID, _INPUT_TABLE_HEADER_CSS);
        // 表格头元素
        TableTheadElementTag theadContainer = new TableTheadElementTag();
        TableThElementTag thContainer = new TableThElementTag();
        if (g.selectabled()) {
            contentDiv = new DivElementTag(_CHECK_TD_ID, _CHECK_THEAD_CSS);
            if (g.isMultiple()) {
                InputElementTag checkboxElement = new InputElementTag();
                checkboxElement.setId(_SELECT_ALL_ID);
                checkboxElement.setType(ElementEnum.CHECKBOX.name());
                checkboxElement.setOnclick(_SELECT_ALL_EVENT);
                contentDiv.addChildrenTag(checkboxElement);
            }
            thContainer.addChildrenTag(contentDiv);
            theadContainer.addChildrenTag(thContainer);
        }
        LabelElementTag labelElement = null;
        for (Element column : g.columns()) {
            thContainer = new TableThElementTag();
            contentDiv = new DivElementTag(column.id() + _CONTENT_TABLE_ELEMNTS_DIV_ID, _CONTENT_DIV_CSS);
            labelElement = new LabelElementTag();
            labelElement.setValue(Element.LabelContent.getLabelContent(StringUtil.isEmpty(column.label()) ? column.id().toUpperCase() : column.label()));
            contentDiv.addChildrenTag(labelElement);
            if (column.required()) {
                parserRequiredElement(column, contentDiv, _REQUIRED_CSS);
            }
            theadContainer.addChildrenTag(thContainer);
            thContainer.addChildrenTag(contentDiv);
            columnCount++;
        }
        tableElement.addChildrenTag(theadContainer);
        // 追加表头
        tableDiv.addChildrenTag(tableElement);

        return columnCount;
    }
}
