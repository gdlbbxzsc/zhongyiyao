package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by 连嘉凡 on 2018/5/31.
 */

public class GetStoreNewGoodsForPageRequest<T extends BaseResponesModel> extends BaseRequest<T> {
    public int storeId;
    public int startRowNum;
    public int endRowNum;



    @Override
    public String getRequestPath() {
        return Constant.Path.GET_STORE_NEW_GOODS_FOR_PAGE;
    }
}