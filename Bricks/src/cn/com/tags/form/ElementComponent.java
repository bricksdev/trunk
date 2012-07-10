/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.form;

import cn.com.annotations.Group;
import cn.com.annotations.parser.AnnotationParser;
import cn.com.tags.form.component.TabIndex;

/**
 * 元素组件
 *
 * @author xy
 */
public abstract class ElementComponent {

    protected static final String _SPLIT_DO = ".";
    protected Group group = null;
    protected AnnotationParser parser = null;
    protected TabIndex tabIndex = null;

    public void setAnnotationParser(AnnotationParser parser) {
        this.parser = parser;
        this.group = parser.getGroup();
    }

    public void setTabIndex(TabIndex tabIndex) {
        this.tabIndex = tabIndex;
    }

    public abstract void parser(ContainHTMLElementTag parentElement);
}
