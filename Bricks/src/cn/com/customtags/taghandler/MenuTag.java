/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.customtags.taghandler;

import cn.com.exceptions.AppException;
import cn.com.template.MenuTemplate;
import cn.com.wapps.style.ApplicationStyle;
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
public class MenuTag extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag.
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            ApplicationStyle style = (ApplicationStyle) ((PageContext) getJspContext()).getAttribute("_style", PageContext.APPLICATION_SCOPE);
            out.println(style.getTemplate(MenuTemplate.class).content());


        } catch (AppException ex) {
            Logger.getLogger(MenuTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.io.IOException ex) {
            throw new JspException("Error in MenuNavigationTag tag", ex);
        }
    }
}
