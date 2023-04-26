package com.pbph.shoppingmall.module.collect;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.model.message.CollectEditMsg;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    CollectEditMsg msg;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        msg = new CollectEditMsg();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }


    @Override
    public void postRxBus4changeEditState(boolean isCheck) {
        msg.isCheck = isCheck;
        RxBusF.post0(msg);
    }
}
