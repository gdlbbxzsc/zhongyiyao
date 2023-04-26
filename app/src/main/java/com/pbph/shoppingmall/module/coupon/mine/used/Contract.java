package com.pbph.shoppingmall.module.coupon.mine.used;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.SelectMyCouponListResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {
        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void clearHttpDatas();

        void setHttpDatas(List<SelectMyCouponListResponse.DataBean.CouponListBean.ListBean> list);

        void finishSmartRefresh();

        void delData(boolean isDel);

        void chooseAll(boolean isChoose);

        List<SelectMyCouponListResponse.DataBean.CouponListBean.ListBean> getDelIds();

        void doDel(List<SelectMyCouponListResponse.DataBean.CouponListBean.ListBean> list);
    }

    interface Presenter extends IBasePresenter {
        void getHttpDatasFirstPage();

        void getHttpDatasNextPage();

        void getHttpDatas(boolean first);

        void getHttpDatas(int page);


        void subscribeRxBus2delData();

        void unSubscribeRxBus2delData();

        void doDel();
    }
}
