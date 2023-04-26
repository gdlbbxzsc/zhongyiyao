package com.pbph.shoppingmall.module.collect.goods;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.message.CollectGoodsTypeMsg;
import com.pbph.shoppingmall.model.request.GetGoodsCategoryListForFollowRequest;
import com.pbph.shoppingmall.model.response.GetGoodsCategoryListForFollowResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    CollectGoodsTypeMsg msg;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        msg = new CollectGoodsTypeMsg();
        getHttpDatas();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    @Override
    public void getHttpDatas() {


        GetGoodsCategoryListForFollowRequest<GetGoodsCategoryListForFollowResponse> request = new GetGoodsCategoryListForFollowRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetGoodsCategoryListForFollowResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetGoodsCategoryListForFollowResponse vo) throws Exception {

                        List<GetGoodsCategoryListForFollowResponse.DataBean.GoodsCategoryBean> list = vo.getData().getGoodsCategory();

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
