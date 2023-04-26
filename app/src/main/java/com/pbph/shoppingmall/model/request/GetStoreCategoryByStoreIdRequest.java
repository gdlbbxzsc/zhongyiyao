package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by 连嘉凡 on 2018/5/30.
 */

public class GetStoreCategoryByStoreIdRequest<T extends BaseResponesModel> extends BaseRequest<T> {


    public int storeId;
    @Override
    public String getRequestPath() {
        return Constant.Path.GET_STORE_CATEGORY_BY_STORE_ID;
    }
}