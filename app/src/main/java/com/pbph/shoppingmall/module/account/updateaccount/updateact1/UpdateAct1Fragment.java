package com.pbph.shoppingmall.module.account.updateaccount.updateact1;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pbph.mvp.base.mvp.BaseFragmentV4;
import com.pbph.shoppingmall.MyApplication;
import com.pbph.shoppingmall.R;
import com.pbph.shoppingmall.utils.ui.OnSingleClickListener;

/**
 * Created by Administrator on 2018/1/19.
 */

public class UpdateAct1Fragment extends BaseFragmentV4<Presenter> implements Contract.View {

    private TextView tvPhone;

    private EditText editText;

    private TextView tvNumCode;
    CountDownTimer timer;


    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int layoutResID() {
        return R.layout.fragment_updateact1;
    }

    @Override
    public void initView(View view) {

        tvPhone = view.findViewById(R.id.tv_phone);


        editText = view.findViewById(R.id.edt_num_code);

        tvNumCode = view.findViewById(R.id.tv_num_code);
        tvNumCode.setOnClickListener(onTitleClick);

        view.findViewById(R.id.button).setOnClickListener(onTitleClick);


        tvPhone.setText(MyApplication.userInfo.getMobile());

    }

    OnSingleClickListener onTitleClick = new OnSingleClickListener() {
        @Override
        public void onCanClick(View view) {
            switch (view.getId()) {

                case R.id.tv_num_code: {
                    presenter.getNumCode();
                }
                break;
                case R.id.button: {
                    presenter.submitNumCode(editText.getText().toString().trim());
                }
                break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroyView();
    }


    @Override
    public void resetNumcode() {
        tvNumCode.setEnabled(true);
        tvNumCode.setText("点击获取验证码");
    }

    @Override
    public void waitNumCode() {

        tvNumCode.setEnabled(false);

        timer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    tvNumCode.setText("重新发送(");
                    tvNumCode.append(String.valueOf(millisUntilFinished / 1000));
                    tvNumCode.append(")");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                try {
                    resetNumcode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }

}
