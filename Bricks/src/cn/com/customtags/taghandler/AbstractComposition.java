/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.customtags.taghandler;

import cn.com.wapps.style.ApplicationStyle;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kete
 */
public abstract class AbstractComposition extends SimpleTagSupport implements TagHandlerConst {

    public AbstractComposition() {
    }

    /**
     * 获取并设定当前应用的风格
     * @param pageContext
     * @return
     */
    protected ApplicationStyle settingAppStyle(PageContext pageContext) {
        String styleName = null;
        ApplicationStyle style = (ApplicationStyle) pageContext.getAttribute(_STYLE_ID, PageContext.APPLICATION_SCOPE);
        if (style == null) {
            // TODO 是否采用配置
            style = ApplicationStyle.WMS;
            pageContext.setAttribute(_STYLE_ID, style, PageContext.APPLICATION_SCOPE);
        } else {
            styleName = (String) pageContext.getAttribute(_PAGE_STYLE_ID, PageContext.REQUEST_SCOPE);
            if (styleName != null) {
                style = ApplicationStyle.valueOf(styleName);
                pageContext.setAttribute(_STYLE_ID, style, PageContext.APPLICATION_SCOPE);
            }
        }
        ApplicationStyle.setCurrentStyle(style);
        pageContext.setAttribute(_PAGE_STYLE_ID, style.name(), PageContext.APPLICATION_SCOPE);
        return style;
    }

    /**
     * 设定标签内容及标签的标题到下一个页面
     * @param pageContext
     * @param content
     * @param title
     */
    protected void settingContentScope(PageContext pageContext, String content, String title) {
        // 保存变量到到下一个标签用
        pageContext.setAttribute(_PAGE_SCOPE_CONTENT_ID, content, PageContext.APPLICATION_SCOPE);
        pageContext.setAttribute(_PAGE_SCOPE_TITLE_ID, title, PageContext.APPLICATION_SCOPE);
    }

}
