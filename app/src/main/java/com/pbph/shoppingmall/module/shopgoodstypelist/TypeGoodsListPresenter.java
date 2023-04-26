package com.pbph.shoppingmall.module.shopgoodstypelist;

import com.android.utils.Logger;
import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.model.message.FilterDatasMsg;
import com.pbph.shoppingmall.model.message.FilterDatasResultMsg;

/**
 * Created by 连嘉凡 on 2018/3/6.
 */

public class TypeGoodsListPresenter<T extends TypeGoodsListContract.View> extends BasePresenter<T>
        implements TypeGoodsListContract.Presenter {


    public TypeGoodsListPresenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {
        subscribeRxBus2showDrawer();
        selectSearchRecords();
        subscribeRxBus2setFilterDatas();
    }

    @Override
    public void unsubscribe() {

    }


    @Override
    public void selectSearchRecords() {

    }

    @Override
    public void deleteSearchRecords() {

    }

    @Override
    public void postRxBus4SendSeachText() {

    }

    @Override
    public void subscribeRxBus2showDrawer() {

    }

    @Override
    public void unSubscribeRxBus2showDrawer() {

    }

    @Override
    public void subscribeRxBus2setFilterDatas() {
        RxBusF.register0(this, FilterDatasMsg.class, it -> {
            getBaseView().setFilterDatas(it.params, it.brands, it.isClearData);
        });
    }

    @Override
    public void unSubscribeRxBus2setFilterDatas() {

    }

    @Override
    public void postRxBus4getFilterDatas(FilterDatasResultMsg msg) {
        Logger.e("=====>"+msg.brand);
        RxBusF.post0(msg);
    }
}
