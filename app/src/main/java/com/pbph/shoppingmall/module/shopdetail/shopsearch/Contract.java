package com.pbph.shoppingmall.module.shopdetail.shopsearch;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.dao.SearchRecord;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.module.search.MsgType;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {

        void initSearchRecords(List<SearchRecord> list);

        void changeView(MsgType type);

         void setHttpData(List<SearchProductResponse.DataBeanX.GoodsInfoBean
                .GoodsInfoListBean.DataBean> strings);


        void httpError();

    }

    interface Presenter extends IBasePresenter {
        /**
         *
         * @param str
         * @param storeId
         * @param pageNo
         */
        void doSearch(String str,int storeId,int pageNo);

        void  selectSearchRecords();

        void deleteSearchRecords();
    }
}
