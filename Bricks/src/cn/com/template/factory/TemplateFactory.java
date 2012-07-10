/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.factory;

import cn.com.annotations.enums.FormTemplateType;
import cn.com.refects.InstanceCreator;
import cn.com.template.form.FormStrategy;
import cn.com.template.form.MenuTemplate;
import cn.com.template.form.TasksTemplate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kete
 */
public class TemplateFactory {

    /**
     * 获取策略模板
     * @param type
     * @return
     */
    public static FormStrategy getFormTemplate(FormTemplateType type) {
        FormStrategy template = null;
        try {
            template = (FormStrategy) InstanceCreator.getInstance(type.getTemplateClass().getName());
        } catch (Exception ex) {
            Logger.getLogger(TemplateFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return template;
    }

    public static MenuTemplate getMenuTemplate(String style) {
        if ("1".equals(style)) {
            return null;
        } else {
            return DefaultTemplateFactory.getMenuTemplate();
        }
    }

    public static TasksTemplate getTasksTemplate(String style) {
        if ("1".equals(style)) {
            return null;
        } else {
            return DefaultTemplateFactory.getTasksTemplate();
        }
    }
}
