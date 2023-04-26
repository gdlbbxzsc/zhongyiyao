package com.pbph.shoppingmall.module.goodstype;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetStoreCategoryByStoreIdResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        void setHttpData(List<GetStoreCategoryByStoreIdResponse.DataBean> stringList);


    }

    interface Presenter extends IBasePresenter {

        void getHttpData(int storeId);
    }
}
