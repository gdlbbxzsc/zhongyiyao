package com.pbph.shoppingmall.module.firmorder.invoice;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.request.GetDefaultBillRequest;
import com.pbph.shoppingmall.model.response.GetDefaultBillResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        getDefaultBill();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

    }


    @Override
    public void getDefaultBill() {
        GetDefaultBillRequest<GetDefaultBillResponse> getDefaultBillRequest = new GetDefaultBillRequest<>();
        getDefaultBillRequest.customerId = MyApplication.userInfo.getCustomerId();
        getDefaultBillRequest.request().subscribe(
                new LogoutConsumer<GetDefaultBillResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetDefaultBillResponse vo) throws Exception {
                        getBaseView().setDefaultBill(vo);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getBaseView().toastShort("获取默认发票失败");
                    }
                }
        );
    }
}
