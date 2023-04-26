package com.pbph.shoppingmall.module.firmorder.invoice;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class InvoiceContentBean {
    private String invoiceContentName = "";
    private boolean isChecked = false;

    public InvoiceContentBean(String invoiceContentName, boolean isChecked) {
        this.invoiceContentName = invoiceContentName;
        this.isChecked = isChecked;
    }

    public String getInvoiceContentName() {
        return invoiceContentName;
    }

    public void setInvoiceContentName(String invoiceContentName) {
        this.invoiceContentName = invoiceContentName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
