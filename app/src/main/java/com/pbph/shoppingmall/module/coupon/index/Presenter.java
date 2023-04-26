package com.pbph.shoppingmall.module.coupon.index;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.request.GetCouponCategoryListRequest;
import com.pbph.shoppingmall.model.response.GetCouponCategoryListResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        getHttpDatas();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    @Override
    public void getHttpDatas() {

        GetCouponCategoryListRequest<GetCouponCategoryListResponse> request = new GetCouponCategoryListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetCouponCategoryListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetCouponCategoryListResponse vo) throws Exception {

                        List<GetCouponCategoryListResponse.DataBean.CouponCategoryListBean> list=vo.getData().getCouponCategoryList();
                        //向listview中加载数据
                        getBaseView().setHttpDatas(list);
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
