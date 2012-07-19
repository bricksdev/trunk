/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements.component.style.wms;

import cn.com.elements.component.actions.ActionElementComponent;
import cn.com.elements.containstag.ContainHTMLElementTag;
import cn.com.elements.containstag.div.DivElementTag;
import cn.com.exceptions.AppException;

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
public class WmsActionComponent extends ActionElementComponent {

    private static final String _BUTTONS_DIV = "ButtonsDiv";
    private static final String _VMIADDG_B_BUT = "vmiAddG_b_but";
    private static final String _VMIADDG_B_BUTTON = "vmiAddG_b_button";
    private static final String _VMIADDG_NEXT_BUTTON = "vmiAddG_next_button";
    private static final String _VMI_BTNMAIN_C = "vmi_btnmain_c";

    @Override
    public void parser(ContainHTMLElementTag parentElement) throws AppException {

        DivElementTag buttonsDiv = new DivElementTag(super.getParser().getGroup().name() + _BUTTONS_DIV, _VMIADDG_NEXT_BUTTON);
        super.component(_VMIADDG_B_BUTTON, _VMIADDG_B_BUT, _VMI_BTNMAIN_C, buttonsDiv);
        parentElement.addChildrenTag(buttonsDiv);

    }
}
