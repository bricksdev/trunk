/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.library;

import cn.com.annotations.parser.AnnotationParser;
import cn.com.manager.PermissionManager;
import cn.com.refects.InstanceCreator;
import cn.com.template.facade.TemplateFacade;
import cn.com.wapps.permission.Permission;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author kete
 */
public class CompositionTag extends SimpleTagSupport {

    private String className = null;
    private String bundleObject = null;
    private String template = null;
    private String group = null;
    private boolean permission = Boolean.FALSE;
    private String permissionClass = null;

    /**
     * Called by the container to invoke this tag.
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        try {

            PageContext pageContext = (PageContext) getJspContext();
            Object bundleObj = pageContext.findAttribute(bundleObject);
            boolean ifIniting = false;
            if (bundleObj == null) {
                bundleObj = InstanceCreator.getInstance(className);
                ifIniting = true;
            }
            AnnotationParser parser = new AnnotationParser(bundleObj, group);
            // 设定元素的默认值
            if (ifIniting) {
                parser.settingDefaultValue();
            }

            // 设定许可
            Permission permissionImpl = null;
            if (isPermission()) {
                permissionImpl = PermissionManager.getUserPermission();
            }

            String content = TemplateFacade.getHTML(parser, permissionImpl);

            // 保存变量到到下一个标签用
            pageContext.setAttribute("__content", content, PageContext.APPLICATION_SCOPE);

            pageContext.setAttribute("__title", parser.getForm().pageTitle(), PageContext.APPLICATION_SCOPE);

            pageContext.include(template);


        } catch (ServletException ex) {
            Logger.getLogger(CompositionTag.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.io.IOException ex) {
            throw new JspException("Error in CompositionTagHandler tag", ex);
        } catch (Throwable ex) {
            Logger.getLogger(CompositionTag.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the bundleObject
     */
    public String getBundleObject() {
        return bundleObject;
    }

    /**
     * @param bundleObject the bundleObject to set
     */
    public void setBundleObject(String bundleObject) {
        this.bundleObject = bundleObject;
    }

    /**
     * @return the template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * @param template the template to set
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the permissionClass
     */
    public String getPermissionClass() {
        return permissionClass;
    }

    /**
     * @param permissionClass the permissionClass to set
     */
    public void setPermissionClass(String permissionClass) {
        this.permissionClass = permissionClass;
    }

    /**
     * @return the permission
     */
    public boolean isPermission() {
        return permission;
    }

    /**
     * @param permission the permission to set
     */
    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
