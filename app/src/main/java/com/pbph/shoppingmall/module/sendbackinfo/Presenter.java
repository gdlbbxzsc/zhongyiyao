package com.pbph.shoppingmall.module.sendbackinfo;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.request.AddLogisticsInfoRequest;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }


    @Override
    public void submit(String logisticsCompany, String logisticsNumber) {

        AddLogisticsInfoRequest<ResultResponse> request = new AddLogisticsInfoRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderId = getBaseView().getOrderId();

        request.logisticsCompany = logisticsCompany;
        request.logisticsNumber = logisticsNumber;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().onSubmit();
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("提交失败");
                    }
                }
        );
    }
}
