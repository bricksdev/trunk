/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements.component;

import cn.com.annotations.parser.AnnotationParser;
import cn.com.exceptions.AppException;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.wapps.permission.Permission;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xy
 */
public class ContextComponent {

    private List<ViewsComponent> components = new ArrayList<ViewsComponent>(3);
    private AnnotationParser parser = null;
    private ContainHTMLElementTag parentElement = null;
    private TabIndex tabIndex = null;
    private Permission permission = null;

    public ContextComponent(ContainHTMLElementTag parentElement, AnnotationParser parser, Permission permission,
        TabIndex tabIndex) {
        this.parentElement = parentElement;
        this.parser = parser;
        this.tabIndex = tabIndex;
        this.permission = permission;
    }

    public void processComponents() throws AppException {
        for (ViewsComponent component : components) {
            component.parser(parentElement);
        }
    }

    public void addComponent(ViewsComponent component) {
        component.setAnnotationParser(parser);
        component.setTabIndex(tabIndex);
        component.setPermission(permission);
        components.add(component);
    }
}
