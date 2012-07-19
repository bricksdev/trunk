/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template;

import cn.com.annotations.Form;
import cn.com.annotations.Group;
import cn.com.template.groups.enums.GroupTemplateType;
import cn.com.annotations.parser.AnnotationParser;
import cn.com.codes.BricksMessagesCodes;
import cn.com.exceptions.AppException;
import cn.com.elements.TagWriter;
import cn.com.elements.component.ContextComponent;
import cn.com.elements.component.TabIndex;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.elements.containstag.form.FormElementTag;
import cn.com.elements.containstag.div.DivElementTag;
import cn.com.utils.StringUtil;
import cn.com.wapps.permission.Permission;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;

/**
 *
 * @author kete
 */
public abstract class ContentTemplate implements Template {

    private static final String _ACTION = "action";
    private static final String _CONDITION = "condition";
    private static final String _TABLE = "table";
    private static final String _TOP_CONTAINER = "_topContainer";
    private AnnotationParser parser = null;
    private Permission permission = null;
    private String style = null;

    public void setParser(AnnotationParser parser) {
        this.parser = parser;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public AnnotationParser getParser() {
        return parser;
    }

    public Permission getPermission() {
        return permission;
    }

    /**
     * 获取组模版
     * @return
     */
    public GroupTemplateType getGroupTemplateType() throws AppException {
        GroupTemplateType template = GroupTemplateType.valueOf(getStyle());
        if (parser.getGroup().template() != null && !StringUtil.isEmpty(parser.getGroup().template().template())) {
            String templateName = StringUtil.joinString(_SPLITER_CHAE, getStyle(), parser.getGroup().template().template().toUpperCase());
            try {

                template = GroupTemplateType.valueOf(templateName);
            } catch (Throwable ex) {
                throw new AppException(BricksMessagesCodes._E00011, ex, templateName);
            }
        }

        return template;
    }

    @Override
    public String content() throws AppException {
        TagWriter writer = null;
        try {
            writer = new TagWriter(new CharArrayWriter());

            Form form = parser.getForm();
            ContainHTMLElementTag topElement = null;
            if ((form != null && !(parser.getGroup() instanceof Group.DEFAULT) && !form.id().equals(Form.__DEFAULT_FORM_NAME)) || parser.getGroup() instanceof Group.DEFAULT) {

                // 生成form标签
                FormElementTag formElement = new FormElementTag();
                formElement.setAction(form.action());
                formElement.setEnctype(form.enctype());
                formElement.setMethod(form.method().name());
                formElement.setId(form.id());
                formElement.setCssClass(form.cssClass());
                topElement = formElement;
            } else {

                DivElementTag divElement = new DivElementTag(_TOP_CONTAINER);
                topElement = divElement;
            }
            this.parserComponents(topElement);
            topElement.doTag(writer);
            return writer.getWritedString();
        } catch (JspException ex) {
            Logger.getLogger(ContentTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContentTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        return null;
    }

    /**
     * 解析组件
     * @param parentElement
     * @throws AppException
     */
    protected abstract void parserComponents(ContainHTMLElementTag parentElement) throws AppException;

    /**
     * 实际组装处理
     * @param parser
     * @param parentElement
     * @param group
     */
    protected void parser(String templateStyle, ContainHTMLElementTag parentElement) throws AppException {
        Group group = parser.getGroup();
        if (group != null) {
            DivElementTag contentDiv = new DivElementTag(group.name(), templateStyle);

            ContextComponent context = new ContextComponent(contentDiv, parser, permission, new TabIndex());

            if (parser.getCurrentComponents() == null) {
                //条件标签
                context.addComponent(getGroupTemplateType().getComponent(_CONDITION));
                // 表格标签
                context.addComponent(getGroupTemplateType().getComponent(_TABLE));
                // 事件标注
                context.addComponent(getGroupTemplateType().getComponent(_ACTION));
            } else {
                for (String component : parser.getCurrentComponents()) {
                    //
                    context.addComponent(getGroupTemplateType().getComponent(component));
                }
            }

            context.processComponents();
            parentElement.addChildrenTag(contentDiv);
        }
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }
}
