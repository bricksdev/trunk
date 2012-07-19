/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.implement.wms;

import cn.com.exceptions.AppException;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.template.ContentTemplate;

/**
 *
 * @author kete
 */
public class WmsContentTemplate extends ContentTemplate {

    private static final String _VMI_CONTENT = "vmi_content";

    public WmsContentTemplate() {
    }

    @Override
    protected void parserComponents(ContainHTMLElementTag parentElement) throws AppException {

        super.parser(_VMI_CONTENT, parentElement);
    }
}
