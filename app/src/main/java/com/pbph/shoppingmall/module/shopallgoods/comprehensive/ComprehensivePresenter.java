package com.pbph.shoppingmall.module.shopallgoods.comprehensive;

import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.model.request.SearchProductRequest;
import com.pbph.shoppingmall.model.response.SearchProductResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

/**
 * Created by 连嘉凡 on 2018/3/7.
 */

public class ComprehensivePresenter<T extends ComprehensiveContract.View> extends
        BasePresenter<T> implements ComprehensiveContract.Presenter {


    public ComprehensivePresenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {

    }


    @Override
    public void getGoodsTypeListData(SearchProductRequest<SearchProductResponse> searchProductRequest) {

        searchProductRequest.request().subscribe(new LogoutConsumer<SearchProductResponse>(getBaseView().getContext()) {
            @Override
            public void onDo(SearchProductResponse vo) throws Exception {
            getBaseView().setHttpData(vo.getData().getGoodsInfo().getGoodsInfoList().getData());
            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                getBaseView().httpError();
            }
        });
    }
}
