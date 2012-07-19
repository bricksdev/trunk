/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.factory;

import cn.com.elements.component.ViewsComponent;
import cn.com.elements.component.style.DefaultActionComponent;
import cn.com.elements.component.style.DefaultConditionComponent;
import cn.com.elements.component.style.DefaultTableComponent;

/**
 *
 * @author kete
 */
public class DefaultComponentFactory implements ComponentFactory {

    @Override
    public ViewsComponent getConditionComponent() {

        return new DefaultConditionComponent();
    }

    @Override
    public ViewsComponent getTableComponent() {
        return new DefaultTableComponent();
    }

    @Override
    public ViewsComponent getActionComponent() {

        return new DefaultActionComponent();
    }
}
