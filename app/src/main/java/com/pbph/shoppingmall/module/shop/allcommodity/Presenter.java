package com.pbph.shoppingmall.module.shop.allcommodity;


import com.pbph.mvp.base.mvp.BasePresenter;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {
    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        //摸人家在第一页

    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

    }

}
