package com.pbph.shoppingmall.module.browserecords;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetBrowseRecordListResponse;

import java.util.Iterator;
import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void clearHttpDatas();

        void setHttpDatas(List<GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean> list);

        void finishSmartRefresh();

        List<GetBrowseRecordListResponse.DataBean.GoodsInfoBean.ListBean> getDelIds();

        void doDel();
    }

    interface Presenter extends IBasePresenter {

        void getHttpDatasFirstPage();

        void getHttpDatasNextPage();

        void getHttpDatas(int page);


        void doDel(String id);

        void addShoppingCar(int goodsInfoId, int goodsNum, int shopType, String clientType);
    }
}
