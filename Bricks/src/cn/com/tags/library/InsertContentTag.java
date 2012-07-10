/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.library;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kete
 */
public class InsertContentTag extends SimpleTagSupport {

    public InsertContentTag() {
    }

    /**
     * Called by the container to invoke this tag.
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            PageContext context = (PageContext) getJspContext();
            String content = (String) context.getAttribute("__content", PageContext.APPLICATION_SCOPE);

            out.println(content);

        } catch (java.io.IOException ex) {
            throw new JspException("Error in InsertContentTag tag", ex);
        }
    }
}
