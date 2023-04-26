package com.pbph.shoppingmall.module.addressmng.list;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetAddressListResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        void clearHttpDatas();

        void setHttpDatas(List<GetAddressListResponse.DataBean> list);

        void flushDefaultAddress(int id);

        void flushDatas4Del(int id);
    }

    interface Presenter extends IBasePresenter {
        void getHttpDatasFirstPage();

        void getHttpDatas(int page);

        void submitHttpDefaultAddress(GetAddressListResponse.DataBean addressRequest);

        void submitDaoDefaultAddress(GetAddressListResponse.DataBean addressRequest);


        void delHttpAddress(int id);

        void delDaoDefaultAddress(int id);
    }
}
