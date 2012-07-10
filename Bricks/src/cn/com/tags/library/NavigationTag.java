/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.library;

import cn.com.template.factory.TemplateFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kete
 */
public class NavigationTag extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag.
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
//
//        try {
//            String style = (String) ((PageContext) getJspContext()).findAttribute("__style");
//            out.println(TemplateFactory.getTasksTemplate(style).content());
//
//        } catch (java.io.IOException ex) {
//            throw new JspException("Error in TasksNavigationTag tag", ex);
//        }
    }
}
