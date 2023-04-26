package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by 连嘉凡 on 2018/5/21.
 */

public class GetMarketingOfGoodsDetailRequest<T extends BaseResponesModel> extends BaseRequest<T> {
    public int goodsInfoId;
    @Override
    public String getRequestPath() {
        return Constant.Path.GET_MARKETING_OF_GOODS_DETAIL;
    }
}