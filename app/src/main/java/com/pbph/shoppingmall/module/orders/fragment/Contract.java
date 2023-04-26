package com.pbph.shoppingmall.module.orders.fragment;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetOrderResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {

        int getTypeId();

        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void clearHttpDatas();

        void setHttpDatas(List<GetOrderResponse.DataBean.ListBean> list);

        void finishSmartRefresh();

        void onBuyAgain(GetOrderResponse.DataBean.ListBean vo);

        void onDelOrder(GetOrderResponse.DataBean.ListBean vo);

        void onCancelOrder(GetOrderResponse.DataBean.ListBean vo);

        void onConfirmOrder(GetOrderResponse.DataBean.ListBean vo);
    }

    interface Presenter extends IBasePresenter {


        void getHttpDatasFirstPage();

        void getHttpDatasNextPage();

        void getHttpDatas(int page);

        void delOrder(GetOrderResponse.DataBean.ListBean bean);

        void cancelOrder(GetOrderResponse.DataBean.ListBean bean);

        void confirmOrder(GetOrderResponse.DataBean.ListBean bean);

        void buyAgain(GetOrderResponse.DataBean.ListBean bean);


    }
}
