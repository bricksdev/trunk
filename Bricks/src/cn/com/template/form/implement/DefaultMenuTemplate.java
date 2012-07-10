/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.form.implement;

import cn.com.factory.OperatFactory;
import cn.com.manager.services.MenuService;
import cn.com.tags.AbstractHtmlElementTag;
import cn.com.tags.TagWriter;
import cn.com.tags.form.containstag.LIElementTag;
import cn.com.tags.form.containstag.ULElementTag;
import cn.com.tags.form.singletag.LinkElementTag;
import cn.com.template.form.MenuTemplate;
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
public class DefaultMenuTemplate implements MenuTemplate {

    @Override
    public String content() {

        List<Menu> menus = OperatFactory.getServiceObject(MenuService.class, DataProviderModel.FILE).getMenus();
        AbstractHtmlElementTag ulElement = createMenus(menus, 0);
        TagWriter writer = new TagWriter(new CharArrayWriter());
        try {
            ulElement.doTag(writer);
        } catch (JspException ex) {
            Logger.getLogger(DefaultMenuTemplate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DefaultMenuTemplate.class.getName()).log(Level.SEVERE, null, ex);
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
            String cssClass = (levelCount == 0 ? "hide" : "");
            AbstractHtmlElementTag childElement = null;
            for (Menu m : menus) {
                liElement = new LIElementTag(m.getId());
                linkElement = new LinkElementTag();
                linkElement.setHref(StringUtil.isEmpty(m.getUrl()) ? "#" : m.getUrl());
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
