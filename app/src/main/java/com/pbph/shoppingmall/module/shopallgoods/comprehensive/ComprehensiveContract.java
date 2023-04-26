package com.pbph.shoppingmall.module.shopallgoods.comprehensive;

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

        void setHttpData(List<SearchProductResponse.DataBeanX.GoodsInfoBean.GoodsInfoListBean.DataBean> strings);

        void httpError();
    }

    interface Presenter extends IBasePresenter {

        void getGoodsTypeListData(SearchProductRequest<SearchProductResponse> searchProductRequest);

    }
}
