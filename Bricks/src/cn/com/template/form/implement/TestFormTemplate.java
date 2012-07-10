/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.form.implement;

import cn.com.annotations.Group;
import cn.com.annotations.parser.AnnotationParser;
import cn.com.tags.form.ContainHTMLElementTag;
import cn.com.tags.form.ContextComponent;
import cn.com.tags.form.component.ActionComponent;
import cn.com.tags.form.component.ConditionComponent;
import cn.com.tags.form.component.TabIndex;
import cn.com.tags.form.component.TableComponent;
import cn.com.tags.form.containstag.DivElementTag;
import cn.com.template.form.FormTemplate;
import cn.com.wapps.permission.Permission;

/**
 *
 * @author xy
 */
public class TestFormTemplate extends FormTemplate {

    @Override
    protected void parserComponents(AnnotationParser parser, ContainHTMLElementTag parentElement, Group group, Permission permission) {
        if (group != null) {
            DivElementTag contentDiv = new DivElementTag(group.name(), "form_content");

            ContextComponent context = new ContextComponent(contentDiv, parser, new TabIndex());
            // 事件标注
            context.addComponent(new ActionComponent(permission));

            // 表格标签
            context.addComponent(new TableComponent());

             //条件标签
            context.addComponent(new ConditionComponent(permission));

            context.processComponents();
            parentElement.addChildrenTag(contentDiv);
        }
    }

}
