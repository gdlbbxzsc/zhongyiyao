package com.pbph.shoppingmall.module.collect.goods;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetGoodsCategoryListForFollowResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {
        void clearHttpDatas();

        void setHttpDatas(List<GetGoodsCategoryListForFollowResponse.DataBean.GoodsCategoryBean> list);
    }

    interface Presenter extends IBasePresenter {
        void getHttpDatas();

        void postRxBus4setTypeId(int type_id);
    }
}
