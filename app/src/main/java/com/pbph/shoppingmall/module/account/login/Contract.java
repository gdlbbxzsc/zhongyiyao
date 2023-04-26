package com.pbph.shoppingmall.module.account.login;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        void resetNumcode();

        void waitNumCode();

        void goMain();
    }

    interface Presenter extends IBasePresenter {

        void getNumCode(String acc);

        void login(String acc, String code);

    }
}
