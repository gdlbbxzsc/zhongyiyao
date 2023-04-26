package com.pbph.shoppingmall.module.main;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.message.Jump2ShopTypeMsg;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {
        void goShopType();

        void setShoppingCartNumber(int num);
    }

    interface Presenter extends IBasePresenter {

        void subscribeRxBus2goShopType();

        void unSubscribeRxBus2goShopType();

        void getShoppingCartNumber();

        void getMyCustomer();
    }
}
