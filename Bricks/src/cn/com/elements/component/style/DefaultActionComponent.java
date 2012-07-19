/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements.component.style;

import cn.com.elements.component.actions.ActionElementComponent;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.exceptions.AppException;

/**
 *
 * @author xy
 */
public class DefaultActionComponent extends ActionElementComponent {

    private static final String _ACTION_DIV = "actionDiv";
    private static final String _ACTION_BUTTON = "actionButton";

    @Override
    public void parser(ContainHTMLElementTag parentElement) throws AppException {

        super.component(_ACTION_DIV, null, _ACTION_BUTTON, parentElement);
    }
}
