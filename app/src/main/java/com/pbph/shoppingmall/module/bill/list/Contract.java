package com.pbph.shoppingmall.module.bill.list;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetBillListResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        void clearHttpDatas();

        void setHttpDatas(List<GetBillListResponse.DataBean> list);

        void flushDefaultBill(int defId);

        void flushDatas4Del(int delId);
    }

    interface Presenter extends IBasePresenter {
        void getHttpDatasFirstPage();

        void getHttpDatas(int page);

        void submitHttpDefaultBill(GetBillListResponse.DataBean dataBean);

        void submitDaoDefaultBill(GetBillListResponse.DataBean vo);

        void delHttpBill(int id);

        void delDaoDefaultBill(int id);
    }
}
