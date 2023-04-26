package com.pbph.shoppingmall.module.bill.submit;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.dao.BillDefault;
import com.pbph.shoppingmall.model.request.InsertBillRequest;
import com.pbph.shoppingmall.model.request.UpdateBillRequest;
import com.pbph.shoppingmall.model.response.GetBillByIdResponse;
import com.pbph.shoppingmall.model.response.GetBillListResponse;
import com.pbph.shoppingmall.model.response.InsertBillResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        int getId();

        void onSucc(GetBillListResponse.DataBean vo);

        void onGetHttpBillById(GetBillByIdResponse.DataBean vo);
    }

    interface Presenter extends IBasePresenter {

        void getHttpBillById(int serId);

        void submitHttpBill(InsertBillRequest<InsertBillResponse> request);

        void updateHttpBill(UpdateBillRequest<ResultResponse> request);

        void submitDaoDefaultBill(BillDefault request);

    }
}
