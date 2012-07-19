/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wapps.style;

import cn.com.template.ContentTemplate;
import cn.com.template.MenuTemplate;
import cn.com.template.TasksTemplate;
import cn.com.template.Template;
import cn.com.wapps.style.factory.DefaultTemplateFactory;
import cn.com.wapps.style.factory.TemplateFactory;
import cn.com.wapps.style.factory.WmsTemplateFactory;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kete
 */
public enum ApplicationStyle {

    DEFAULT(new DefaultTemplateFactory(), "/templates/default"),
    WMS(new WmsTemplateFactory(), "/templates/wms");
    private Map<Class<? extends Template>, Template> _TEMPLATES = new HashMap<Class<? extends Template>, Template>(3);
    private String templatePath = null;
    private static ApplicationStyle currentStyle = null;

    private ApplicationStyle(TemplateFactory templateFactory, String templatePath) {
        this.templatePath = templatePath;
        this._TEMPLATES.put(MenuTemplate.class, templateFactory.getMenuTemplate());
        this._TEMPLATES.put(TasksTemplate.class, templateFactory.getTasksTemplate());
        this._TEMPLATES.put(ContentTemplate.class, templateFactory.getContentTemplate(this.name()));
    }

    public static ApplicationStyle getCurrentStyle() {
        return currentStyle;
    }

    public static void setCurrentStyle(ApplicationStyle style) {
        currentStyle = style;
    }

    /**
     * 获取风格模板
     * @param templateType
     * @return
     */
    public Template getTemplate(Class<? extends Template> templateType) {
        return _TEMPLATES.get(templateType);
    }

    public String getTemplatePath() {
        return templatePath;
    }
}
