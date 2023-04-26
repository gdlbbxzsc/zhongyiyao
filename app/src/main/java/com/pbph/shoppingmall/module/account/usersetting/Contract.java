package com.pbph.shoppingmall.module.account.usersetting;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.request.UpdateCustomerInfoRequest;
import com.pbph.shoppingmall.model.request.UpdateCustomerRequest;
import com.pbph.shoppingmall.model.response.GetMyCustomerResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.utils.AliOss;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        void setMyCustomer(GetMyCustomerResponse.DataBean dataBean);

        void onLogout();
    }

    interface Presenter extends IBasePresenter {
        void getMyCustomer();

        void updateCustomerRequest(UpdateCustomerRequest<ResultResponse> request);

        void updateCustomerInfoRequest(UpdateCustomerInfoRequest<ResultResponse> request);

        void logout();

        void saveImg2AliOss(String url);
    }


}
