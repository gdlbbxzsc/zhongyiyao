package com.pbph.shoppingmall.module.account.updateaccount.updateact1;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {
        void resetNumcode();

        void waitNumCode();
    }

    interface Presenter extends IBasePresenter {
        void getNumCode();

        void submitNumCode(String code);

        void postRxBus4AddFragment();
    }
}
