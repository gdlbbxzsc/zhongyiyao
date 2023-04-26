package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class SubmitOrderRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;

    public int addressId;//收货地址id
    public int billId;//发票id
    public String customerRemark;//留言
    public int billOpen;//是否开发票
    public String customerContent;//用户发票内容

    public int orderType;

    public String ids;//id（多个以逗号分割：1，2，3）
    public int goodsNum;//货品数量
 
    @Override
    public String getRequestPath() {
        return Constant.Path.SUBMIT_ORDER;
    }
}
