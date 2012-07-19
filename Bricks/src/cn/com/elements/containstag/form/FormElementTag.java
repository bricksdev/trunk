package cn.com.elements.containstag.form;

import cn.com.elements.TagWriter;
import cn.com.elements.containstag.ContainHTMLElementTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class FormElementTag extends ContainHTMLElementTag {

    private static final String _TAGNAME = "form";
    private static final String _ACTION = "action";
    private static final String _METHOD = "method";
    private static final String _ENCTYPE = "enctype";
    private String action = null;
    private String method = null;
    private String enctype = null;

    @Override
    protected void writeOptionalAttributes(TagWriter tagWriter)
            throws JspException {
        super.writeOptionalAttributes(tagWriter);
        super.writeOptionalAttribute(tagWriter, _ACTION, action);
        super.writeOptionalAttribute(tagWriter, _METHOD, method);
        super.writeOptionalAttribute(tagWriter, _ENCTYPE, enctype);
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the enctype
     */
    public String getEnctype() {
        return enctype;
    }

    /**
     * @param enctype the enctype to set
     */
    public void setEnctype(String enctype) {
        this.enctype = enctype;
    }

    @Override
    public void doTag(TagWriter tagWriter) throws JspException, IOException {
        tagWriter.startTag(_TAGNAME);
        // 属性写入
        this.writeOptionalAttributes(tagWriter);
        super.doChildrenTag(tagWriter);
        tagWriter.endTag();
    }
}
