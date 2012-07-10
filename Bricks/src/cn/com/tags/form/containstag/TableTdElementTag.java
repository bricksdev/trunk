package cn.com.tags.form.containstag;

import cn.com.tags.TagWriter;
import cn.com.tags.form.ContainHTMLElementTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class TableTdElementTag extends ContainHTMLElementTag {

    private static final String _TAGNAME = "td";

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
        super.doChildrenTag(tagWriter);
        tagWriter.endTag(true, super.getValue());
    }


}
