package com.pbph.shoppingmall.module.orders.myorders.detail;


import android.widget.Toast;

import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.UserInfo;
import com.pbph.shoppingmall.model.request.CancelOrderRequest;
import com.pbph.shoppingmall.model.request.ConfirmOrderRequest;
import com.pbph.shoppingmall.model.request.DelOrderRequest;
import com.pbph.shoppingmall.model.request.OrderBuyAgainRequest;
import com.pbph.shoppingmall.model.request.OrderInfoRequest;
import com.pbph.shoppingmall.model.request.SaveShoppingCartRequest;
import com.pbph.shoppingmall.model.response.OrderInfoResponse;
import com.pbph.shoppingmall.model.response.OrderStatusResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.model.response.SaveShoppingCartResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        getOrderInfo();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }

    @Override
    public void getOrderInfo() {
        OrderInfoRequest request = new OrderInfoRequest();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderId = getBaseView().getOrderId();
        request.request().subscribe(
                new LogoutFilteErrorConsumer<OrderInfoResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(OrderInfoResponse vo) throws Exception {
                        OrderInfoResponse.DataBean data = vo.getData();
                        getBaseView().setOrderInfo(data);
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
    public void delOrder(int id) {
        DelOrderRequest<ResultResponse> request = new DelOrderRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderId = id;
        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().onDelOrder(id);
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
    public void cancelOrder(int id) {

        CancelOrderRequest<OrderStatusResponse> request = new CancelOrderRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderId = id;
        request.request().subscribe(
                new LogoutFilteErrorConsumer<OrderStatusResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(OrderStatusResponse vo) throws Exception {
                        getOrderInfo();
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
    public void confirmOrder(int id) {


        ConfirmOrderRequest<OrderStatusResponse> request = new ConfirmOrderRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderId = id;
        request.request().subscribe(
                new LogoutFilteErrorConsumer<OrderStatusResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(OrderStatusResponse vo) throws Exception {
                        getOrderInfo();
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
    public void saveShoppingCart(int goodsInfoId, int goodsNum, int shopType) {

        SaveShoppingCartRequest<SaveShoppingCartResponse> saveShoppingCartRequest = new
                SaveShoppingCartRequest<>();
        saveShoppingCartRequest.customerId = UserInfo.getInstance().getCustomerId();
        saveShoppingCartRequest.goodsInfoId = goodsInfoId;
        saveShoppingCartRequest.goodsNum = goodsNum;
        saveShoppingCartRequest.shopType = shopType;
//        saveShoppingCartRequest.mobile = UserInfo.getInstance().getMobile();

        saveShoppingCartRequest.request().subscribe(new LogoutConsumer<SaveShoppingCartResponse>(getBaseView().getContext()) {
            @Override
            public void onDo(SaveShoppingCartResponse vo) throws Exception {
                Toast.makeText(getBaseView().getContext(), "添加成功", Toast.LENGTH_SHORT).show();
            }
        }, new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
            @Override
            public void onDo(Throwable throwable) throws Exception {

            }
        });
    }


    @Override
    public void buyAgain(String code) {

        OrderBuyAgainRequest<ResultResponse> request = new OrderBuyAgainRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();
        request.orderCode = code;

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().onBuyAgain();
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
