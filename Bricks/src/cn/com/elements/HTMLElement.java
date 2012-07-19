/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements;

import cn.com.elements.containstag.div.DivElementTag;
import cn.com.elements.containstag.div.SpanElementTag;
import cn.com.elements.containstag.form.FormElementTag;
import cn.com.elements.containstag.label.LabelElementTag;
import cn.com.elements.containstag.select.SelectElementTag;
import cn.com.elements.containstag.table.TableElementTag;
import cn.com.elements.containstag.table.TableTbodyElementTag;
import cn.com.elements.containstag.table.TableTdElementTag;
import cn.com.elements.containstag.table.TableThElementTag;
import cn.com.elements.containstag.table.TableTheadElementTag;
import cn.com.elements.containstag.table.TableTrElementTag;
import cn.com.elements.containstag.ul.LIElementTag;
import cn.com.elements.containstag.ul.ULElementTag;
import cn.com.elements.singletag.input.InputElementTag;
import cn.com.elements.singletag.link.LinkElementTag;
import cn.com.elements.singletag.options.OptionElementTag;

/**
 *
 * @author kete
 */
@Deprecated
public enum HTMLElement {

    DIV(DivElementTag.class),
    SPAN(SpanElementTag.class),
    FORM(FormElementTag.class),
    LABEL(LabelElementTag.class),
    SELECT(SelectElementTag.class),
    TABLE(TableElementTag.class),
    TBODY(TableTbodyElementTag.class),
    TD(TableTdElementTag.class),
    TH(TableThElementTag.class),
    THEAD(TableTheadElementTag.class),
    TR(TableTrElementTag.class),
    UL(ULElementTag.class),
    LI(LIElementTag.class),
    INPUT(InputElementTag.class),
    LINK(LinkElementTag.class),
    OPTION(OptionElementTag.class);
    private Class<? extends AbstractHtmlElementTag> _element = null;

    private HTMLElement(Class<? extends AbstractHtmlElementTag> e) {
        this._element = e;
    }

    public Class<? extends AbstractHtmlElementTag> getElement() {
        return _element;
    }
}
