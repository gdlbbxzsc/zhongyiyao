package com.pbph.shoppingmall.module.salespromotion;


import com.pbph.mvp.base.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        getHttpDatas();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    @Override
    public void getHttpDatas() {

        //假装请求网络数据
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        //向listview中加载数据
        getBaseView().setHttpDatas(list);
    }
}
