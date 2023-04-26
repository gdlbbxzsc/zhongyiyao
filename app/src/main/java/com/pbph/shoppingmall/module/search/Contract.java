package com.pbph.shoppingmall.module.search;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.dao.SearchRecord;
import com.pbph.shoppingmall.model.message.FilterDatasResultMsg;
import com.pbph.shoppingmall.model.response.SearchProductResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {
        void initSearchRecords(List<SearchRecord> list);

        void changeView(MsgType type);

        void showDrawer();

        void setFilterDatas(List<SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean> params, List<SearchProductResponse.DataBeanX.GoodsInfoBean.BrandsBean> brands, boolean b);

    }

    interface Presenter extends IBasePresenter {

        void doSearch(String str);

        void selectSearchRecords();

        void deleteSearchRecords();

        void postRxBus4SendSeachText();

        void subscribeRxBus2showDrawer();

        void unSubscribeRxBus2showDrawer();

        void subscribeRxBus2setFilterDatas();

        void unSubscribeRxBus2setFilterDatas();

        void postRxBus4getFilterDatas(FilterDatasResultMsg msg);

    }
}
