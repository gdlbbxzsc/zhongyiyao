package com.pbph.mvp.base.mvp;

public abstract class BasePresenter<T extends IBaseView> {

    private T baseView;

    public BasePresenter(T baseView) {
        this.baseView = baseView;
    }

    public T getBaseView() {
        return baseView;
    }
}