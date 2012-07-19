package cn.com.elements.containstag.table;

import cn.com.elements.TagWriter;
import cn.com.elements.containstag.ContainHTMLElementTag;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class TableElementTag extends ContainHTMLElementTag {

    private static final String _DEFAULT_ZERO = "0";
    private static final String _TAGNAME = "table";
    private static final String _CELL_SAPCING = "cellspacing";
    private static final String _CELL_PADDING = "cellpadding";
    private String cellspacing = _DEFAULT_ZERO;
    private String cellpadding = _DEFAULT_ZERO;

    public TableElementTag() {
    }

    public TableElementTag(String id, String cssClass) {
        super.setId(id);
        super.setCssClass(cssClass);
    }

    public TableElementTag(String id, String cssClass, String cssStyle) {
        this(id, cssClass);
        super.setCssStyle(cssStyle);
    }

    @Override
    public void doTag(TagWriter tagWriter) throws JspException, IOException {
        tagWriter.startTag(_TAGNAME);
        // 属性写入
        this.writeOptionalAttributes(tagWriter);
        super.writeOptionalAttribute(tagWriter, _CELL_PADDING, cellpadding);
        super.writeOptionalAttribute(tagWriter, _CELL_SAPCING, cellspacing);
        super.doChildrenTag(tagWriter);
        tagWriter.endTag(true);
    }

    /**
     * @return the cellspacing
     */
    public String getCellspacing() {
        return cellspacing;
    }

    /**
     * @param cellspacing the cellspacing to set
     */
    public void setCellspacing(String cellspacing) {
        this.cellspacing = cellspacing;
    }

    /**
     * @return the cellpadding
     */
    public String getCellpadding() {
        return cellpadding;
    }

    /**
     * @param cellpadding the cellpadding to set
     */
    public void setCellpadding(String cellpadding) {
        this.cellpadding = cellpadding;
    }
}
