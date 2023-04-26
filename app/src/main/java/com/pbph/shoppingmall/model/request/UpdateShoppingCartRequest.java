package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class UpdateShoppingCartRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public String mobile;
    public Integer customerId;
    public int shoppingCartId;
    public int goodsInfoId;
    public int goodsNum;

    @Override
    public String getRequestPath() {
        return Constant.Path.UPDATE_SHOPPING_CART;
    }
}
