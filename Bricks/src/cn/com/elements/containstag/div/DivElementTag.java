package cn.com.elements.containstag.div;

import cn.com.elements.TagWriter;
import cn.com.elements.containstag.ContainHTMLElementTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class DivElementTag extends ContainHTMLElementTag {

    private static final String _TAGNAME = "div";

    public DivElementTag() {
    }

    public DivElementTag(String id) {
        super.setId(id);
    }

    public DivElementTag(String id, String cssClass) {
        super.setId(id);
        super.setCssClass(cssClass);
    }

    public DivElementTag(String id, String cssClass, String cssStyle) {
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