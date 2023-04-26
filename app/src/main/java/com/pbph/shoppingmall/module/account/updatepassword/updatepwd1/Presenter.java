package com.pbph.shoppingmall.module.account.updatepassword.updatepwd1;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.model.message.UpdatePwdMsg;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    UpdatePwdMsg msg = new UpdatePwdMsg();

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        msg.step = com.pbph.shoppingmall.module.account.updatepassword.MsgType.MsgType_Update_Pwd_Step1;
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }


    @Override
    public void submit2ValidateAccount(String account) {
        postRxBus4AddFragment();
    }

    @Override
    public void postRxBus4AddFragment() {
        RxBusF.post0(msg);
    }

}
