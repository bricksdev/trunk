/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wapps.style.factory;

import cn.com.template.ContentTemplate;
import cn.com.template.MenuTemplate;
import cn.com.template.TasksTemplate;

/**
 *
 * @author kete
 */
public interface TemplateFactory {

    public ContentTemplate getContentTemplate(String style);

    public MenuTemplate getMenuTemplate();

    public TasksTemplate getTasksTemplate();
}
