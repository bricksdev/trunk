/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byd.wms.receipt;

import java.math.BigDecimal;

/**
 *
 * @author kete
 */
public class AsnItem {

    private String poNo;
    private String materialNo;
    private String materialDesc;
    private BigDecimal quantity;
    private BigDecimal operatQty;
    private BigDecimal receiptedQty;
    private String unit;
    private String itemText;

    /**
     * @return the poNo
     */
    public String getPoNo() {
        return poNo;
    }

    /**
     * @param poNo the poNo to set
     */
    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    /**
     * @return the materialNo
     */
    public String getMaterialNo() {
        return materialNo;
    }

    /**
     * @param materialNo the materialNo to set
     */
    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    /**
     * @return the materialDesc
     */
    public String getMaterialDesc() {
        return materialDesc;
    }

    /**
     * @param materialDesc the materialDesc to set
     */
    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    /**
     * @return the quantity
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the operatQty
     */
    public BigDecimal getOperatQty() {
        return operatQty;
    }

    /**
     * @param operatQty the operatQty to set
     */
    public void setOperatQty(BigDecimal operatQty) {
        this.operatQty = operatQty;
    }

    /**
     * @return the receiptedQty
     */
    public BigDecimal getReceiptedQty() {
        return receiptedQty;
    }

    /**
     * @param receiptedQty the receiptedQty to set
     */
    public void setReceiptedQty(BigDecimal receiptedQty) {
        this.receiptedQty = receiptedQty;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
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
}
