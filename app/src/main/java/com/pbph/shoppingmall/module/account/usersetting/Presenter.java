package com.pbph.shoppingmall.module.account.usersetting;


import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.model.request.GetMyCustomerRequest;
import com.pbph.shoppingmall.model.request.UpdateCustomerInfoRequest;
import com.pbph.shoppingmall.model.request.UpdateCustomerRequest;
import com.pbph.shoppingmall.model.response.GetMyCustomerResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutFilteErrorConsumer;
import com.pbph.shoppingmall.utils.AliOss;
import com.pbph.shoppingmall.utils.SpHelper;
import com.utils.StringUtils;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {


    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
        getMyCustomer();
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
    public void updateCustomerRequest(UpdateCustomerRequest<ResultResponse> request) {

        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {

                        if (!StringUtils.isEmpty(request.customerNickname)) {
                            MyApplication.userInfo.setCustomerNickname(request.customerNickname);
                        }

                        if (!StringUtils.isEmpty(request.mobile)) {
                            MyApplication.userInfo.setMobile(request.mobile);
                        }
                        if (!StringUtils.isEmpty(request.customerImg)) {
                            MyApplication.userInfo.setCustomerImg(request.customerImg);
                        }
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("修改失败");
                    }
                }
        );
    }

    @Override
    public void updateCustomerInfoRequest(UpdateCustomerInfoRequest<ResultResponse> request) {
        request.customerId = MyApplication.userInfo.getCustomerId();

        request.request().subscribe(
                new LogoutFilteErrorConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        if (!StringUtils.isEmpty(request.customerBirthday)) {
                            MyApplication.userInfo.setCustomerBirthday(request.customerBirthday);
                        }
                        if (request.sex != null) {
                            MyApplication.userInfo.setSex(request.sex);
                        }
                    }
                }
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("修改失败");
                    }
                }
        );
    }

    @Override
    public void logout() {
//        SpHelper.getInstance().putAccountPassword(acc, "");
//                        SpHelper.getInstance().putUserId(vo.getData().getPpid());

        MyApplication.userInfo.setCustomerId(null);
        MyApplication.userInfo.setToken(null);
        SpHelper.getInstance().putUserId(0);
        SpHelper.getInstance().putToken(null);


        getBaseView().onLogout();
    }

    @Override
    public void saveImg2AliOss(String url) {

        AliOss.getInstance().uplodImage(getBaseView().getContext().getApplicationContext(), url, new AliOss.OnOosUploadListener() {
            @Override
            public void onSuccess(String path) {

                UpdateCustomerRequest<ResultResponse> request = new UpdateCustomerRequest<>();
                request.customerImg = path;

                updateCustomerRequest(request);
            }

            @Override
            public void onFail() {
                getBaseView().toastShort("上传失败");
            }
        });
    }
}
