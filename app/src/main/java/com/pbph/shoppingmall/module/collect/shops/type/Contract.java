package com.pbph.shoppingmall.module.collect.shops.type;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetCollectionSellerListResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {
        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void clearHttpDatas();

        void setHttpDatas(List<GetCollectionSellerListResponse.DataBean.CollectionSellerListBean.ListBean> list);

        void finishSmartRefresh();

        void changeEditState(boolean isChecked);

    }

    interface Presenter extends IBasePresenter {
        void getHttpDatasFirstPage();

        void getHttpDatasNextPage();

        void getHttpDatas(int page);

        void subscribeRxBus2changeEditState();

        void unSubscribeRxBus2changeEditState();


        void subscribeRxBus2setTypeId();

        void unSubscribeRxBus2setTypeId();


    }
}
