package com.pbph.shoppingmall.module.shop.shopindex;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.model.request.GetStorePageFloorRequest;
import com.pbph.shoppingmall.model.response.GetStorePageFloorResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

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
    public void getGetStorePageFloor(int storeId) {
        GetStorePageFloorRequest<GetStorePageFloorResponse> getStorePageFloorRequest = new
                GetStorePageFloorRequest<>();
        getStorePageFloorRequest.storeId = storeId;
        getStorePageFloorRequest.request().subscribe(new LogoutConsumer<GetStorePageFloorResponse>(getBaseView().getContext()) {
            @Override
            public void onDo(GetStorePageFloorResponse vo) throws Exception {
                GetStorePageFloorResponse.DataBean.FloorMapBean floorMapBean = vo.getData()
                        .getFloorMap();

                if (vo.getData().getFloorMap()==null){
                    getBaseView().setDefaultImage(vo.getData().getDefaultImage());
                    return;
                }

                getBaseView().setOneFloor(floorMapBean.getOneFloor());
                getBaseView().setTwoFloor(floorMapBean.getTwoFloor());
                getBaseView().setThreeFloor(floorMapBean.getThreeFloor());
                getBaseView().setFourFloor(floorMapBean.getFourFloor());
            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {

            }
        });

    }
}
