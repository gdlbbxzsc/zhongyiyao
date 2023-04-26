package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by 连嘉凡 on 2018/5/7.
 */

public class GetClassifyRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer mobCatebarId = null;

    @Override
    public String getRequestPath() {
        return Constant.Path.GET_CLASSIFY;
    }
}
