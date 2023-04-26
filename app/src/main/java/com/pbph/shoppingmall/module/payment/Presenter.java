package com.pbph.shoppingmall.module.payment;


import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.android.utils.Logger;
import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.custom.dialog.WaitUI;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.message.CouponMineOperationMsg;
import com.pbph.shoppingmall.model.message.PayResultMsg;
import com.pbph.shoppingmall.model.request.PayMethodRequest;
import com.pbph.shoppingmall.model.request.PayOrderRequest;
import com.pbph.shoppingmall.model.response.PayMethodResponse;
import com.pbph.shoppingmall.model.response.PayOrderResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        payMethod();
        subscribeRxBus2payResult();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
        unSubscribeRxBus2payResult();
    }


    @Override
    public void payMethod() {
        PayMethodRequest<PayMethodResponse> payMethodRequest = new PayMethodRequest<>();

        payMethodRequest.customerId = MyApplication.userInfo.getCustomerId();
        payMethodRequest.mobile = MyApplication.userInfo.getMobile();
        payMethodRequest.request().subscribe(
                new LogoutConsumer<PayMethodResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(PayMethodResponse vo) throws Exception {
                        vo.getData().get(0).setChecked(true);//默认第一条选中
                        getBaseView().initPayMethod(vo.getData());
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getBaseView().toastShort("获取支付方式失败");
//                    onFail(nowTime);
                    }
                }
        );
    }


    @Override
    public void payOrder(String orderCode, int orderType, int payId, int payType) {

        WaitUI.Show(getBaseView().getContext());

        PayOrderRequest<PayOrderResponse> request = new PayOrderRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderCode = orderCode;
        request.orderType = orderType;
        request.payId = payId;
        request.payType = payType;
        request.request().subscribe(
                new LogoutConsumer<PayOrderResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(PayOrderResponse vo) throws Exception {
                        WaitUI.Cancel();
                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }
//                        类型（1：支付宝，2：微信）
                        switch (payType) {
                            case 1:
                                aliPay(vo.getData().getAlipay());
                                break;
                            case 2:
                                wxPay(vo.getData().getWechat());
                                break;
                        }

                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getBaseView().toastShort("支付失败");
                        WaitUI.Cancel();
                    }
                }
        );
    }


    void wxPay(PayOrderResponse.DataBean.WechatBean vo) {

        IWXAPI wx_api = WXAPIFactory.createWXAPI(getBaseView().getContext(), vo.getAppid(), false);
        wx_api.registerApp(vo.getAppid());

        PayReq req = new PayReq();
        req.appId = vo.getAppid();
        req.partnerId = vo.getPartnerid();
        req.prepayId = vo.getPrepayid();
        req.packageValue = vo.getPackageStr();
        req.nonceStr = vo.getNoncestr();
        req.timeStamp = vo.getTimestamp();
        req.sign = vo.getSign();

        wx_api.sendReq(req);
    }

    private void aliPay(PayOrderResponse.DataBean.AlipayBean data) {
        Observable
                .create((ObservableOnSubscribe<String>) e -> {
                    PayTask alipay = new PayTask(getBaseView().getActivity());
                    Map<String, String> result = alipay.payV2(data.getSgin(), true);
                    Logger.e(result.toString());
                    e.onNext(result.get("resultStatus"));
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((payResult) -> {
                            if (!TextUtils.equals(payResult, "9000")) {
                                PayResultMsg msg = new PayResultMsg();
                                msg.errCode = -1;
                                RxBusF.getInstance().post(msg);
                                return;
                            }
                            PayResultMsg msg = new PayResultMsg();
                            msg.errCode = 0;
                            RxBusF.getInstance().post(msg);
                        }, (throwable) -> {
                            PayResultMsg msg = new PayResultMsg();
                            msg.errCode = -1;
                            RxBusF.getInstance().post(msg);
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
                getBaseView().payResult(false);
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
