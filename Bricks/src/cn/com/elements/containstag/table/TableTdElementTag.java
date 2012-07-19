package cn.com.elements.containstag.table;

import cn.com.elements.TagWriter;
import cn.com.elements.containstag.ContainHTMLElementTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class TableTdElementTag extends ContainHTMLElementTag {

    private static final String _TAGNAME = "td";
    private static final String _COLSPAN = "colspan";
    private String colspan = null;


    public TableTdElementTag() {
    }

    public TableTdElementTag(String id) {
        super.setId(id);
    }

    @Override
    public void doTag(TagWriter tagWriter) throws JspException, IOException {
        tagWriter.startTag(_TAGNAME);
        // 属性写入
        this.writeOptionalAttributes(tagWriter);
        super.writeOptionalAttribute(tagWriter, _COLSPAN, colspan);
        super.doChildrenTag(tagWriter);
        tagWriter.endTag(true, super.getValue());
    }

    /**
     * @return the colspan
     */
    public String getColspan() {
        return colspan;
    }

    /**
     * @param colspan the colspan to set
     */
    public void setColspan(String colspan) {
        this.colspan = colspan;
    }


}
