package com.pbph.shoppingmall.module.shopallgoods;


import com.pbph.mvp.base.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    int page_num = 1;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        //摸人家在第一页
        getHttpDatas(page_num = 1);
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

    }


    @Override
    public void getHttpDatas(int page) {
        //如果 等于零 代表第一页 要清除数据
        if (page == page_num && page_num == 1) {
            getBaseView().clearHttpDatas();
        }
//假装请求网络数据
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
//向listview中加载数据
        getBaseView().setHttpDatas(list);
//加一页
        page_num++;
    }
}
