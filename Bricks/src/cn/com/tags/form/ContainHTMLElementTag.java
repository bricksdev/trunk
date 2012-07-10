/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.form;

import cn.com.tags.AbstractHtmlElementTag;
import cn.com.tags.TagWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.jsp.JspException;

/**
 *
 * @author kete
 */
public abstract class ContainHTMLElementTag extends AbstractHtmlElementTag {

    private List<AbstractHtmlElementTag> childrenTags = new ArrayList<AbstractHtmlElementTag>(5);

    public void addChildrenTag(AbstractHtmlElementTag childrenTag) {
        childrenTags.add(childrenTag);
    }

    public void addChildrenTags(List<AbstractHtmlElementTag> childrenTags) {
        childrenTags.addAll(childrenTags);
    }

    /**
     * 处理子标签
     * @param tagWriter
     * @throws JspException
     * @throws IOException
     */
    protected void doChildrenTag(TagWriter tagWriter) throws JspException, IOException {
        for (AbstractHtmlElementTag child : this.childrenTags) {
            // 处理意外情况下 保存子标签为空不处理
            if (child != null) {
                child.doTag(tagWriter);
            }
        }
    }
}
