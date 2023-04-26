package com.pbph.shoppingmall.module.main.type.childtypelist;

import android.content.Context;

import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.model.request.GetClassifyRequest;
import com.pbph.shoppingmall.model.response.GetClassifyResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;

/**
 * Created by 连嘉凡 on 2018/3/5.
 */

public class ChildTypesPresenter<T extends ChildTypeContract.View> extends BasePresenter<T>
        implements ChildTypeContract.Presenter {
    Context context;

    public ChildTypesPresenter(T baseView, Context context) {
        super(baseView);
        this.context = context;
    }

    @Override
    public void subscribe() {

    }


    @Override
    public void unsubscribe() {

    }

    public void getHttpData(int mobCatebarId) {
        GetClassifyRequest<GetClassifyResponse> getClassifyRequest = new GetClassifyRequest<>();
        getClassifyRequest.mobCatebarId = mobCatebarId;
        getClassifyRequest.request().subscribe(
                new LogoutConsumer<GetClassifyResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetClassifyResponse vo) throws Exception {
                        getBaseView().setHttpData(vo.getData().getCateBarVos(),vo.getData()
                                .getCates());
                    }
                }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });


    }

}
