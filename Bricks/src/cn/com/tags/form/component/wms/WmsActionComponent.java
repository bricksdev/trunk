/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.tags.form.component.wms;

import cn.com.annotations.Action;
import cn.com.tags.form.ContainHTMLElementTag;
import cn.com.tags.form.ElementComponent;
import cn.com.tags.form.containstag.DivElementTag;
import cn.com.tags.form.singletag.InputElementTag;
import cn.com.wapps.permission.Permission;

/**
<div class="vmiAddG_next_button">
<div class="vmiAddG_b_button">
<div class="vmiAddG_b_but">
<input id="preview" class="vmi_btnmain_c" type="submit" value="提交" onclick="return checkinput();" name="preview">
</div>
<div class="vmiAddG_b_but">&nbsp;</div>
<div class="vmiAddG_b_but">
<input class="vmi_btnmain_c" type="button" onclick="location.href='PicklistByProductionOrder.jsp'" value="取消" name="input">
</div>
</div>
</div>
 * @author xy
 */
public class WmsActionComponent extends ElementComponent {

    private Permission permission = null;

    public WmsActionComponent(Permission permission) {
        this.permission = permission;
    }

    @Override
    public void parser(ContainHTMLElementTag parentElement) {

        DivElementTag buttonsDiv = new DivElementTag(group.name() + "ButtonsDiv", "vmiAddG_next_button");
        DivElementTag buttonDiv = new DivElementTag(group.name() + "ButtonsDiv", "vmiAddG_b_button");
        Action[] actions = group.actions();
        if (actions != null && actions.length > 0) {


            for (Action a : actions) {
                DivElementTag actionDiv = new DivElementTag(group.name() + "ActionDiv", "vmiAddG_b_but");
                // 判断当前操作是否为当前人所具有的权限
                if (permission != null && !permission.validate(parser.getBundleClassName(), a.id())) {
                    continue;
                }

                InputElementTag buttonElement = new InputElementTag();
                buttonElement.setId(a.id());
                buttonElement.setCssClass("vmi_btnmain_c");
                buttonElement.setType(a.submitType().name());
                buttonElement.setValue(a.label());
                buttonElement.setOnclick(a.onclick());
                buttonElement.setTabindex(String.valueOf(tabIndex.next()));

                actionDiv.addChildrenTag(buttonElement);
                buttonDiv.addChildrenTag(actionDiv);
            }
            buttonsDiv.addChildrenTag(buttonDiv);
            parentElement.addChildrenTag(buttonsDiv);

        }
    }
}
