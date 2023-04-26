package com.pbph.shoppingmall.module.firmorder;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.request.SubmitOrderRequest;
import com.pbph.shoppingmall.model.response.GetDefaultAddressResponse;
import com.pbph.shoppingmall.model.response.GetSubmitPriceResponse;
import com.pbph.shoppingmall.model.response.SubmitOrderResponse;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {
        void setDefaultAddress(GetDefaultAddressResponse.DataBean bean);

        void toPayment(String orderCode, int orderType);

        void setSubmitPrice(GetSubmitPriceResponse.DataBean vo);

        void payResult(boolean payResult);
    }

    interface Presenter extends IBasePresenter {

        void payMethod();

        void getDefaultAddress();

        void getSubmitPrice(int addressId, String shoppingCartIds, String goodsInfoIdNum);

        void submitOrder(SubmitOrderRequest<SubmitOrderResponse> submitOrderRequest);

        void subscribeRxBus2payResult();

        void unSubscribeRxBus2payResult();
    }
}
