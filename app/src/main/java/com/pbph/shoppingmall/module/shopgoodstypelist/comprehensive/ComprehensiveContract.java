package com.pbph.shoppingmall.module.shopgoodstypelist.comprehensive;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.request.SearchProductRequest;
import com.pbph.shoppingmall.model.response.SearchProductResponse;

import java.util.List;

/**
 * Created by 连嘉凡 on 2018/3/7.
 */

public interface ComprehensiveContract {

    interface View extends IBaseFragmentViewV4 {


        void setHttpData(List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean
                .DataBean> strings);

        void setScreenData(String brands, String priceMin, String priceMax, String params);

        void httpError();

        void adapterViewHolderType(boolean b);
    }

    interface Presenter extends IBasePresenter {

        void getHttpData(String keyword, String sort, Integer storeId, Integer isThird, Integer
                startRowNum, Integer endRowNum, String catIds, String thirdCats, String brands,
                         String priceMin, String priceMax);


        void getGoodsTypeListData(SearchProductRequest<SearchProductResponse> searchProductRequest);

        //发送筛选条件到主界面的右侧滑选项卡上
        void postRxBus4setFilterDatas(boolean isClearData);

        void subscribeRxBus2getFilterDatas();

        void unSubscribeRxBus2getFilterDatas();

        void setRxBusAdapterViewHolderType();

        void removeDisposable0();
    }


}
