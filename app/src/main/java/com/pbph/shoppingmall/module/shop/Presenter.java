package com.pbph.shoppingmall.module.shop;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.model.request.GetStoreDetailRequest;
import com.pbph.shoppingmall.model.response.GetStoreDetailResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情


    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

    }

    GetStoreDetailRequest<GetStoreDetailResponse> getStoreDetailRequest = new
            GetStoreDetailRequest<>();
    @Override
    public void getStoreDetail(int storeId) {

        getStoreDetailRequest.storeId = storeId;
        getStoreDetailRequest.request().subscribe(new LogoutConsumer<GetStoreDetailResponse>
                (getBaseView().getContext()) {
            @Override
            public void onDo(GetStoreDetailResponse vo) throws Exception {
                getBaseView().setHttpData(vo.getData());
            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {

            }
        });
    }
}
