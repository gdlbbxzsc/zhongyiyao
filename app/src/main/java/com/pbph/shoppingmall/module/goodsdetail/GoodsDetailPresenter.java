package com.pbph.shoppingmall.module.goodsdetail;

import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.base.mvp.IBasePresenter;

/**
 * Created by 连嘉凡 on 2018/4/8.
 */

public class GoodsDetailPresenter<T extends GoodsDetailContract.View> extends BasePresenter<T>
        implements IBasePresenter {

    public GoodsDetailPresenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
