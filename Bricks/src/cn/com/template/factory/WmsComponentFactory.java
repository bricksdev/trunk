/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.factory;

import cn.com.elements.component.ViewsComponent;
import cn.com.elements.component.style.wms.WmsActionComponent;
import cn.com.elements.component.style.wms.WmsConditionComponent;
import cn.com.elements.component.style.wms.WmsTableComponent;

/**
 *
 * @author kete
 */
public class WmsComponentFactory implements ComponentFactory {

    @Override
    public ViewsComponent getConditionComponent() {

        return new WmsConditionComponent();
    }

    @Override
    public ViewsComponent getTableComponent() {
        return new WmsTableComponent();
    }

    @Override
    public ViewsComponent getActionComponent() {

        return new WmsActionComponent();
    }
}
