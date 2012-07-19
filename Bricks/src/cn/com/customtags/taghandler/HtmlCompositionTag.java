/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.customtags.taghandler;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

/**
 *
 * @author kete
 */
public class HtmlCompositionTag extends AbstractComposition {

    /**
     * Called by the container to invoke this tag.
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }



        } catch (java.io.IOException ex) {
            throw new JspException("Error in HtmlCompositionTagHandler tag", ex);
        }
    }
}
