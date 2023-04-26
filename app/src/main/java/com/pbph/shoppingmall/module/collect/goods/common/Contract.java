package com.pbph.shoppingmall.module.collect.goods.common;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetCustomerFollowListResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {
        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void clearHttpDatas();

        void setHttpDatas(List<GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean> list);

        void finishSmartRefresh();

        String getid();

        void changeEditState(boolean isChecked);

        List<GetCustomerFollowListResponse.DataBean.GoodsInfoBean.ListBean> getDelIds();

        void doDel();

    }

    interface Presenter extends IBasePresenter {

        void getHttpDatasFirstPage();

        void getHttpDatasNextPage();

        void getHttpDatas(int page);

        void subscribeRxBus2changeEditState();

        void unSubscribeRxBus2changeEditState();

        void doDel(String id);
        void addShoppingCar(int goodsInfoId, int goodsNum, int shopType, String clientType);
    }
}
