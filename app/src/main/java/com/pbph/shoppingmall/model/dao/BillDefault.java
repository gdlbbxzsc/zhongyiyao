package com.pbph.shoppingmall.model.dao;

import com.pbph.mvp.base.model.BaseDaoModel;
import com.pbph.mvp.base.model.BaseResponesModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/3/7.
 */

@Entity
public class BillDefault extends BaseDaoModel {
    @Id(autoincrement = true)
    private Long id;//主键  自增长

    @NotNull   //不许为空
    private int billSerId;//服务器id

    @NotNull   //不许为空
    private String billTitle;

    @NotNull   //不许为空
    private String billType;

    private String billIdNum;

    @NotNull   //不许为空
    private boolean defBill;//是否默认

    @Generated(hash = 1186233703)
    public BillDefault(Long id, int billSerId, @NotNull String billTitle,
            @NotNull String billType, String billIdNum, boolean defBill) {
        this.id = id;
        this.billSerId = billSerId;
        this.billTitle = billTitle;
        this.billType = billType;
        this.billIdNum = billIdNum;
        this.defBill = defBill;
    }

    @Generated(hash = 2127588514)
    public BillDefault() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBillSerId() {
        return this.billSerId;
    }

    public void setBillSerId(int billSerId) {
        this.billSerId = billSerId;
    }

    public String getBillTitle() {
        return this.billTitle;
    }

    public void setBillTitle(String billTitle) {
        this.billTitle = billTitle;
    }

    public String getBillType() {
        return this.billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillIdNum() {
        return this.billIdNum;
    }

    public void setBillIdNum(String billIdNum) {
        this.billIdNum = billIdNum;
    }

    public boolean getDefBill() {
        return this.defBill;
    }

    public void setDefBill(boolean defBill) {
        this.defBill = defBill;
    }
 

}
