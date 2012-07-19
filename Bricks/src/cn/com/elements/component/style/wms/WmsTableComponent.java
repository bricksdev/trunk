/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.elements.component.style.wms;

import cn.com.elements.containstag.div.DivElementTag;
import cn.com.elements.containstag.table.TableElementTag;
import cn.com.annotations.Grid;
import cn.com.elements.component.tables.TableElementComponent;
import cn.com.exceptions.AppException;
import cn.com.elements.containstag.ContainHTMLElementTag;

/**
 *
 * @author xy
 */
public class WmsTableComponent extends TableElementComponent {

    private static final String _TABLE_CONTAINER_CSS = "tableContainer";
    private static final String _TABLE_CONTAINER_DIV_CSS = "tableContainerDiv";
    private static final String _TABLE_CONTAINER_DIV_ID = "table_container_div";
    private static final String _TABLE_CONTAINER_ID = "table_container";

    @Override
    public void parser(ContainHTMLElementTag parentElement) throws AppException {
        Grid[] grids = super.getParser().getGroup().grids();
        // 追加容器标签
        DivElementTag containerDiv = new DivElementTag(_TABLE_CONTAINER_DIV_ID, _TABLE_CONTAINER_DIV_CSS);
        for (Grid g : grids) {

            TableElementTag tableContainer = new TableElementTag(_TABLE_CONTAINER_ID, _TABLE_CONTAINER_CSS);
            // 解析表格事件
            // TD跨列的数量
            int columnCount = parserTableEvents(g, tableContainer);
            // 解析表格内容
            parserTableTbody(columnCount, tableContainer, g);
            containerDiv.addChildrenTag(tableContainer);
        }
        parentElement.addChildrenTag(containerDiv);
    }
}
