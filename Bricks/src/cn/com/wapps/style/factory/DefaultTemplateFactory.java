/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wapps.style.factory;

import cn.com.template.ContentTemplate;
import cn.com.template.MenuTemplate;
import cn.com.template.TasksTemplate;
import cn.com.template.implement.DefaultContentTemplate;
import cn.com.template.implement.DefaultMenuTemplate;
import cn.com.template.implement.DefaultTasksTemplate;

/**
 *
 * @author kete
 */
public class DefaultTemplateFactory implements TemplateFactory {

    @Override
    public ContentTemplate getContentTemplate(String style) {
        ContentTemplate content = new DefaultContentTemplate();
        content.setStyle(style);
        return content;
    }

    @Override
    public MenuTemplate getMenuTemplate() {
        return new DefaultMenuTemplate();
    }

    @Override
    public TasksTemplate getTasksTemplate() {

        return new DefaultTasksTemplate();
    }
}
