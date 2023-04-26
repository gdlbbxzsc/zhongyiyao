package com.pbph.shoppingmall.module.welcome;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        void onGetPerms();//@AfterPermissionGranted

        void goLogin();

        void goMain();

    }

    interface Presenter extends IBasePresenter {
        void checkPermission2onGetPerms();//@AfterPermissionGranted

        void login();

    }
}
