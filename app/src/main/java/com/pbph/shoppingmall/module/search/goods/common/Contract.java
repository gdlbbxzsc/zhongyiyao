package com.pbph.shoppingmall.module.search.goods.common;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.SearchProductResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {

        int getid();

        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void clearHttpDatas();

        void finishSmartRefresh();

        void setHttpDatas(List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean> list);

        boolean isVisibleFragMent();
    }

    interface Presenter extends IBasePresenter {
        void getHttpDatasFirstPage();

        void getHttpDatasNextPage();

        void getHttpDatas(int page);

        //接收从父界面收到的 搜索文字
        void subscribeRxBus2getSearchText2Item();

        void unSubscribeRxBus2getSearchText2Item();

        //发送筛选条件到主界面的右侧滑选项卡上
        void postRxBus4setFilterDatas(boolean isClearData);

        void subscribeRxBus2getFilterDatas();

        void unSubscribeRxBus2getFilterDatas();
    }
}
