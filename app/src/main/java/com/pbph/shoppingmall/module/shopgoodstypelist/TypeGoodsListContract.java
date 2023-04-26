package com.pbph.shoppingmall.module.shopgoodstypelist;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.message.FilterDatasResultMsg;
import com.pbph.shoppingmall.model.response.SearchProductResponse;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/6.
 */

public interface TypeGoodsListContract {
    interface View extends IBaseActivityView {
        void setFilterDatas(List<SearchProductResponse.DataBeanX.GoodsInfoBean.ParamsBean>
                                    params, List<SearchProductResponse.DataBeanX.GoodsInfoBean
                .BrandsBean> brands, boolean b);
    }

    interface Presenter extends IBasePresenter {

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
