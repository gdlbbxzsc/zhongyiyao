package com.pbph.shoppingmall.module.orders.fragment;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseConsumer;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.constant.Constant;
import com.pbph.shoppingmall.model.request.CancelOrderRequest;
import com.pbph.shoppingmall.model.request.ConfirmOrderRequest;
import com.pbph.shoppingmall.model.request.DelOrderRequest;
import com.pbph.shoppingmall.model.request.GetOrderRequest;
import com.pbph.shoppingmall.model.request.OrderBuyAgainRequest;
import com.pbph.shoppingmall.model.response.GetOrderResponse;
import com.pbph.shoppingmall.model.response.OrderStatusResponse;
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
        GetOrderRequest<GetOrderResponse> request = new GetOrderRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.startRowNum = page;
        request.endRowNum = Constant.Data.PAGE_COUNT;
//        订单类型（0：全部，1：待付款，2：待收货，3：已完成，4：已取消，5：待评价，6：退还售后）
        request.orderType = getBaseView().getTypeId();
        request.request().subscribe(
                new LogoutConsumer<GetOrderResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetOrderResponse vo) throws Exception {

                        getBaseView().finishSmartRefresh();

                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            return;
                        }

                        //如果 等于零 代表第一页 要清除数据
                        if (page == page_num && page_num == 1) {
                            getBaseView().clearHttpDatas();
                        }
                        List<GetOrderResponse.DataBean.ListBean> list = vo.getData().getList();

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
                        getBaseView().finishSmartRefresh();       getBaseView().toastShort("获取失败");
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
    public void delOrder(GetOrderResponse.DataBean.ListBean bean) {
        DelOrderRequest<ResultResponse> request = new DelOrderRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderId = bean.getPpid();
        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().onDelOrder(bean);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("删除失败");
                    }
                }
        );
    }


    @Override
    public void cancelOrder(GetOrderResponse.DataBean.ListBean bean) {

        CancelOrderRequest<OrderStatusResponse> request = new CancelOrderRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderId = bean.getPpid();
        request.request().subscribe(
                new LogoutFilteErrorConsumer<OrderStatusResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(OrderStatusResponse vo) throws Exception {
                        bean.setOrderStatus(vo.getData().getOrderStatus());
                        getBaseView().onCancelOrder(bean);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("取消失败");
                    }
                }
        );
    }

    @Override
    public void confirmOrder(GetOrderResponse.DataBean.ListBean bean) {


        ConfirmOrderRequest<OrderStatusResponse> request = new ConfirmOrderRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderId = bean.getPpid();
        request.request().subscribe(
                new LogoutFilteErrorConsumer<OrderStatusResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(OrderStatusResponse vo) throws Exception {
                        bean.setOrderStatus(vo.getData().getOrderStatus());
                        getBaseView().onConfirmOrder(bean);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("确认失败");
                    }
                }
        );
    }

    @Override
    public void buyAgain(GetOrderResponse.DataBean.ListBean bean) {

        OrderBuyAgainRequest<ResultResponse> request = new OrderBuyAgainRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderCode = bean.getOrderCode();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().onBuyAgain(bean);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("再次购买失败");
                    }
                }
        );
    }
}
