/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.form;

import cn.com.annotations.Group;
import cn.com.annotations.parser.AnnotationParser;
import cn.com.tags.TagWriter;
import cn.com.tags.form.ContainHTMLElementTag;
import cn.com.tags.form.containstag.FormElementTag;
import cn.com.wapps.permission.Permission;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;

/**
 * 解析Bean策略的抽象模板
 *
 * @author kete
 */
public abstract class FormTemplate implements FormStrategy {

    @Override
    public String process(AnnotationParser parser, Permission permission) {
        String htmlStr = "";
        //加入权限

        //解析注解信息
        htmlStr = this.content(parser, permission);
        return htmlStr;
    }

    private String content(AnnotationParser parser, Permission permission) {
        try {
            TagWriter writer = new TagWriter(new CharArrayWriter());
            // 生成form标签
            FormElementTag mainElement = new FormElementTag();
            mainElement.setAction(parser.getForm().action());
            mainElement.setEnctype(parser.getForm().enctype());
            mainElement.setMethod(parser.getForm().method().name());
            mainElement.setId(parser.getForm().id());
            mainElement.setCssClass(parser.getForm().cssClass());
            Group group = parser.getGroup();
            this.parserComponents(parser, mainElement, group, permission);
            mainElement.doTag(writer);
            return writer.getWritedString();
        } catch (JspException ex) {
            Logger.getLogger(FormTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 解析组件
     * @param parser
     * @param parentElement
     * @param group
     */
    protected abstract void parserComponents(AnnotationParser parser, ContainHTMLElementTag parentElement, Group group, Permission permission);
}
