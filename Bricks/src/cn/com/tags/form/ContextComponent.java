/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.form;

import cn.com.annotations.parser.AnnotationParser;
import cn.com.tags.form.component.TabIndex;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xy
 */
public class ContextComponent {

    private List<ElementComponent> components = new ArrayList<ElementComponent>();
    private AnnotationParser parser=null;
    private ContainHTMLElementTag parentElement=null;
    private TabIndex tabIndex=null;

    public ContextComponent(ContainHTMLElementTag parentElement, AnnotationParser parser,TabIndex tabIndex) {
        this.parentElement = parentElement;
        this.parser = parser;
        this.tabIndex = tabIndex;
    }

    public void processComponents() {
        for (ElementComponent component : components) {
            component.parser(parentElement);
        }
    }

    public void addComponent(ElementComponent component) {
        component.setAnnotationParser(parser);
        component.setTabIndex(tabIndex);
        components.add(component);
    }
    
}
