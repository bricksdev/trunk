package cn.com.tags.form.singletag;

import cn.com.tags.AbstractHtmlElementTag;
import cn.com.tags.TagWriter;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class LinkElementTag extends AbstractHtmlElementTag {

    private static final String _TAGNAME = "a";
    private static final String _HREF = "href";
    private String href = null;

    @Override
    public void doTag(TagWriter tagWriter) throws JspException, IOException {
        tagWriter.startTag(_TAGNAME);
        super.writeOptionalAttributes(tagWriter);
        // 属性写入
        this.writeOptionalAttribute(tagWriter, _HREF, href);
        tagWriter.endTag(true, getValue());
    }

    /**
     * @return the href
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href the href to set
     */
    public void setHref(String href) {
        this.href = href;
    }
}
