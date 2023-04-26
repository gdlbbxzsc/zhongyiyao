package com.pbph.shoppingmall.module.message.type.interaction;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {
        void clearHttpDatas();

        void setHttpDatas(List<String> list);
    }

    interface Presenter extends IBasePresenter {
        void getHttpDatas(int page);
    }
}
