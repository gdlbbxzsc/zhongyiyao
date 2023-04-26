package com.pbph.shoppingmall.module.account.updateaccount.updateact1;


import android.text.TextUtils;

import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.custom.dialog.WaitUI;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.message.UpdateActMsg;
import com.pbph.shoppingmall.model.request.CheckCodeRequest;
import com.pbph.shoppingmall.model.request.SendSmsValCodeRequest;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.module.account.updateaccount.MsgType;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.pbph.shoppingmall.utils.AMUtils;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    UpdateActMsg msg = new UpdateActMsg();

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        msg.step = MsgType.MsgType_Update_Act_Step1;
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    @Override
    public void getNumCode() {

        String acc = MyApplication.userInfo.getMobile();

        if (!validateAccount(acc)) return;

        SendSmsValCodeRequest<ResultResponse> sendSmsValCodeRequest = new SendSmsValCodeRequest<>();
        sendSmsValCodeRequest.mobile = acc;

        sendSmsValCodeRequest.request().subscribe(
                new LogoutConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            getBaseView().resetNumcode();
                            return;
                        }
                        getBaseView().waitNumCode();
                    }
                }
                //失败
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("发送验证码失败");
                        getBaseView().resetNumcode();
                    }
                }
        );
    }

    @Override
    public void submitNumCode(String code) {

        String acc = MyApplication.userInfo.getMobile();

        if (!validateAccount(acc)) return;
        if (!validateCode(code)) return;

        WaitUI.Show(getBaseView().getContext());

        CheckCodeRequest<ResultResponse> moblieLoginRequest = new CheckCodeRequest<>();
        moblieLoginRequest.customerId = MyApplication.userInfo.getCustomerId();
        moblieLoginRequest.mobile = acc;
        moblieLoginRequest.code = code;

        moblieLoginRequest.request().subscribe(
                new LogoutConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        WaitUI.Cancel();
                        postRxBus4AddFragment();
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        WaitUI.Cancel();
                        getBaseView().toastShort("验证失败");
                    }
                }
        );


    }

    @Override
    public void postRxBus4AddFragment() {
        RxBusF.post0(msg);
    }


    boolean validateAccount(String call) {
        if (TextUtils.isEmpty(call)) {
            getBaseView().toastShort(R.string.phone_number_is_null);
            return false;
        }
        if (!AMUtils.isMobile(call)) {
            getBaseView().toastShort(R.string.Illegal_phone_number);
            return false;
        }
        return true;
    }

    boolean validateCode(String code) {
        if (TextUtils.isEmpty(code)) {
            getBaseView().toastShort("请输入验证码");
            return false;
        }
        if (!AMUtils.isNumCode(code)) {
            getBaseView().toastShort("验证码输入不正确");
            return false;
        }
        return true;
    }
}
