/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wapps.style.factory;

import cn.com.template.ContentTemplate;
import cn.com.template.MenuTemplate;
import cn.com.template.TasksTemplate;
import cn.com.template.implement.wms.WmsContentTemplate;
import cn.com.template.implement.wms.WmsMenuTemplate;
import cn.com.template.implement.wms.WmsTasksTemplate;

/**
 *
 * @author kete
 */
public class WmsTemplateFactory implements TemplateFactory {

    @Override
    public ContentTemplate getContentTemplate(String style) {
        ContentTemplate content = new WmsContentTemplate();
        content.setStyle(style);
        return content;
    }

    @Override
    public MenuTemplate getMenuTemplate() {
        return new WmsMenuTemplate();
    }

    @Override
    public TasksTemplate getTasksTemplate() {

        return new WmsTasksTemplate();
    }
}
