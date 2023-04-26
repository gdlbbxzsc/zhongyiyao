package com.pbph.shoppingmall.module.orders.myorders.detail;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetOrderResponse;
import com.pbph.shoppingmall.model.response.OrderInfoResponse;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        int getOrderId();

        void setOrderInfo(OrderInfoResponse.DataBean data);

        void onDelOrder(int id);

        void onBuyAgain();

    }

    interface Presenter extends IBasePresenter {

        void getOrderInfo();

        void delOrder(int id);

        void cancelOrder(int id);

        void confirmOrder(int id);

        void saveShoppingCart(int goodsInfoId, int goodsNum, int shopType);

        void buyAgain(String code);

    }
}
