package com.pbph.shoppingmall.module.account.login;


import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.pbph.mvp.base.mvp.BasePresenter;
import com.pbph.mvp.custom.dialog.WaitUI;
import com.pbph.mvp.rxjava2.filterobserver.BaseErrorConsumer;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.model.request.MoblieLoginRequest;
import com.pbph.shoppingmall.model.request.SendSmsValCodeRequest;
import com.pbph.shoppingmall.model.response.MoblieLoginResponse;
import com.pbph.shoppingmall.model.response.ResultResponse;
import com.pbph.shoppingmall.rxjava2.filterobserver.LogoutConsumer;
import com.pbph.shoppingmall.utils.AMUtils;
import com.pbph.shoppingmall.utils.SpHelper;

import io.reactivex.disposables.CompositeDisposable;

public class Presenter<T extends Contract.View> extends BasePresenter<T> implements Contract.Presenter {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private static final long DURATION_TIMES = 1500;

    public Presenter(T baseView) {
        super(baseView);
    }

    @Override
    public void subscribe() {  //  第一次创建默认执行的事情
    }

    @Override
    public void unsubscribe() { //销毁时执行的事情
        mCompositeDisposable.clear();
    }

    SendSmsValCodeRequest<ResultResponse> sendSmsValCodeRequest = new SendSmsValCodeRequest<>();

    @Override
    public void getNumCode(String acc) {

        if (!validateAccount(acc)) return;

        sendSmsValCodeRequest.mobile = acc;

        sendSmsValCodeRequest.request().subscribe(
                new LogoutConsumer<ResultResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(ResultResponse vo) throws Exception {
                        if (vo.getCode() != 200) {
                            getBaseView().toastShort(vo.getMsg());
                            getBaseView().resetNumcode();
                            return;
                        }
                        getBaseView().waitNumCode();
                    }
                }
                //失败
                , new BaseErrorConsumer<Throwable>(getBaseView().getContext()) {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("发送验证码失败");
                        getBaseView().resetNumcode();
                    }
                }
        );
    }

    MoblieLoginRequest<MoblieLoginResponse> moblieLoginRequest = new MoblieLoginRequest<>();

    @Override
    public void login(final String acc, String code) {


        if (!validateAccount(acc)) return;
        if (!validateCode(code)) return;

        WaitUI.Show(getBaseView().getContext());

        moblieLoginRequest.mobile = acc;
        moblieLoginRequest.code = code;

        lastTime = System.currentTimeMillis();

        moblieLoginRequest.request().subscribe(
                new LogoutConsumer<MoblieLoginResponse>(getBaseView().getContext()) {
                    @Override
                    public void onDo(MoblieLoginResponse vo) throws Exception {
                        WaitUI.Cancel();
                        if (vo.getCode() != 200) {
                            Toast.makeText(getBaseView().getContext(), vo.getMsg(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        SpHelper.getInstance().putAccountPassword(acc, "");
                        SpHelper.getInstance().putUserId(vo.getData().getCustomers().getCustomerId());
                        SpHelper.getInstance().putToken(vo.getData().getTokens());

                        MyApplication.userInfo.setUserInfoFromLogin(vo.getData());

                        long nowTime = scaleTimes();
                        onSucc(nowTime);
                    }
                }
                , new BaseErrorConsumer<Throwable>(

                        getBaseView().

                                getContext())

                {
                    @Override
                    public void onDo(Throwable throwable) throws Exception {
                        getBaseView().toastShort("登录失败");
                        WaitUI.Cancel();
                    }
                }
        );

//
    }

    boolean validateAccount(String call) {
        if (TextUtils.isEmpty(call)) {
            getBaseView().toastShort(R.string.phone_number_is_null);
            return false;
        }
        if (!AMUtils.isMobile(call)) {
            getBaseView().toastShort(R.string.Illegal_phone_number);
            return false;
        }
        return true;
    }

    boolean validateCode(String code) {
        if (TextUtils.isEmpty(code)) {
            getBaseView().toastShort("请输入验证码");
            return false;
        }
        if (!AMUtils.isNumCode(code)) {
            getBaseView().toastShort("验证码输入不正确");
            return false;
        }
        return true;
    }

    private long lastTime = System.currentTimeMillis();

    private long scaleTimes() {
        long nowTime = DURATION_TIMES - (System.currentTimeMillis() - lastTime);
        return nowTime < 0 ? 0 : nowTime;
    }

    private void onSucc(long nowTime) {
        new Handler().postDelayed(() -> getBaseView().goMain(), nowTime);
    }
}
