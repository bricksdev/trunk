package cn.com.elements.singletag.input;

import cn.com.elements.TagWriter;
import cn.com.elements.singletag.SingleHTMLElementTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class InputElementTag extends SingleHTMLElementTag {

    private static final String _ACCESSKEY = "accesskey";
    private static final String _DISABLED = "disabled";
    private static final String _ONBLUR = "onblur";
    private static final String _ONCHANGE = "onchange";
    private static final String _ONFOCUS = "onfocus";
    private static final String _READONLY = "readonly";
    private static final String _MAXLENGTH = "maxlength";
    private static final String _TYPE = "type";
    private static final String _TAGNAME = "input";
    private static final String _REQUIRED = "ifrequired";
    private boolean required = Boolean.FALSE;
    private String onfocus = null;
    private String onblur = null;
    private String onchange = null;
    private String accesskey = null;
    private boolean disabled = Boolean.FALSE;
    private boolean readonly = Boolean.FALSE;
    private String alt = null;
    private int maxLength = -1;
    private String type = null;

    public void setOnfocus(String onfocus) {
        this.onfocus = onfocus;
    }

    protected String getOnfocus() {
        return this.onfocus;
    }

    public void setOnblur(String onblur) {
        this.onblur = onblur;
    }

    protected String getOnblur() {
        return this.onblur;
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

        super.writeOptionalAttribute(tagWriter, _ONFOCUS, getOnfocus());
        super.writeOptionalAttribute(tagWriter, _ONBLUR, getOnblur());
        super.writeOptionalAttribute(tagWriter, _ONCHANGE, getOnchange());
        super.writeOptionalAttribute(tagWriter, _ACCESSKEY, getAccesskey());
        super.writeOptionalAttribute(tagWriter, _TYPE, getType());
        super.writeOptionalAttribute(tagWriter, _VALUE, getValue());
        if (isDisabled()) {
            tagWriter.writeAttribute(_DISABLED, _DISABLED);
        }

        if (isReadonly()) {
            super.writeOptionalAttribute(tagWriter, _READONLY, _READONLY);
        }

        if (getMaxLength() > 0) {
            super.writeOptionalAttribute(tagWriter, _MAXLENGTH, String.valueOf(getMaxLength()));
        }
        super.writeOptionalAttribute(tagWriter, _REQUIRED, String.valueOf(isRequired()));
        super.writeOptionalAttribute(tagWriter, _NAME, getId());
    }

    /**
     * @return the alt
     */
    public String getAlt() {
        return alt;
    }

    /**
     * @param alt the alt to set
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    public void doTag(TagWriter tagWriter) throws JspException, IOException {
        tagWriter.startTag(_TAGNAME);
        // 属性写入
        this.writeOptionalAttributes(tagWriter);
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

    /**
     * @return the maxLength
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * @param maxLength the maxLength to set
     */
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
