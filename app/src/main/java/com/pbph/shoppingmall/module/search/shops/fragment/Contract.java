package com.pbph.shoppingmall.module.search.shops.fragment;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.SearchStoreResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {
        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void clearHttpDatas();

        void setHttpDatas(List<SearchStoreResponse.DataBeanX.StoreInfoBean.StoreInfoListBean.DataBean> list);

        void finishSmartRefresh();

        int getid();
    }

    interface Presenter extends IBasePresenter {
        void getHttpDatasFirstPage();

        void getHttpDatasNextPage();

        void getHttpDatas(int page);

        //接收从父界面收到的 搜索文字
        void subscribeRxBus2getSearchText2Item();

        void unSubscribeRxBus2getSearchText2Item();
    }
}
