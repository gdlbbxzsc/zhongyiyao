package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by 连嘉凡 on 2018/5/18.
 */

public class GetCouponOfGoodsDetailRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;
    public int goodsInfoId;

    @Override
    public String getRequestPath() {
        return Constant.Path.GET_COUPON_Of_GOODS_DETAIL;
    }
}
