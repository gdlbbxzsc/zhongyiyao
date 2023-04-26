package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class GetShoppingCartRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;
    public String mobile;

    public int shopType;
    public int startRowNum = 1;
    public int endRowNum = 10000;

    @Override
    public String getRequestPath() {
        return Constant.Path.GET_SHOPPING_CART;
    }
}