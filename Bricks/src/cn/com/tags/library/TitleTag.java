/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.library;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kete
 */
public class TitleTag extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag.
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        try {
            JspWriter out = getJspContext().getOut();
            PageContext context = (PageContext) getJspContext();
            String title = (String) context.getAttribute("__title", PageContext.APPLICATION_SCOPE);
            if (title == null) {
                title = "Default Tile";
            }
            out.print("<title>"+title+"</title>");
        } catch (IOException ex) {
            Logger.getLogger(TitleTag.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
