package com.pbph.shoppingmall.module.orders.evaluateorders.submit;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.custom.dialog.WaitUI;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.request.CommentRequest;
import com.pbph.shoppingmall.model.request.GetCommentDetailRequest;
import com.pbph.shoppingmall.model.response.GetCommentDetailResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;
import com.pbph.shoppingmall.utils.AliOss;

import java.util.ArrayList;
import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        getCommentDetail();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    @Override
    public void getCommentDetail() {
        GetCommentDetailRequest<GetCommentDetailResponse> request = new GetCommentDetailRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderId = getBaseView().getOrderId();
        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetCommentDetailResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetCommentDetailResponse vo) throws Exception {
                        GetCommentDetailResponse.DataBean bean = vo.getData();
                        getBaseView().setCommentDetail(bean);
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
    public void saveImg2AliOss(int id, List<String> urls) {
        WaitUI.Show(getBaseView().getContext());
        List<String> result = new ArrayList<>(urls.size());

        AliOss.getInstance().uplodImageList(getBaseView().getContext().getApplicationContext(), urls, new AliOss.OnOosUploadListener() {
            @Override
            public void onSuccess(String path) {
                result.add(path);
                if (result.size() < urls.size()) return;
                getBaseView().onSaveImg2AliOss(id, result);
                WaitUI.Cancel();
            }

            @Override
            public void onFail() {
                result.add("file_upload_2_ali_oss_fail");
                if (result.size() < urls.size()) return;
                getBaseView().onSaveImg2AliOssonFail();
                WaitUI.Cancel();
            }
        });
    }


    @Override
    public void submit(CommentRequest request) {
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderId = getBaseView().getOrderId();
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
