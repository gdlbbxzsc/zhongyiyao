package com.pbph.shoppingmall.module.firmorder.invoice;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class InvoiceTypeBean {
    private String invoiceTypeName = "";
    private boolean isChecked = false;
    private int invoiceType = 0;//0 不开发票  1 普通发票

    public InvoiceTypeBean(String invoiceTypeName, boolean isChecked, int invoiceType) {
        this.invoiceTypeName = invoiceTypeName;
        this.isChecked = isChecked;
        this.invoiceType = invoiceType;
    }

    public int getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(int invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTypeName() {
        return invoiceTypeName;
    }

    public void setInvoiceTypeName(String invoiceTypeName) {
        this.invoiceTypeName = invoiceTypeName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
