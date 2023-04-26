package com.pbph.shoppingmall.module.orders.evaluateorders.submit;

import com.pbph.mvp.base.mvp.IBaseActivityView;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.request.CommentRequest;
import com.pbph.shoppingmall.model.response.GetCommentDetailResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个BaseFragmentView，为什么就不用我多说了吧。
    interface View extends IBaseActivityView {
        String getOrderId();

        void setCommentDetail(GetCommentDetailResponse.DataBean vo);

        void onSaveImg2AliOss(int id, List<String> urls);
        void onSaveImg2AliOssonFail();

        void onSubmit();
    }

    interface Presenter extends IBasePresenter {
        void getCommentDetail();

        void saveImg2AliOss(int id, List<String> urls);

        void submit(CommentRequest request);
    }
}
