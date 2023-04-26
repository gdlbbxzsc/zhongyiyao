package com.pbph.shoppingmall.module.coupon.index.fragment;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.request.GetAllCouponListRequest;
import com.pbph.shoppingmall.model.request.ReceiveCouponRequest;
import com.pbph.shoppingmall.model.response.GetAllCouponListResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

import java.util.List;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    int page_num = 1;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        //摸人家在第一页
        getHttpDatas(page_num = 1);
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    @Override
    public void getHttpDatas(int page) {


        if (page != page_num) {
            return;
        }
        if (page == -1) {
            getBaseView().enableSmartRefresh(true, false);
            return;
        }
        if (page > 1) {
            getBaseView().enableSmartRefresh(true, true);
        }


        GetAllCouponListRequest<GetAllCouponListResponse> request = new GetAllCouponListRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.categoryId = String.valueOf(getBaseView().getid());
        request.startRowNum = page;
        request.endRowNum = Constant.Data.PAGE_COUNT;

        request.request().subscribe(
                new LogoutConsumer<GetAllCouponListResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetAllCouponListResponse vo) throws Exception {

                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }

                        //如果 等于零 代表第一页 要清除数据
                        if (page == page_num && page_num == 1) {
                            getBaseView().clearHttpDatas();
                        }
                        List<GetAllCouponListResponse.DataBean.AllCouponListBean.ListBean> list = vo.getData().getAllCouponList().getList();

                        if (list == null || list.isEmpty() || list.size() < Constant.Data.PAGE_COUNT) {
                            page_num = -1;
                            getBaseView().enableSmartRefresh(true, false);
                        } else {
                            //加一页
                            page_num++;
                            getBaseView().enableSmartRefresh(true, true);
                        }
                        ////向listview中加载数据
                        getBaseView().setHttpDatas(list);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().finishSmartRefresh();
                        getBaseView().toastShort("获取失败");
                    }
                }
        );
    }

    @Override
    public void getHttpDatasFirstPage() {
        getHttpDatas(page_num = 1);
    }

    @Override
    public void getHttpDatasNextPage() {
        getHttpDatas(page_num);
    }

    @Override
    public void receiveCouponRequest(int id) {

        ReceiveCouponRequest<ResultResponse> request = new ReceiveCouponRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.couponActivityId = id;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().receiveCouponRequest(id);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("领取失败");
                    }
                }
        );
    }
}
