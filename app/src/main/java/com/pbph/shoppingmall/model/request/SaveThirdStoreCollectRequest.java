package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by 连嘉凡 on 2018/5/14.
 */

public class SaveThirdStoreCollectRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;
    public String storeIds;

    @Override
    public String getRequestPath() {
        return Constant.Path.SAVE_THIRD_STORE_COLLECT;
    }
}
