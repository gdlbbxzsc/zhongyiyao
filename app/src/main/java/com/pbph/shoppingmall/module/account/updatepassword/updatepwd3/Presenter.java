package com.pbph.shoppingmall.module.account.updatepassword.updatepwd3;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.shoppingmall.model.message.UpdatePwdMsg;
import com.pbph.shoppingmall.module.account.updatepassword.MsgType;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    UpdatePwdMsg msg = new UpdatePwdMsg();

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        msg.step = MsgType.MsgType_Update_Pwd_Step3;
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }


    @Override
    public void submitPwd() {
        postRxBus4AddFragment();
    }

    @Override
    public void postRxBus4AddFragment() {
        RxBusF.post0(msg);
    }

}
