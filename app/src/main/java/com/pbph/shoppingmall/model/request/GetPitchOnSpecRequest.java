package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by 连嘉凡 on 2018/6/20.
 */

public class GetPitchOnSpecRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public int goodsId;
    public String specIdArr;
    public String specValueIdArr;

    @Override
    public String getRequestPath() {
        return Constant.Path.GET_PITCH_ON_SPEC;
    }
}