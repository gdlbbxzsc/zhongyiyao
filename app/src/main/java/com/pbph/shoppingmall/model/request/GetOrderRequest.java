package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class GetOrderRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;

    public Integer startRowNum;
    public Integer endRowNum;
    public Integer orderType;


    @Override
    public String getRequestPath() {
        return Constant.Path.GETORDER;
    }
}
