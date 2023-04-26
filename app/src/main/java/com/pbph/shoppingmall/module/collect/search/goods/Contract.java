package com.pbph.shoppingmall.module.collect.search.goods;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetCustomerFollowListResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {
        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void clearHttpDatas();

        void setHttpDatas(List<GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean> list);

        void finishSmartRefresh();
    }

    interface Presenter extends IBasePresenter {

        void doSearch(String text);

        void getHttpDatasFirstPage();

        void getHttpDatasNextPage();

        void getHttpDatas(int page);
    }
}
