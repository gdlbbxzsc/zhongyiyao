package com.pbph.shoppingmall.module.shop.shopindex;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetStorePageFloorResponse;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {

        void setDefaultImage(String imageUrl);

        void setOneFloor(GetStorePageFloorResponse.DataBean.FloorMapBean.OneFloorBean oneFloor);

        void setTwoFloor(GetStorePageFloorResponse.DataBean.FloorMapBean.TwoFloorBean twoFloor);

        void setThreeFloor(GetStorePageFloorResponse.DataBean.FloorMapBean.ThreeFloorBean threeFloor);

        void setFourFloor(GetStorePageFloorResponse.DataBean.FloorMapBean.FourFloorBean fourFloor  );
    }

    interface Presenter extends IBasePresenter {
        void getGetStorePageFloor(int storeId);
    }
}
