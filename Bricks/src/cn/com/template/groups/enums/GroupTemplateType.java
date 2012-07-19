/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.groups.enums;

import cn.com.elements.component.ViewsComponent;
import cn.com.template.factory.ComponentFactory;
import cn.com.template.factory.DefaultComponentFactory;
import cn.com.template.factory.WmsComponentFactory;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kete
 */
public enum GroupTemplateType {

    DEFAULT(new DefaultComponentFactory()),
    WMS(new WmsComponentFactory());
    private Map<String, ViewsComponent> _COMPONENT = new HashMap<String, ViewsComponent>(3);

    private GroupTemplateType(ComponentFactory componentFactory) {
        _COMPONENT.put("action", componentFactory.getActionComponent());
        _COMPONENT.put("table", componentFactory.getTableComponent());
        _COMPONENT.put("condition", componentFactory.getConditionComponent());
    }

    /**
     * 获取组件试图
     * @param componentName
     * @return
     */
    public ViewsComponent getComponent(String componentName) {
        return _COMPONENT.get(componentName);
    }


}
