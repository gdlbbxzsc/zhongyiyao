package com.pbph.shoppingmall.module.main.type;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.custom.dialog.WaitUI;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.model.request.GetClassifyRequest;
import com.pbph.shoppingmall.model.response.GetClassifyResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    public Presenter(T baseView) {
        super(baseView);

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() { //销毁时执行的事情

    }

    @Override
    public void getHttpData() {
        GetClassifyRequest<GetClassifyResponse> getClassifyRequest = new GetClassifyRequest<>();


        WaitUI.Show(getBaseView().getContext());
        getClassifyRequest.request().subscribe(
                new LogoutConsumer<GetClassifyResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetClassifyResponse vo) throws Exception {
                        WaitUI.Cancel();
                        getBaseView().setHttpData(vo.getData().getCates());
                    }
                }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        WaitUI.Cancel();
                        throwable.printStackTrace();
                    }
                });
    }
}
