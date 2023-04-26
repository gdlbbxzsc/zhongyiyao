package com.pbph.shoppingmall.module.orders.refundorders.submit;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.request.GetApplyCredentialsListRequest;
import com.pbph.shoppingmall.model.request.GetBackOrderReasonListRequest;
import com.pbph.shoppingmall.model.request.RefundRequest;
import com.pbph.shoppingmall.model.response.GetApplyCredentialsListResponse;
import com.pbph.shoppingmall.model.response.GetBackOrderReasonListResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;
import com.pbph.shoppingmall.utils.AliOss;

import java.util.List;


public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        getBackOrderReasonList();
        getApplyCredentialsList();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    @Override
    public void getBackOrderReasonList() {

        GetBackOrderReasonListRequest<GetBackOrderReasonListResponse> request = new GetBackOrderReasonListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.isBack = getBaseView().getType();
        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetBackOrderReasonListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetBackOrderReasonListResponse vo) throws Exception {
                        List<GetBackOrderReasonListResponse.DataBean.ResonListBean> list = vo.getData().getResonList();
                        getBaseView().setBackOrderReasonList(list);
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

    @Override
    public void getApplyCredentialsList() {

        GetApplyCredentialsListRequest<GetApplyCredentialsListResponse> request = new GetApplyCredentialsListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetApplyCredentialsListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetApplyCredentialsListResponse vo) throws Exception {
                        List<GetApplyCredentialsListResponse.DataBean.ApplyCredentialsListBean> list = vo.getData().getApplyCredentialsList();
                        getBaseView().setApplyCredentialsList(list);
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

    @Override
    public void refund(RefundRequest<ResultResponse> request) {
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().onRefund();
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("申请失败");
                    }
                }
        );
    }


    @Override
    public void saveImg2AliOss(String url) {

        AliOss.getInstance().uplodImage(getBaseView().getContext().getApplicationContext(), url, new AliOss.OnOosUploadListener() {
            @Override
            public void onSuccess(String path) {

                getBaseView().onSaveImg2AliOss(path);
            }

            @Override
            public void onFail() {
                getBaseView().toastShort("上传失败");
            }
        });
    }
}
