package com.pbph.shoppingmall.module.search.shops;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.model.message.SearchText2ShopItemMsg;
import com.pbph.shoppingmall.model.message.SearchTextMsg;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    String searchText;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        subscribeRxBus2getSearchText();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
        unSubscribeRxBus2getSearchText();
    }


    @Override
    public void subscribeRxBus2getSearchText() {
        RxBusF.register0(Presenter.this, SearchTextMsg.class, it -> {
            searchText = it.searchText;
            postRxBus4SendSeachText2Item();
        });
    }

    @Override
    public void unSubscribeRxBus2getSearchText() {
        RxBusF.removeDisposable0(Presenter.this, SearchTextMsg.class);
    }

    SearchText2ShopItemMsg searchTextMsg = new SearchText2ShopItemMsg();

    @Override
    public void postRxBus4SendSeachText2Item() {
        searchTextMsg.searchText = searchText;
        RxBusF.post0(searchTextMsg);
    }

}
