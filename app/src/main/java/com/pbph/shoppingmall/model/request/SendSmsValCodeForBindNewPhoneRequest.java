package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class SendSmsValCodeForBindNewPhoneRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;

    public String mobile;//（手机号）
    public String code;//（验证码）

    @Override
    public String getRequestPath() {
        return Constant.Path.SENDSMSVALCODEFORBINDNEWPHONE;
    }
}
