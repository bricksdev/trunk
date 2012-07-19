/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.implement;

import cn.com.exceptions.AppException;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.template.ContentTemplate;

/**
 *
 * @author kete
 */
public class DefaultContentTemplate extends ContentTemplate {

    private static final String _FORM_CONTENT = "form_content";

    @Override
    protected void parserComponents(ContainHTMLElementTag parentElement) throws AppException {
        super.parser(_FORM_CONTENT, parentElement);
    }
}
