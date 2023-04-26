package com.pbph.shoppingmall.module.shopnewgoods;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.model.request.GetStoreNewGoodsForPageRequest;
import com.pbph.shoppingmall.model.response.GetStoreNewGoodsForPageResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getGetStoreNewGoodsForPage(int storeId, int startRowNum, int endRowNum) {
        GetStoreNewGoodsForPageRequest<GetStoreNewGoodsForPageResponse>
                getStoreNewGoodsForPageRequest = new GetStoreNewGoodsForPageRequest<>();
        getStoreNewGoodsForPageRequest.storeId = storeId;
        getStoreNewGoodsForPageRequest.startRowNum = startRowNum;
        getStoreNewGoodsForPageRequest.endRowNum = endRowNum;
        getStoreNewGoodsForPageRequest.request().subscribe(new LogoutConsumer<GetStoreNewGoodsForPageResponse>(getBaseView().getContext()) {
            @Override
            public void onDo(GetStoreNewGoodsForPageResponse vo) throws Exception {
                getBaseView().setGetStoreNewGoodsForPage(vo.getData().getList());

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
