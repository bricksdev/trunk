package cn.com.tags.form.containstag;

import cn.com.tags.TagWriter;
import cn.com.tags.form.ContainHTMLElementTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class SelectElementTag extends ContainHTMLElementTag {

    private static final String _TAGNAME = "select";
    private static final String _ACCESSKEY = "accesskey";
    private static final String _DISABLED = "disabled";
    private static final String _ONCHANGE = "onchange";
    private static final String _ONFOCUS = "onfocus";
    private static final String _READONLY = "readonly";
    private static final String _REQUIRED = "ifrequired";
    private boolean required = Boolean.FALSE;
    private String onfocus = null;
    private String onchange = null;
    private String accesskey = null;
    private boolean disabled = Boolean.FALSE;
    private boolean readonly = Boolean.FALSE;

    public void setOnfocus(String onfocus) {
        this.onfocus = onfocus;
    }

    protected String getOnfocus() {
        return this.onfocus;
    }

    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    protected String getOnchange() {
        return this.onchange;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    protected String getAccesskey() {
        return this.accesskey;
    }

    @Override
    protected void writeOptionalAttributes(TagWriter tagWriter)
            throws JspException {
        super.writeOptionalAttributes(tagWriter);

        writeOptionalAttribute(tagWriter, _ONFOCUS, getOnfocus());
        writeOptionalAttribute(tagWriter, _ONCHANGE, getOnchange());
        writeOptionalAttribute(tagWriter, _ACCESSKEY, getAccesskey());
        writeOptionalAttribute(tagWriter, _VALUE, getValue());
        if (isDisabled()) {
            tagWriter.writeAttribute(_DISABLED, _DISABLED);
        }
        if (isReadonly()) {
            writeOptionalAttribute(tagWriter, _READONLY, _READONLY);
        }
        writeOptionalAttribute(tagWriter, _REQUIRED, String.valueOf(required));

    }

    /**
     * @return the disabled
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public void doTag(TagWriter tagWriter) throws JspException, IOException {
        tagWriter.startTag(_TAGNAME);
        // 属性写入
        this.writeOptionalAttributes(tagWriter);
        super.doChildrenTag(tagWriter);
        tagWriter.endTag();
    }

    /**
     * @return the readonly
     */
    public boolean isReadonly() {
        return readonly;
    }

    /**
     * @param readonly the readonly to set
     */
    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    /**
     * @return the required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required) {
        this.required = required;
    }
}
