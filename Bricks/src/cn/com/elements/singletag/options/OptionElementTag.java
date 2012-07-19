package cn.com.elements.singletag.options;

import cn.com.elements.TagWriter;
import cn.com.elements.singletag.SingleHTMLElementTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class OptionElementTag extends SingleHTMLElementTag {

    private final static String _TAGNAME = "option";
    private final static String _SELECTED = "selected";
    private String display = null;
    private boolean selected = false;

    @Override
    protected void writeOptionalAttributes(TagWriter tagWriter)
            throws JspException {
        super.writeOptionalAttributes(tagWriter);

        writeOptionalAttribute(tagWriter, _VALUE, getValue());
        if(isSelected()){
            writeOptionalAttribute(tagWriter,_SELECTED, _SELECTED);
        }
    }

    @Override
    public void doTag(TagWriter tagWriter) throws JspException, IOException {
        tagWriter.startTag(_TAGNAME);
        // 属性写入
        this.writeOptionalAttributes(tagWriter);
        tagWriter.endTag(true, getDisplay());
    }

    /**
     * @return the display
     */
    public String getDisplay() {
        return display;
    }

    /**
     * @param display the display to set
     */
    public void setDisplay(String display) {
        this.display = display;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
