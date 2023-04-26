package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class GetSubmitPriceRequest<T extends BaseResponesModel> extends BaseRequest<T> {
    public Integer customerId;
    public String goodsInfoIdNum;
    public int addressId;
    public String shoppingCartIds;

    @Override
    public String getRequestPath() {
        return Constant.Path.GET_SUBMIT_PRICE;
    }
}
