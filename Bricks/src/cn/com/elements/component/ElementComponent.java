/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements.component;

import cn.com.annotations.Element;
import cn.com.annotations.Link;
import cn.com.annotations.enums.ElementEnum;
import cn.com.annotations.parser.AnnotationParser;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.elements.containstag.div.SpanElementTag;
import cn.com.elements.containstag.select.SelectElementTag;
import cn.com.elements.singletag.input.InputElementTag;
import cn.com.elements.singletag.link.LinkElementTag;
import cn.com.elements.singletag.options.OptionElementTag;
import cn.com.exceptions.AppException;
import cn.com.utils.StringUtil;
import cn.com.wapps.permission.Permission;

/**
 * 元素组件
 *
 * @author xy
 */
public abstract class ElementComponent implements ViewsComponent {

    private static final String _REQUIRED_DIV_ID = "RequiredDiv";
    private static final String _REQUIRED_VALUE = "*";
    private static final String _SECURIT_VALUE = "***";
    private static final String _HREF_VALUE = "#";
    private static final String _PARAMETER_CONCATE_CHAR = "&";
    private static final String _PARAMETER_SIGN_CHAR = "?";
    private static final String _SHOW_CALENDAR_EVENT = "__showCalendar(this);";
    private static final String _SHOW_TIMET_EVENT = "__showTime(this);";
    private AnnotationParser parser = null;
    private TabIndex tabIndex = null;
    private Permission permission = null;

    @Override
    public void setAnnotationParser(AnnotationParser parser) {
        this.parser = parser;
    }

    @Override
    public void setTabIndex(TabIndex tabIndex) {
        this.tabIndex = tabIndex;
    }

    @Override
    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public AnnotationParser getParser() {
        return parser;
    }

    public Permission getPermission() {
        return permission;
    }

    public TabIndex getTabIndex() {
        return tabIndex;
    }

    /**
     * 获取焦点事件
     * @param e
     * @return
     */
    protected String getFocusEvent(Element e) {
        String focusEvent = null;
        switch (e.type()) {
            case DATE:
                focusEvent = _SHOW_CALENDAR_EVENT + e.onfocus();
                break;
            case DATE_TIME:
                focusEvent = _SHOW_TIMET_EVENT + e.onfocus();
                break;
            default:
                focusEvent = e.onfocus();
                break;
        }
        return focusEvent;
    }

    /**
     * input类型名称
     * @param e
     * @return
     */
    protected String getInputType(Element e) {
        String type = null;
        switch (e.type()) {
            case DATE:
                type = ElementEnum.TEXT.name();
                break;
            case DATE_TIME:
                type = ElementEnum.TEXT.name();
                break;
            default:
                type = e.type().name();
                break;
        }
        return type;
    }

    /**
     * 解析链接元素
     * @param e
     * @param elementDiv
     * @throws AppException
     */
    protected void parserLinkElement(Element e, ContainHTMLElementTag elementDiv, String value) throws AppException {
        if (e.link() != null) {
            LinkElementTag linkElement = new LinkElementTag();
            // 设定动态参数参数
            StringBuilder href = new StringBuilder(_HREF_VALUE);
            Link link = e.link();
            // 设定链接属性
            if (!StringUtil.isEmpty(link.url())) {
                href = new StringBuilder(link.url());
            }
            // 设定参数列表
            if (link.parameters() != null && link.parameters().length > 0) {
                if (href.indexOf(_PARAMETER_SIGN_CHAR) == -1) {
                    href.append(_PARAMETER_SIGN_CHAR);
                }
                int idx = 0;
                for (String parameter : link.parameters()) {
                    if (idx++ > 0) {
                        href.append(_PARAMETER_CONCATE_CHAR);
                    }
                    settingLinkHref(href, parameter, e);
                }
            }
            linkElement.setHref(href.toString());
            linkElement.setValue(value);
            linkElement.setOnclick(e.onclick());
            elementDiv.addChildrenTag(linkElement);
        }
    }

    /**
     * 设定Link元素的锚的内容
     * @param href
     * @param parameter
     * @param e
     * @throws AppException
     */
    protected abstract void settingLinkHref(StringBuilder href, String parameter, Object e) throws AppException;

