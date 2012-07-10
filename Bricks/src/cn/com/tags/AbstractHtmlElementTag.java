package cn.com.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
//extends SimpleTagSupport
public abstract class AbstractHtmlElementTag {

    private static final String _CLASS = "class";
    private static final String _DIR = "dir";
    private static final String _ID = "id";
    private static final String _LANG = "lang";
    protected static final String _NAME = "name";
    protected static final String _VALUE = "value";
    private static final String _ONCLICK = "onclick";
    private static final String _ONDBLCLICK = "ondblclick";
    private static final String _ONKEYDOWN = "onkeydown";
    private static final String _ONKEYPRESS = "onkeypress";
    private static final String _ONKEYUP = "onkeyup";
    private static final String _ONMOUSEDOWN = "onmousedown";
    private static final String _ONMOUSEOUT = "onmouseout";
    private static final String _ONMOUSEOVER = "onmouseover";
    private static final String _ONMOUSEUP = "onmouseup";
    private static final String _STYLE = "style";
    private static final String _TABINDEX = "tabindex";
    private static final String _TITLE = "title";
    private String cssClass = null;
    private String value = null;
    private String cssStyle = null;
    private String lang = null;
    private String title = null;
    private String dir = null;
    private String tabindex = null;
    private String onclick = null;
    private String ondblclick = null;
    private String onmousedown = null;
    private String onmouseup = null;
    private String onmouseover = null;
    private String onmousemove = null;
    private String onmouseout = null;
    private String onkeypress = null;
    private String onkeyup = null;
    private String onkeydown = null;
    private String id = null;

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    protected String getCssClass() {
        return this.cssClass;
    }

    public void setValue(String value) {
        this.value = value;
    }

    protected String getValue() {
        return this.value;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    protected String getCssStyle() {
        return this.cssStyle;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    protected String getLang() {
        return this.lang;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected String getTitle() {
        return this.title;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    protected String getDir() {
        return this.dir;
    }

    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }

    protected String getTabindex() {
        return this.tabindex;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    protected String getOnclick() {
        return this.onclick;
    }

    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
    }

    protected String getOndblclick() {
        return this.ondblclick;
    }

    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }

    protected String getOnmousedown() {
        return this.onmousedown;
    }

    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
    }

    protected String getOnmouseup() {
        return this.onmouseup;
    }

    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
    }

    protected String getOnmouseover() {
        return this.onmouseover;
    }

    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
    }

    protected String getOnmousemove() {
        return this.onmousemove;
    }

    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }

    protected String getOnmouseout() {
        return this.onmouseout;
    }

    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }

    protected String getOnkeypress() {
        return this.onkeypress;
    }

    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }

    protected String getOnkeyup() {
        return this.onkeyup;
    }

    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
    }

    protected String getOnkeydown() {
        return this.onkeydown;
    }

    protected void writeOptionalAttributes(TagWriter tagWriter)
            throws JspException {
        writeOptionalAttribute(tagWriter, _CLASS, getCssClass());
        writeOptionalAttribute(tagWriter, _STYLE, getCssStyle());
        writeOptionalAttribute(tagWriter, _LANG, getLang());
        writeOptionalAttribute(tagWriter, _TITLE, getTitle());
        writeOptionalAttribute(tagWriter, _DIR, getDir());
        writeOptionalAttribute(tagWriter, _TABINDEX, getTabindex());
        writeOptionalAttribute(tagWriter, _ONCLICK, getOnclick());
        writeOptionalAttribute(tagWriter, _ONDBLCLICK, getOndblclick());
        writeOptionalAttribute(tagWriter, _ONMOUSEDOWN, getOnmousedown());
        writeOptionalAttribute(tagWriter, _ONMOUSEUP, getOnmouseup());
        writeOptionalAttribute(tagWriter, _ONMOUSEOVER, getOnmouseover());
        writeOptionalAttribute(tagWriter, _ONMOUSEOUT, getOnmouseout());
        writeOptionalAttribute(tagWriter, _ONKEYPRESS, getOnkeypress());
        writeOptionalAttribute(tagWriter, _ONKEYUP, getOnkeyup());
        writeOptionalAttribute(tagWriter, _ONKEYDOWN, getOnkeydown());

        writeOptionalAttribute(tagWriter, _ID, getId());
//        writeOptionalAttribute(tagWriter, _NAME, getId());

    }

    protected void writeOptionalAttribute(TagWriter tagWriter, String propertyName, String propertyValue) throws JspException {
        tagWriter.writeOptionalAttributeValue(propertyName, propertyValue);
    }

    public abstract void doTag(TagWriter tagWriter) throws JspException, IOException;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
