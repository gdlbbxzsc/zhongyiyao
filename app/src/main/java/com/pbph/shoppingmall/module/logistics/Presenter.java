package com.pbph.shoppingmall.module.logistics;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.request.GetLogisticsInformationRequest;
import com.pbph.shoppingmall.model.response.GetLogisticsInformationResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        //摸人家在第一页
        getHttpDatas();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

    }


    @Override
    public void getHttpDatas() {

        GetLogisticsInformationRequest<GetLogisticsInformationResponse> request = new GetLogisticsInformationRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderId = getBaseView().getOrderId();
        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetLogisticsInformationResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetLogisticsInformationResponse vo) throws Exception {
                        List<GetLogisticsInformationResponse.DataBean.LogisticsListBean> list = vo.getData().getLogisticsList();
                        getBaseView().setHttpDatas(list, vo.getData().getOrderCode());
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("获取失败");
                    }
                }
        );
    }

}
