/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements.component.actions;

import cn.com.annotations.Action;
import cn.com.annotations.Element;
import cn.com.elements.component.ElementComponent;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.elements.containstag.div.DivElementTag;
import cn.com.elements.singletag.input.InputElementTag;
import cn.com.exceptions.AppException;
import cn.com.utils.StringUtil;

/**
 *
 * @author kete
 */
public abstract class ActionElementComponent extends ElementComponent {

    private static final String _ACTION_DIV = "ActionDiv";
    private static final String _BUTTON_DIV = "buttonDiv";

    protected void component(String divCss, String actionCss, String buttionCss, ContainHTMLElementTag parentElement) throws AppException {
        Action[] actions = super.getParser().getGroup().form().actions();
        if (actions != null && actions.length > 0) {
            DivElementTag actionDiv = new DivElementTag(super.getParser().getGroup().name() + _ACTION_DIV, divCss);

            for (Action a : actions) {
                DivElementTag inputDiv = new DivElementTag(a.id() + _BUTTON_DIV, actionCss);
                // 判断当前操作是否为当前人所具有的权限
                if (super.getPermission() != null && !super.getPermission().validate(super.getParser().getBundleClassName(), a.id())) {
                    continue;
                }

                InputElementTag buttonElement = new InputElementTag();
                buttonElement.setId(a.id());
                buttonElement.setCssClass(buttionCss);
                buttonElement.setType(a.submitType().name());
                buttonElement.setValue(Element.LabelContent.getLabelContent(StringUtil.isEmpty(a.label()) ? a.id().toUpperCase() : a.label()));
                buttonElement.setOnclick(a.onclick());
                buttonElement.setTabindex(String.valueOf(super.getTabIndex().next()));
                inputDiv.addChildrenTag(buttonElement);

                actionDiv.addChildrenTag(inputDiv);
            }
            parentElement.addChildrenTag(actionDiv);

        }
    }

    @Override
    protected void settingLinkHref(StringBuilder href, String parameter, Object e) throws AppException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
