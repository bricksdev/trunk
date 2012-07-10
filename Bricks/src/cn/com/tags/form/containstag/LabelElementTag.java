package cn.com.tags.form.containstag;

import cn.com.tags.TagWriter;
import cn.com.tags.form.ContainHTMLElementTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class LabelElementTag extends ContainHTMLElementTag {

    private static final String _TAGNAME = "label";

    public LabelElementTag() {
    }

    public LabelElementTag(String id, String cssClass) {
        super.setId(id);
        super.setCssClass(cssClass);
    }

    public LabelElementTag(String id, String cssClass, String cssStyle) {
        this(id, cssClass);
        super.setCssStyle(cssStyle);
    }

    @Override
    public void doTag(TagWriter tagWriter) throws JspException, IOException {
        tagWriter.startTag(_TAGNAME);
        // 属性写入
        this.writeOptionalAttributes(tagWriter);
        super.doChildrenTag(tagWriter);
        tagWriter.endTag(true, super.getValue());
    }
}
