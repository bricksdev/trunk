/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.implement.wms;

import cn.com.exceptions.AppException;
import cn.com.factory.OperatFactory;
import cn.com.manager.services.MenuService;
import cn.com.elements.AbstractHtmlElementTag;
import cn.com.elements.TagWriter;
import cn.com.elements.containstag.ul.LIElementTag;
import cn.com.elements.containstag.ul.ULElementTag;
import cn.com.elements.singletag.link.LinkElementTag;
import cn.com.template.MenuTemplate;
import cn.com.utils.DataProviderConfig.DataProviderModel;
import cn.com.utils.StringUtil;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import cn.com.manager.domains.Menu;

/**
 *
 * @author kete
 */
public class WmsMenuTemplate implements MenuTemplate {
    private static final String _BLANK_CSS = "";
    private static final String _CURRENT_PAGE_HREF = "#";
    private static final String _UL_HIDE_CSS = "hide";

    @Override
    public String content()  throws AppException{

        List<Menu> menus = OperatFactory.getServiceObject(MenuService.class, DataProviderModel.FILE).getMenus();
        AbstractHtmlElementTag ulElement = createMenus(menus, 0);
        TagWriter writer = new TagWriter(new CharArrayWriter());
        try {
            ulElement.doTag(writer);
        } catch (JspException ex) {
            Logger.getLogger(WmsMenuTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WmsMenuTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return writer.getWritedString();
    }

    /**
     * 生成菜单
     * @param menus
     * @param parentElement
     */
    private AbstractHtmlElementTag createMenus(List<Menu> menus, int levelCount) {
        if (menus != null) {
            ULElementTag ulElement = new ULElementTag();
            LIElementTag liElement = null;
            LinkElementTag linkElement = null;
            String cssClass = (levelCount == 0 ? _UL_HIDE_CSS : _BLANK_CSS);
            AbstractHtmlElementTag childElement = null;
            for (Menu m : menus) {
                liElement = new LIElementTag(m.getId());
                linkElement = new LinkElementTag();
                linkElement.setHref(StringUtil.isEmpty(m.getUrl()) ? _CURRENT_PAGE_HREF : m.getUrl());
                linkElement.setValue(m.getLabel());
                linkElement.setCssClass(cssClass);
                liElement.addChildrenTag(linkElement);
                childElement = this.createMenus(m.getSubmenus(), 1);
                if (childElement != null) {
                    liElement.addChildrenTag(childElement);
                }
                ulElement.addChildrenTag(liElement);
            }

            return ulElement;

        }

        return null;
    }
}
