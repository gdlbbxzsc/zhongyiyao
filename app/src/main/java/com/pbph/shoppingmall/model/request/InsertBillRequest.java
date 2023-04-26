package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class InsertBillRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;

    //    本地自用
    public int id;

    public Integer billType;
    public String billTitle;
    public Integer defaultVal;
    public String billTelephone;
    public String billEmail;
    public String billParagraph;
    public String billUnit;
    public String billBankName;
    public String billBankAccount;

    @Override
    public String getRequestPath() {
        return Constant.Path.INSERTBILL;
    }
}
