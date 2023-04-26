package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class MoblieLoginRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public String code;
    public String mobile;


    @Override
    public String getRequestPath() {
        return Constant.Path.MOBLIELOGIN;
    }
}
