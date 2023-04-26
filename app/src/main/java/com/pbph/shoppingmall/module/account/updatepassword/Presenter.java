package com.pbph.shoppingmall.module.account.updatepassword;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.model.message.UpdatePwdMsg;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        subscribeRxBus2addFragment();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
        unSubscribeRxBus2addFragment();
    }


    @Override
    public void subscribeRxBus2addFragment() {
        RxBusF.register0(Presenter.this, UpdatePwdMsg.class, it -> getBaseView().addFragment(it));
    }

    @Override
    public void unSubscribeRxBus2addFragment() {
        RxBusF.removeDisposable0(Presenter.this, UpdatePwdMsg.class);
    }
}
