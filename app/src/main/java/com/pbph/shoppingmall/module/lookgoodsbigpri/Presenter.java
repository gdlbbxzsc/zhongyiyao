package com.pbph.shoppingmall.module.lookgoodsbigpri;


import com.pbph.mvp.base.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    private long lastTime = System.currentTimeMillis();

    private static final long DURATION_TIMES = 2500;


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        List<String> strings = new ArrayList<>();
        getBaseView().setHttpData(strings);
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }


    private long scaleTimes() {
        long nowTime = DURATION_TIMES - (System.currentTimeMillis() - lastTime);
        return nowTime < 0 ? 0 : nowTime;
    }

    private void onSucc(long nowTime) {

    }

}
