package com.pbph.shoppingmall.module.orders.search;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetOrderResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        int getTypeId();

        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void finishSmartRefresh();

        void clearHttpDatas();

        void setHttpDatas(List<GetOrderResponse.DataBean.ListBean> list);

        void onDelOrder(GetOrderResponse.DataBean.ListBean vo);

        void onCancelOrder(GetOrderResponse.DataBean.ListBean vo);

        void onConfirmOrder(GetOrderResponse.DataBean.ListBean vo);
    }

    interface Presenter extends IBasePresenter {

        void doSearch(String text);

        void getHttpDatasFirstPage();

        void getHttpDatasNextPage();

        void getHttpDatas(int page);

        void delOrder(GetOrderResponse.DataBean.ListBean bean);

        void cancelOrder(GetOrderResponse.DataBean.ListBean bean);

        void confirmOrder(GetOrderResponse.DataBean.ListBean bean);
    }
}
