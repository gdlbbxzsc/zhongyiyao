package com.pbph.shoppingmall.module.addressmng.submit;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.dao.AddressDefault;
import com.pbph.shoppingmall.model.request.SaveAddressRequest;
import com.pbph.shoppingmall.model.request.UpdateAddressRequest;
import com.pbph.shoppingmall.model.response.GetAddressListResponse;
import com.pbph.shoppingmall.model.response.GetAddressResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.model.response.SaveAddressResponse;
import com.pbph.shoppingmall.model.response.SelectSysAddressListResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        int getId();

        void onSucc(GetAddressListResponse.DataBean vo);

        void onGetHttpAddressById(GetAddressResponse.DataBean vo);

        void setSysAddressList(List<SelectSysAddressListResponse.DataBean.ProvinceListBean> litst);
    }

    interface Presenter extends IBasePresenter {

        void selectSysAddressList();

        void getHttpAddressById(int serId);

        void submitHttpAddress(SaveAddressRequest<SaveAddressResponse> addressRequest);

        void updateHttpAddress(UpdateAddressRequest<ResultResponse> addressRequest);

        void submitDaoDefaultAddress(AddressDefault addressRequest);

    }
}
