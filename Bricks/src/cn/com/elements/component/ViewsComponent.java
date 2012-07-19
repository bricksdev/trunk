/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements.component;

import cn.com.annotations.parser.AnnotationParser;
import cn.com.exceptions.AppException;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.wapps.permission.Permission;

/**
 *
 * @author kete
 */
public interface ViewsComponent {

    String _SPLIT_DO = ".";

    void parser(ContainHTMLElementTag parentElement) throws AppException;

    public void setAnnotationParser(AnnotationParser parser);

    public void setTabIndex(TabIndex tabIndex);

    public void setPermission(Permission permission);
}
