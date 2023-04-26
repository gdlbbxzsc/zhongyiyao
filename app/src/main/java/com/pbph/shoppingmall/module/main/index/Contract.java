package com.pbph.shoppingmall.module.main.index;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetAppDefaultTemplateResponse;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {

        void gotoScan();

        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void clearHttpDatas();

        void setHttpDatas(GetAppDefaultTemplateResponse.DataBean dataBean);

        void finishSmartRefresh();
    }

    interface Presenter extends IBasePresenter {
        void checkPermission2gotoScan();

        void getHttpDatas();

        void postRxBus4goShopType();
    }
}
