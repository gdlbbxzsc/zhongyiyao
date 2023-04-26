package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class RefundRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;

    public String orderId;
    public Integer isBack;
    public Integer isCollect;

    public String backReason;
    public String applyCredentials;
    
    public String uploadDocuments;
    public String backPerson;
    public String backPhone;
    public String questionRemark;


    @Override
    public String getRequestPath() {
        return Constant.Path.REFUND;
    }
}
