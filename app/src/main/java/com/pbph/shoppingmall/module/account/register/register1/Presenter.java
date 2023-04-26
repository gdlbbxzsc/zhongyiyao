package com.pbph.shoppingmall.module.account.register.register1;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.model.message.RegisterMsg;
import com.pbph.shoppingmall.module.account.register.MsgType;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    RegisterMsg msg = new RegisterMsg();

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        msg.step = MsgType.MsgType_Register_Step1;
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    @Override
    public void getImgCode() {
        getBaseView().showImgCode("");
    }

    @Override
    public void submitAccountImgCode() {
        postRxBus4AddFragment();
    }

    @Override
    public void postRxBus4AddFragment() {
        RxBusF.post0(msg);
    }

}
