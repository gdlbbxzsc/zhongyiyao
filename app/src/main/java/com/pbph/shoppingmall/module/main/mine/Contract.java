package com.pbph.shoppingmall.module.main.mine;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetMyCustomerResponse;
import com.pbph.shoppingmall.model.response.GetOrderNumberResponse;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {
        void changViewIsLogin();

        void signSucc();

        void signFail();

        void changeSignType(boolean type);

        void setMyCollectionAndBrowse(int browseRecordCount, int goodsCount, int storeCount);

        void setMyCustomer(GetMyCustomerResponse.DataBean dataBean);

        void setOrderNumber(GetOrderNumberResponse.DataBean bean);
    }

    interface Presenter extends IBasePresenter {
        void getMyCustomer();

        void getSysSwitch();

        void getMyCollectionAndBrowseRequest();

        void sign();

        void getOrderNumber();
    }

}
