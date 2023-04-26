package com.pbph.shoppingmall.module.goodstype;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.model.request.GetStoreCategoryByStoreIdRequest;
import com.pbph.shoppingmall.model.response.GetStoreCategoryByStoreIdResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract
        .Presenter {

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情


    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }


    @Override
    public void getHttpData(int storeId) {
        GetStoreCategoryByStoreIdRequest<GetStoreCategoryByStoreIdResponse>
                getStoreCategoryByStoreIdRequest = new GetStoreCategoryByStoreIdRequest<>();

        getStoreCategoryByStoreIdRequest.storeId = storeId;
        getStoreCategoryByStoreIdRequest.request().subscribe(new LogoutConsumer<GetStoreCategoryByStoreIdResponse>(getBaseView().getContext()) {

            @Override
            public void onDo(GetStoreCategoryByStoreIdResponse vo) throws Exception {
              List<GetStoreCategoryByStoreIdResponse.DataBean> dataBeans= vo.getData();
              getBaseView().setHttpData(dataBeans);
            }

        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {

            }
        });
    }
}
