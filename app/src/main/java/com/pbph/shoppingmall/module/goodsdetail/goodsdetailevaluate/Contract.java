package com.pbph.shoppingmall.module.goodsdetail.goodsdetailevaluate;

import com.pbph.mvp.base.mvp.IBaseFragmentViewV4;
import com.pbph.mvp.base.mvp.IBasePresenter;
import com.pbph.shoppingmall.model.response.GetProductCommentResponse;

import java.util.List;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface Contract {

    //看清楚哦 这里继承的是 IBaseActivityView ，还有一个 IBaseFragmentViewV4，为什么就不用我多说了吧。
    interface View extends IBaseFragmentViewV4 {

        void setGoodsCommentCount(GetProductCommentResponse.DataBean.GoodsCommentCountBean goodsCommentCountBean);

        void setEvaluateData(List<GetProductCommentResponse.DataBean.CommentPageBeanBean.ListBean> list);
    }

    interface Presenter extends IBasePresenter {
        void getHttpData(int startRowNum, int endRowNum, int goodsId, int type, String title);

    }
}
