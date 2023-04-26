package com.pbph.shoppingmall.module.firmorder;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.custom.dialog.WaitUI;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.message.PayResultMsg;
import com.pbph.shoppingmall.model.request.GetDefaultAddressRequest;
import com.pbph.shoppingmall.model.request.GetSubmitPriceRequest;
import com.pbph.shoppingmall.model.request.PayMethodRequest;
import com.pbph.shoppingmall.model.request.SubmitOrderRequest;
import com.pbph.shoppingmall.model.response.GetDefaultAddressResponse;
import com.pbph.shoppingmall.model.response.GetSubmitPriceResponse;
import com.pbph.shoppingmall.model.response.PayMethodResponse;
import com.pbph.shoppingmall.model.response.SubmitOrderResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
//        payMethod();
        getDefaultAddress();
        subscribeRxBus2payResult();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
        unSubscribeRxBus2payResult();
    }


    @Override
    public void payMethod() {

        PayMethodRequest<PayMethodResponse> request = new PayMethodRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.mobile = MyApplication.userInfo.getMobile();
        request.request().subscribe(
                new LogoutFilteErrorConsumer<PayMethodResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(PayMethodResponse vo) throws Exception {
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getBaseView().toastShort("获取支付方式失败");
                    }
                }
        );
    }


    @Override
    public void getDefaultAddress() {

        WaitUI.Show(getBaseView().getContext());

        GetDefaultAddressRequest<GetDefaultAddressResponse> getDefaultAddressRequest = new GetDefaultAddressRequest<>();
        getDefaultAddressRequest.customerId = MyApplication.userInfo.getCustomerId();
        getDefaultAddressRequest.request().subscribe(
                new LogoutConsumer<GetDefaultAddressResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetDefaultAddressResponse vo) throws Exception {
                        WaitUI.Cancel();

                        getBaseView().setDefaultAddress(vo.getData());
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        WaitUI.Cancel();
                        getBaseView().toastShort("获取收货地址失败");
                    }
                }
        );
    }


    @Override
    public void submitOrder(SubmitOrderRequest<SubmitOrderResponse> submitOrderRequest) {

        submitOrderRequest.customerId = MyApplication.userInfo.getCustomerId();

        submitOrderRequest.request().subscribe(
                new LogoutFilteErrorConsumer<SubmitOrderResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(SubmitOrderResponse vo) throws Exception {
                        SubmitOrderResponse.DataBean data = vo.getData();
                        getBaseView().toPayment(data.getOrderCode(), data.getOrderType());


                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getBaseView().toastShort("提交订单失败");
                    }
                }
        );
    }


    @Override
    public void getSubmitPrice(int addressId, String shoppingCartIds, String goodsInfoIdNum) {
        GetSubmitPriceRequest<GetSubmitPriceResponse> request = new GetSubmitPriceRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.addressId = addressId;
        request.shoppingCartIds = shoppingCartIds;
        request.goodsInfoIdNum = goodsInfoIdNum;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetSubmitPriceResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetSubmitPriceResponse vo) throws Exception {
                        getBaseView().setSubmitPrice(vo.getData());
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getBaseView().toastShort("获取价格失败");
//                    onFail(nowTime);
                    }
                }
        );
    }


    @Override
    public void subscribeRxBus2payResult() {
        RxBusF.register0(Presenter.this, PayResultMsg.class, it -> {
            if (it.errCode == 0) {
//                respString = "支付成功";
                getBaseView().payResult(true);
            } else if (it.errCode == -1) {
//                respString = "支付错误";
            } else if (it.errCode == -2) {
//                respString = "取消支付";
            }
        });
    }

    @Override
    public void unSubscribeRxBus2payResult() {
        RxBusF.removeDisposable0(Presenter.this, PayResultMsg.class);
    }

}
