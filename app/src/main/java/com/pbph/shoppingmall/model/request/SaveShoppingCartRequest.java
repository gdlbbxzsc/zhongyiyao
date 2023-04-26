package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by 连嘉凡 on 2018/5/9.
 */

public class SaveShoppingCartRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;
    public int goodsInfoId;
    public int goodsNum;
    public int shopType;
//    public String mobile;


    @Override
    public String getRequestPath() {
        return Constant.Path.SAVE_SHOPPING_CART;
    }
}
