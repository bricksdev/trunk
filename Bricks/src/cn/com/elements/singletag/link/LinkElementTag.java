package cn.com.elements.singletag.link;

import cn.com.elements.TagWriter;
import cn.com.elements.singletag.SingleHTMLElementTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class LinkElementTag extends SingleHTMLElementTag {

    private static final String _TAGNAME = "a";
    private static final String _HREF = "href";
    private String href = null;

    @Override
    public void doTag(TagWriter tagWriter) throws JspException, IOException {
        tagWriter.startTag(_TAGNAME);
        super.writeOptionalAttributes(tagWriter);
        // 属性写入
        super.writeOptionalAttribute(tagWriter, _HREF, href);
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
