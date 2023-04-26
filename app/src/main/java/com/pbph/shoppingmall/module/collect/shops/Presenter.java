package com.pbph.shoppingmall.module.collect.shops;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.message.CollectShopTypeMsg;
import com.pbph.shoppingmall.model.request.GetStoreCategoryListForCollectionRequest;
import com.pbph.shoppingmall.model.response.GetStoreCategoryListForCollectionResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    CollectShopTypeMsg msg;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        msg = new CollectShopTypeMsg();
        getHttpDatas();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    @Override
    public void getHttpDatas() {

        GetStoreCategoryListForCollectionRequest<GetStoreCategoryListForCollectionResponse> request = new GetStoreCategoryListForCollectionRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetStoreCategoryListForCollectionResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetStoreCategoryListForCollectionResponse vo) throws Exception {

                        List<GetStoreCategoryListForCollectionResponse.DataBean.StoreCategoryBean> list = vo.getData().getStoreCategory();

                        //向listview中加载数据
                        getBaseView().setHttpDatas(list);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getBaseView().toastShort("获取失败");
                    }
                }
        );
    }

    @Override
    public void postRxBus4setTypeId(int type_id) {
        msg.type_id = type_id;
        RxBusF.post0(msg);
    }


}
