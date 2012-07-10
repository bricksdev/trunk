/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.form.component;

import cn.com.annotations.Action;
import cn.com.tags.form.ContainHTMLElementTag;
import cn.com.tags.form.ElementComponent;
import cn.com.tags.form.containstag.DivElementTag;
import cn.com.tags.form.singletag.InputElementTag;
import cn.com.wapps.permission.Permission;

/**
 *
 * @author xy
 */
public class ActionComponent extends ElementComponent {

    private Permission permission = null;

    public ActionComponent(Permission permission) {
        this.permission = permission;
    }

    @Override
    public void parser(ContainHTMLElementTag parentElement) {
        Action[] actions = group.actions();
        if (actions != null && actions.length > 0) {
            DivElementTag actionDiv = new DivElementTag(group.name() + "ActionDiv", "actionDiv");

            for (Action a : actions) {

                // 判断当前操作是否为当前人所具有的权限
                if (permission != null && !permission.validate(parser.getBundleClassName(), a.id())) {
                    continue;
                }

                InputElementTag buttonElement = new InputElementTag();
                buttonElement.setId(a.id());
                buttonElement.setCssClass("actionButton");
                buttonElement.setType(a.submitType().name());
                buttonElement.setValue(a.label());
                buttonElement.setOnclick(a.onclick());
                buttonElement.setTabindex(String.valueOf(tabIndex.next()));

                actionDiv.addChildrenTag(buttonElement);
            }
            parentElement.addChildrenTag(actionDiv);

        }
    }
}
