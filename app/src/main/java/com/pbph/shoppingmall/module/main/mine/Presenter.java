package com.pbph.shoppingmall.module.main.mine;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.request.GetMyCollectionAndBrowseRequest;
import com.pbph.shoppingmall.model.request.GetMyCustomerRequest;
import com.pbph.shoppingmall.model.request.GetOrderNumberRequest;
import com.pbph.shoppingmall.model.request.GetSysSwitchRequest;
import com.pbph.shoppingmall.model.request.PsetSignRequest;
import com.pbph.shoppingmall.model.response.GetMyCollectionAndBrowseResponse;
import com.pbph.shoppingmall.model.response.GetMyCustomerResponse;
import com.pbph.shoppingmall.model.response.GetOrderNumberResponse;
import com.pbph.shoppingmall.model.response.GetSysSwitchResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    boolean switchSign = false;

    boolean switchChat = false;

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
    public void getMyCustomer() {
        GetMyCustomerRequest<GetMyCustomerResponse> request = new GetMyCustomerRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetMyCustomerResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetMyCustomerResponse vo) throws Exception {
                        GetMyCustomerResponse.DataBean dataBean = vo.getData();
                        MyApplication.userInfo.setUserInfoFromGetMyCustomer(dataBean);

                        getBaseView().setMyCustomer(dataBean);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                    }
                }
        );
    }

    @Override
    public void getSysSwitch() {
        GetSysSwitchRequest<GetSysSwitchResponse> request = new GetSysSwitchRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetSysSwitchResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetSysSwitchResponse vo) throws Exception {

                        GetSysSwitchResponse.DataBean.NpSysSwitchBean switchBean = vo.getData().getNpSysSwitch();

                        switchSign = switchBean.getSwitchSign() == 1;
                        switchChat = switchBean.getSwitchChat() == 1;

                        getBaseView().changeSignType(switchSign);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                    }
                }
        );
    }

    @Override
    public void getMyCollectionAndBrowseRequest() {

        GetMyCollectionAndBrowseRequest<GetMyCollectionAndBrowseResponse> request = new GetMyCollectionAndBrowseRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetMyCollectionAndBrowseResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetMyCollectionAndBrowseResponse vo) throws Exception {
                        GetMyCollectionAndBrowseResponse.DataBean bean = vo.getData();
                        getBaseView().setMyCollectionAndBrowse(bean.getBrowseRecordCount(), bean.getGoodsCount(), bean.getStoreCount());
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                    }
                }
        );
    }


    @Override
    public void sign() {
        if (!switchSign) return;

        PsetSignRequest<ResultResponse> request = new PsetSignRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        getBaseView().signSucc();
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("签到失败");
                    }
                }
        );
    }

    @Override
    public void getOrderNumber() {
        GetOrderNumberRequest<GetOrderNumberResponse> request = new GetOrderNumberRequest<>();
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<GetOrderNumberResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(GetOrderNumberResponse vo) throws Exception {
                        GetOrderNumberResponse.DataBean bean = vo.getData();
                        getBaseView().setOrderNumber(bean);
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
//                        getBaseView().toastShort("签到失败");
                    }
                }
        );
    }
}
