package com.pbph.shoppingmall.module.goodsdetail.goodsdetailsdetail.detailspecifications;


import com.android.utils.Logger;
import com.pbph.mvp.base.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        getHttpData();

    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

    }


    @Override
    public void getHttpData() {//获取网络数据
        Logger.e(getBaseView().getShopId());// 通过view 获取拼接参数
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);

        }
        getBaseView().setHttpData(list);
    }
}
