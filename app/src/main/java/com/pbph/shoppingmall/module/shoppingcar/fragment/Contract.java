package com.pbph.shoppingmall.module.shoppingcar.fragment;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.ShoppingCarResponse;

import java.util.List;
import java.util.Map;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {
        void enableSmartRefresh(boolean finishRefresh, boolean finishLoadMore);

        void clearHttpDatas();

        void setHttpDatas(List<ShoppingCarResponse.DataBean.ListBean> list);

        void finishSmartRefresh();

        void onDelShoppingCart(Map<Integer, Boolean> temps);
    }

    interface Presenter extends IBasePresenter {
        void getHttpDatasFirstPage();

        void getHttpDatasNextPage();

        void getHttpDatas(int page);


        void updateShoppingCart(ShoppingCarResponse.DataBean.ListBean.ShoppingCartDTOListBean retailBean);

        void delShoppingCart(Map<Integer, Boolean> temps);

        void saveGoodsFollow(Map<Integer, Boolean> temps);
    }
}
