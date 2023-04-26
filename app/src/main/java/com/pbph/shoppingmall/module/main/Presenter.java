package com.pbph.shoppingmall.module.main;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.message.Jump2ShopTypeMsg;
import com.pbph.shoppingmall.model.request.GetMyCustomerRequest;
import com.pbph.shoppingmall.model.request.GetShoppingCartNumberRequest;
import com.pbph.shoppingmall.model.response.GetMyCustomerResponse;
import com.pbph.shoppingmall.model.response.GetShoppingCartNumberResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        subscribeRxBus2goShopType();
        getMyCustomer();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
        unSubscribeRxBus2goShopType();
    }


    @Override
    public void getShoppingCartNumber() {
        GetShoppingCartNumberRequest<GetShoppingCartNumberResponse> getShoppingCartNumberRequest = new GetShoppingCartNumberRequest<>();

        getShoppingCartNumberRequest.customerId = MyApplication.userInfo.getCustomerId();
        getShoppingCartNumberRequest.shopType = 1;
        getShoppingCartNumberRequest.request().subscribe(
                new LogoutConsumer<GetShoppingCartNumberResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetShoppingCartNumberResponse vo) throws Exception {
//                        getBaseView().toastShort("" + vo);
                        getBaseView().setShoppingCartNumber(vo.getData().getGoodsNum());
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }
        );
    }

    @Override
    public void subscribeRxBus2goShopType() {
        RxBusF.register0(Presenter.this, Jump2ShopTypeMsg.class, it -> getBaseView().goShopType());
    }

    @Override
    public void unSubscribeRxBus2goShopType() {
        RxBusF.removeDisposable0(Presenter.this, Jump2ShopTypeMsg.class);
    }


    @Override
    public void getMyCustomer() {
        if (MyApplication.userInfo.getCustomerId() == null || MyApplication.userInfo.getCustomerId() == -1)
            return;


        GetMyCustomerRequest<GetMyCustomerResponse> request = new GetMyCustomerRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new BaseConsumer<GetMyCustomerResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetMyCustomerResponse vo) throws Exception {

                        if (vo.getCode() != 200) return;
                        
                        GetMyCustomerResponse.DataBean dataBean = vo.getData();
                        MyApplication.userInfo.setUserInfoFromGetMyCustomer(dataBean);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                    }
                }
        );
    }
}
