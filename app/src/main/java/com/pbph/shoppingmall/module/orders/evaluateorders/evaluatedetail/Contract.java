package com.pbph.shoppingmall.module.orders.evaluateorders.evaluatedetail;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        void setHttpData(List<String> strings);

    }

    interface Presenter extends IBasePresenter {

    }
}
