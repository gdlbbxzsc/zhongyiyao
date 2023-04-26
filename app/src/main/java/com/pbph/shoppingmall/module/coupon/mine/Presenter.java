package com.pbph.shoppingmall.module.coupon.mine;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxbus2.RxBusF;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.message.CouponMineOperationMsg;
import com.pbph.shoppingmall.model.request.SelectMyCouponCountRequest;
import com.pbph.shoppingmall.model.response.SelectMyCouponCountResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    CouponMineOperationMsg msg = new CouponMineOperationMsg();

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        selectMyCouponCountRequest();
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
    }


    @Override
    public void postRxBus4delData(OperationMsgType type) {
        msg.type = type;
        RxBusF.post0(msg);
    }

    @Override
    public void selectMyCouponCountRequest() {

        SelectMyCouponCountRequest<SelectMyCouponCountResponse> request = new SelectMyCouponCountRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<SelectMyCouponCountResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(SelectMyCouponCountResponse vo) throws Exception {
                        getBaseView().updateNums(vo.getData());
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
