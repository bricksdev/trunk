/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.factory;

import cn.com.elements.component.ViewsComponent;

/**
 *
 * @author kete
 */
public interface ComponentFactory {

    public ViewsComponent getConditionComponent();

    public ViewsComponent getTableComponent();

    public ViewsComponent getActionComponent();
}
