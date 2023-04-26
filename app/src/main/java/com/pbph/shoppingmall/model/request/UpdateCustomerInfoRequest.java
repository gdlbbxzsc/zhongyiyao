package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class UpdateCustomerInfoRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;

    public Integer sex;//（0保密1男2女）
    public String customerBirthday;

    @Override
    public String getRequestPath() {
        return Constant.Path.UPDATECUSTOMERINFO;
    }
}
