package com.pbph.shoppingmall.module.payment;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.PayMethodResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {
        void initPayMethod(List<PayMethodResponse.DataBean> payMethodBeans);

        void payResult(boolean payResult);


    }

    interface Presenter extends IBasePresenter {
        void payOrder(String orderCode, int orderType, int payId, int payType);

        void payMethod();

        void subscribeRxBus2payResult();

        void unSubscribeRxBus2payResult();

    }
}
