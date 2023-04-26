package com.pbph.shoppingmall.module.search.shops;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {

        boolean isFragmentVisible();
    }

    interface Presenter extends IBasePresenter {
        //接收从父界面收到的 搜索文字
        void subscribeRxBus2getSearchText();

        void unSubscribeRxBus2getSearchText();
        //        将搜索文字转发到子界面上去
        void postRxBus4SendSeachText2Item();
    }
}
