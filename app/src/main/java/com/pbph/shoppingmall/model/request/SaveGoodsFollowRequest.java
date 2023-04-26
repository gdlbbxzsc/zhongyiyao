package com.pbph.shoppingmall.model.request;

import com.pbph.mvp.base.model.BaseResponesModel;
import com.pbph.shoppingmall.constant.Constant;

/**
 * Created by Administrator on 2018/3/7.
 */

public class SaveGoodsFollowRequest<T extends BaseResponesModel> extends BaseRequest<T> {

    public Integer customerId;
    public String productIds;

    public Integer allCollectionFlag;//高速服务器只收藏 不取消收藏。

    @Override
    public String getRequestPath() {
        return Constant.Path.SAVE_GOODS_FOLLOW;
    }
}
