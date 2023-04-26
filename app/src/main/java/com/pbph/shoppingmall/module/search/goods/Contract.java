package com.pbph.shoppingmall.module.search.goods;

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

        void postRxBus4flushListByPrice(boolean type);

        void postRxBus4showDrawer();

        //        将搜索文字转发到子界面上去
        //参数 true 代表 上级控件传来了 文字改变消息，我继续向下传递
//        false 代表 我也不知道文字改变没有，但是 本 fragment  是从 不可见 变为了可见，代表了滑动或者切换了选项卡。
//        我需要把 搜索文字传到下一个界面区，让有可能不知道的下级界面收到该文字。
        void postRxBus4SendSeachText2Item(boolean isSearchTextChanged);
    }
}