    /**
     * 解析元素内容
     * @param parentId
     * @param e
     * @param elementDiv
     * @param inputCss
     * @param requiredCss
     * @param value
     * @throws AppException
     */
    protected void parserElement(String parentId, Element e, ContainHTMLElementTag elementDiv, String inputCss,
        String requiredCss, String value) throws AppException {
        switch (e.type()) {
            case LINK:
                parserLinkElement(e, elementDiv, value);
                break;
            case SELECT:
                parserSelectElement(parentId, e, inputCss, elementDiv, value);
                break;
            default:
                parserInputElement(parentId, e, inputCss, elementDiv, value);
                break;
        }
        // 是否必须元素处理
        if (StringUtil.isEmpty(parentId) && e.required()) {
            parserRequiredElement(e, elementDiv, requiredCss);
        }
    }

    /**
     * 解析必须输入项元素
     * @param column
     * @param contentDiv
     * @param requiredCss
     */
    protected void parserRequiredElement(Element column, ContainHTMLElementTag contentDiv, String requiredCss) {
        SpanElementTag requiredElement = new SpanElementTag(column.id() + _REQUIRED_DIV_ID, requiredCss);
        requiredElement.setValue(_REQUIRED_VALUE);
        contentDiv.addChildrenTag(requiredElement);
    }

    /**
     * 解析input元素内容
     * @param parentId
     * @param e
     * @param inputCss
     * @param elementDiv
     * @param value
     * @throws AppException
     */
    protected void parserInputElement(String parentId, Element e, String inputCss, ContainHTMLElementTag elementDiv,
        String value) throws AppException {
        InputElementTag inputElement = new InputElementTag();
        inputElement.setId(StringUtil.joinString(_SPLIT_DO, parentId, e.id()));
        inputElement.setMaxLength(e.maxLength());
        inputElement.setType(getInputType(e));
        inputElement.setValue(permission == null || permission.validateSecret(e.id()) ? value : _SECURIT_VALUE);
        inputElement.setOnblur(e.onblur());
        inputElement.setReadonly(e.readonly());
        String focusEvent = getFocusEvent(e);
        inputElement.setOnfocus(focusEvent);
        inputElement.setCssClass(inputCss);
        elementDiv.addChildrenTag(inputElement);
        // 作为异常报错的提示标示
        inputElement.setTitle(Element.LabelContent.getLabelContent(StringUtil.isEmpty(e.label()) ? e.id().toUpperCase() : e.label()));
        inputElement.setRequired(e.required());
        inputElement.setTabindex(String.valueOf(tabIndex.next()));
    }

    /**
     * 解析select元素内容
     * @param parentId
     * @param e
     * @param inputCss
     * @param elementDiv
     * @param value
     * @throws AppException
     */
    protected void parserSelectElement(String parentId, Element e, String inputCss, ContainHTMLElementTag elementDiv,
        String value) throws AppException {
        SelectElementTag selectElement = new SelectElementTag();
        String selectedValue = value;
        selectElement.setCssClass(inputCss);
        selectElement.setReadonly(e.readonly());
        selectElement.setDisabled(e.disabled());
        selectElement.setId(StringUtil.joinString(_SPLIT_DO, parentId, e.id()));
        String[] values = e.source().value();
        if (values != null && values.length > 0) {
            int idx = 0;
            for (String d : values) {
                OptionElementTag optionElement = new OptionElementTag();
                optionElement.setDisplay(Element.LabelContent.getLabelContent(e.source().display()[idx++]));
                optionElement.setValue(d);
                // 设定option被选择
                if (d.equals(selectedValue)) {
                    optionElement.setSelected(true);
                }
                selectElement.addChildrenTag(optionElement);
            }
        }
        // 作为异常报错的提示标示
        selectElement.setTitle(Element.LabelContent.getLabelContent(StringUtil.isEmpty(e.label()) ? e.id().toUpperCase() : e.label()));
        selectElement.setRequired(e.required());
        selectElement.setTabindex(String.valueOf(tabIndex.next()));
        elementDiv.addChildrenTag(selectElement);
    }
}
