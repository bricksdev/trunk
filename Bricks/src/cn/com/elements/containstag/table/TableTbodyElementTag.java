package cn.com.elements.containstag.table;

import cn.com.elements.TagWriter;
import cn.com.elements.containstag.ContainHTMLElementTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class TableTbodyElementTag extends ContainHTMLElementTag {

    private static final String _TAGNAME = "tbody";

    public TableTbodyElementTag() {
    }

    public TableTbodyElementTag(String id) {
        super.setId(id);
    }

    public TableTbodyElementTag(String id, String cssClass) {
        this(id);
        super.setCssClass(cssClass);
    }

    public TableTbodyElementTag(String id, String cssClass, String cssStyle) {
        this(id, cssClass);
        super.setCssStyle(cssStyle);
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
