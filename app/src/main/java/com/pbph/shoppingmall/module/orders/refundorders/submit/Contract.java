package com.pbph.shoppingmall.module.orders.refundorders.submit;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.request.RefundRequest;
import com.pbph.shoppingmall.model.response.GetApplyCredentialsListResponse;
import com.pbph.shoppingmall.model.response.GetBackOrderReasonListResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {
        int getType();

        void setBackOrderReasonList(List<GetBackOrderReasonListResponse.DataBean.ResonListBean> list);

        void setApplyCredentialsList(List<GetApplyCredentialsListResponse.DataBean.ApplyCredentialsListBean> list);

        void onSaveImg2AliOss(String url);

        void onRefund();
    }

    interface Presenter extends IBasePresenter {

        void getBackOrderReasonList();

        void getApplyCredentialsList();

        void refund(RefundRequest<ResultResponse> request);

        void saveImg2AliOss(String url);
    }
}
