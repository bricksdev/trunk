/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byd.wms.receipt;

import cn.com.annotations.Action;
import cn.com.annotations.Element;
import cn.com.annotations.Form;
import cn.com.annotations.Grid;
import cn.com.annotations.Group;
import cn.com.annotations.Groups;
import cn.com.annotations.enums.ElementTagType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kete
 */
@Groups(groups =
@Group(name="inputAsn",actions={@Action(id="confirm",label="提交",onclick="return checkInputRequired();")},form =
@Form(action = "ReceiptOperat.jsp", id = "asnReceipt", label = "页面收货"), elements =
        {@Element(id = "asnNo", label = "送货单号", required = true)}
       )

        )
@Form(action = "ReceiptOperat.jsp", id = "asnReceipt", label = "页面收货")
public class Asn {

    @Element(id = "asnNo", label = "送货单号", readonly = true)
    private String asnNo = null;
    @Element(id = "asnStatus", label = "送货状态", readonly = true)
    private String asnStatus = null;
    @Element(id = "vendor", label = "供应商", readonly = true)
    private String vendor = null;
    @Element(id = "vendorName", label = "供应商名称", readonly = true)
    private String vendorName = null;
    @Element(id = "warehouseCode", label = "仓库号")
    private String warehouseCode = null;
    @Element(id = "headerText", label = "抬头文本")
    private String headerText = null;
    @Element(id = "pzhDate", label = "凭证日期",type= ElementTagType.DATE, formate="yyyy-MM-dd")
    private Date pzhDate = null;
    @Element(id = "transactionDate", label = "过帐日期",type= ElementTagType.DATE, formate="yyyy-MM-dd")
    private Date transactionDate = null;
    @Element(id = "receiptNo", label = "提货单号")
    private String receiptNo = null;
    @Element(id = "deliveryNo", label = "交货单号")
    private String deliveryNo = null;
    @Element(id = "itemText", label = "行项目文本")
    private String itemText = null;
    @Grid(id = "items", contextClass = AsnItem.class, columns = {
        @Element(label = "订单号", id = "poNo"),
        @Element(label = "物料号", id = "materialNo"),
        @Element(label = "物料描述", id = "materialDesc"),
        @Element(label = "送货单数量", id = "quantity"),
        @Element(label = "操作数量", id = "operatQty", required = true),
        @Element(label = "已收数量", id = "receiptedQty"),
        @Element(label = "单位", id = "unit"),
        @Element(label = "行项目文本", id = "itemText")
    })
    private List<AsnItem> items = null;

    /**
     * @return the asnNo
     */
    public String getAsnNo() {
        return asnNo;
    }

    /**
     * @param asnNo the asnNo to set
     */
    public void setAsnNo(String asnNo) {
        this.asnNo = asnNo;
    }

    /**
     * @return the asnStatus
     */
    public String getAsnStatus() {
        return asnStatus;
    }

    /**
     * @param asnStatus the asnStatus to set
     */
    public void setAsnStatus(String asnStatus) {
        this.asnStatus = asnStatus;
    }

    /**
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * @return the vendorName
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * @param vendorName the vendorName to set
     */
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * @return the warehouseCode
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * @param warehouseCode the warehouseCode to set
     */
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    /**
     * @return the headerText
     */
    public String getHeaderText() {
        return headerText;
    }

    /**
     * @param headerText the headerText to set
     */
    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    /**
     * @return the pzhDate
     */
    public Date getPzhDate() {
        return pzhDate;
    }

    /**
     * @param pzhDate the pzhDate to set
     */
    public void setPzhDate(Date pzhDate) {
        this.pzhDate = pzhDate;
    }

    /**
     * @return the transactionDate
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * @return the receiptNo
     */
    public String getReceiptNo() {
        return receiptNo;
    }

    /**
     * @param receiptNo the receiptNo to set
     */
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    /**
     * @return the deliveryNo
     */
    public String getDeliveryNo() {
        return deliveryNo;
    }

    /**
     * @param deliveryNo the deliveryNo to set
     */
    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    /**
     * @return the itemText
     */
    public String getItemText() {
        return itemText;
    }

    /**
     * @param itemText the itemText to set
     */
    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    /**
     * @return the items
     */
    public List<AsnItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<AsnItem> items) {
        this.items = items;
    }
    @Action(id="save",label="提交")
    public void save(){}
    @Action(id="cancel",label="取消")
    public void cancel(){}
}
