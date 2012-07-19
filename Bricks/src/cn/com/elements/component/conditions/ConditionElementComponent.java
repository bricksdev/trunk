/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements.component.conditions;

import cn.com.annotations.Element;
import cn.com.annotations.enums.ElementEnum;
import cn.com.elements.component.ElementComponent;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.elements.containstag.div.DivElementTag;
import cn.com.elements.singletag.input.InputElementTag;
import cn.com.exceptions.AppException;
import java.util.List;

/**
 *
 * @author kete
 */
public abstract class ConditionElementComponent extends ElementComponent {

    private static final String _FILE_DIV_ID = "FileDiv";
    private static final String _FILE_IMPORT_PREFIX = "import";
    private static final String _IMPORT_BUTTOn_VALUE = "IMPORT";
    private static final String _PARAMETER_EQUAL_CHAR = "=";

    /**
     * 解析文件节点
     * @param fileElements
     * @param boxDiv
     * @param fileDivCss
     * @throws AppException
     */
    protected void parserFileElements(List<Element> fileElements, ContainHTMLElementTag boxDiv, String fileDivCss) throws AppException {
        // 文件导入元素节点
        if (!fileElements.isEmpty()) {
            DivElementTag fileDiv = new DivElementTag(super.getParser().getGroup().name() + _FILE_DIV_ID, fileDivCss);
            for (Element e : fileElements) {

                parserLinkElement(e, fileDiv, super.getParser().getValue(e));

                InputElementTag inputElement = new InputElementTag();
                inputElement.setId(e.id());
                inputElement.setType(e.type().name());
                fileDiv.addChildrenTag(inputElement);

                inputElement = new InputElementTag();
                inputElement.setId(_FILE_IMPORT_PREFIX + e.id());
                inputElement.setType(ElementEnum.BUTTON.name());
                inputElement.setValue(Element.LabelContent.getLabelContent(_IMPORT_BUTTOn_VALUE));
                fileDiv.addChildrenTag(inputElement);
            }
            boxDiv.addChildrenTag(fileDiv);
        }
    }

    /**
     * 设定Link元素的锚的内容
     * @param href
     * @param parameter
     * @param e
     * @throws AppException
     */
    @Override
    protected void settingLinkHref(StringBuilder href, String parameter, Object e) throws AppException {
        href.append(parameter).append(_PARAMETER_EQUAL_CHAR).append(super.getParser().getValue(parameter, e instanceof Element ? ((Element) e).format() : null));
    }
}
