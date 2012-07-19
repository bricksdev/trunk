/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements.component.style;

import cn.com.annotations.Element;
import cn.com.annotations.enums.ElementEnum;
import cn.com.elements.component.conditions.ConditionElementComponent;
import cn.com.exceptions.AppException;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.elements.containstag.div.DivElementTag;
import cn.com.elements.containstag.label.LabelElementTag;
import cn.com.utils.StringUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xy
 */
public class DefaultConditionComponent extends ConditionElementComponent {

    private static final String _CONDITIONS_CSS = "conditionsDiv";
    private static final String _CONDITIONS_DIV = "ConditionsDiv";
    private static final String _CONDITION_CSS = "conditionDiv";
    private static final String _CONDITION_FILE_DIV = "conditionFileDiv";
    private static final String _DISPLAY_LABEL_CSS = "displayLabel";
    private static final String _DIV_ID = "Div";
    private static final String _LABEL_ID = "Label";
    private static final String _REQURIED_CSS = "requriedDiv";

    @Override
    public void parser(ContainHTMLElementTag parentElement) throws AppException {

        // 生成单元素标签
        DivElementTag conditionsDiv = new DivElementTag(super.getParser().getGroup().name() + _CONDITIONS_DIV, _CONDITIONS_CSS);
        // 元素
        Element[] elements = super.getParser().getGroup().elements();
        List<Element> fileElements = new ArrayList<Element>(3);
        for (Element e : elements) {
            // 将文件元素设定到最后
            if (e.type() == ElementEnum.FILE) {
                fileElements.add(e);
                continue;
            }

            DivElementTag elementDiv = new DivElementTag(e.id() + _DIV_ID, _CONDITION_CSS);
            // 标签元素
            LabelElementTag labelElement = new LabelElementTag(e.id() + _LABEL_ID, _DISPLAY_LABEL_CSS);
            labelElement.setValue(Element.LabelContent.getLabelContent(StringUtil.isEmpty(e.label()) ? e.id().toUpperCase() : e.label()));
            elementDiv.addChildrenTag(labelElement);

            super.parserElement(null, e, elementDiv, null, _REQURIED_CSS, super.getParser().getValue(e));

            conditionsDiv.addChildrenTag(elementDiv);
        }

        // 文件导入元素节点
        super.parserFileElements(fileElements, conditionsDiv, _CONDITION_FILE_DIV);

        // 保存条件输入元素
        parentElement.addChildrenTag(conditionsDiv);
    }
}
