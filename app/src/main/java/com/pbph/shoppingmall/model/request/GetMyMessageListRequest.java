package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class GetMyMessageListRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;

    public Integer messageType;
    public Integer startRowNum;
    public Integer endRowNum;


    @Override
    public String getRequestPath() {
        return Constant.Path.GETMYMESSAGELIST;
    }
}
