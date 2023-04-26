package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class GetStoreStreetRequest<T extends BaseResponesModel> extends BaseRequest<T> {


    public int startRowNum;
    public int endRowNum;

    public int type;

    @Override
    public String getRequestPath() {
        return Constant.Path.GETSTORESTREET;
    }
}
