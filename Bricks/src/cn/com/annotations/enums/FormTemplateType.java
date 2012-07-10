/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations.enums;

import cn.com.template.form.implement.DefaultFormTemplate;
import cn.com.template.form.implement.wms.WmsFormTemplate;

/**
 *
 * @author kete
 */
public enum FormTemplateType {

    DEFAULT(DefaultFormTemplate.class),
    WMS(WmsFormTemplate.class);
//    CUSTOM; //在此添加新的模板
    private Class templateClass = null;

    private FormTemplateType(Class templateClass) {
        this.templateClass = templateClass;
    }

    public Class getTemplateClass() {
        return templateClass;
    }
}
