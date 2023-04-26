package com.pbph.shoppingmall.module.coupon.search;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.dao.CouponSearchRecord;
import com.pbph.shoppingmall.model.response.GetAllCouponListResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {
        void initSearchRecords(List<CouponSearchRecord> list);

        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void clearHttpDatas();

        void setHttpDatas(List<GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean> list);

        void finishSmartRefresh();

        void receiveCouponRequest(int id);

    }

    interface Presenter extends IBasePresenter {

        void doSearch(String str);

        void selectSearchRecords();

        void deleteSearchRecords();

        void getHttpDatasFirstPage();

        void getHttpDatasNextPage();

        void getHttpDatas(int page);

        void receiveCouponRequest(int id);
    }
}
